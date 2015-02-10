package compiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;

import parser.PrologBaseVisitor;
import parser.PrologParser.ExprContext;
import parser.PrologParser.ListContext;
import parser.PrologParser.NumberContext;
import parser.PrologParser.PlruleContext;
import parser.PrologParser.PredicateContext;
import parser.PrologParser.PrednameContext;
import parser.PrologParser.QueryContext;
import parser.PrologParser.TopruleContext;
import parser.PrologParser.VariableContext;
import compiler.tokens.AllocateToken;
import compiler.tokens.ArgumentVariable;
import compiler.tokens.CallToken;
import compiler.tokens.CallVariableToken;
import compiler.tokens.CompileToken;
import compiler.tokens.ConstantToken;
import compiler.tokens.CutToken;
import compiler.tokens.DeallocateToken;
import compiler.tokens.EndOfHead;
import compiler.tokens.ListToken;
import compiler.tokens.NumberToken;
import compiler.tokens.ProceedToken;
import compiler.tokens.StringRepresentationToken;
import compiler.tokens.Structure;
import compiler.tokens.SubtermRegister;

public class WAMTokenizer extends PrologBaseVisitor<Integer>{   
	private Map<String, List<List<CompileToken>>> programTokens = new HashMap<String, List<List<CompileToken>>>(); // All the gathered tokens, key = functor/arity
	private List<CompileToken> tokens = new ArrayList<CompileToken>(); // Current tokens 
	private List<CompileToken> query = new ArrayList<CompileToken>(); // Last query
	private boolean isQuery = true; // Whether tokenize is in query mode
	private boolean topTerm = true; // Whether the currently visited predicate is a top term / subgoal
	private PreProcessor preprocessor = new PreProcessor(); // Quantifies variables as permanent/temporary
	private RegisterAssigner registerAssigner = new RegisterAssigner(preprocessor); // Assigns registers to parts of a subgoal
	private Queue<ParseTree> queue = new LinkedList<ParseTree>(); // Tokens are added in breadth first order for which we need this queue
	public Parser p; // For debugging, helps with outputting the register assignment
	

	private void reset(){   
		tokens = new ArrayList<CompileToken>();
		topTerm = true;
		queue.clear();
	}   

	public Integer visitToprule(TopruleContext ctx) {  
		for(int i = 0; i < ctx.getChildCount(); i++){ 
			reset();									// Reset the visitor
			preprocessor.visit(ctx.getChild(i)); 		// Quantify variables for being permanent, temporary or unsafe
			visit(ctx.getChild(i));			
		}
		return null;
	}
	
	public Integer visitQuery(QueryContext ctx){ 
		tokens.add(new StringRepresentationToken(preprocessor.getStringRepresentation()));
		isQuery = true; 
		tokens.add(new AllocateToken(preprocessor.nrOfPermVariables())); 
		for(ParseTree subgoal : WAMTokenizer.subGoals(ctx))
			visitGoal(subgoal.getChild(0), false); 
		//if(nrOfGoals>1) tokens.add(new DeallocateToken());	  
		query = tokens; 
		return null;
	}

	public Integer visitPlrule(PlruleContext ctx){ 
		tokens.add(new StringRepresentationToken(preprocessor.getStringRepresentation()));
		int nrOfGoals = WAMTokenizer.getGoalCount(ctx);
		if(nrOfGoals > 0) // Facts only have 2 children: the fact and '.'
			tokens.add(new AllocateToken(preprocessor.nrOfPermVariables()));
		isQuery = false; // The head is a program-compilation wise
		ParseTree head = ctx.getChild(0);
		visitGoal(head, false); // visit the head
		isQuery = true; 
		boolean isFirstGoalAfterTheHead = true;
		if(nrOfGoals > 0){ 
			tokens.add(new EndOfHead());
			for(ParseTree subgoal : WAMTokenizer.subGoals(ctx)){
				visitGoal(subgoal.getChild(0),isFirstGoalAfterTheHead);
				isFirstGoalAfterTheHead = false;
			}
			tokens.add(new DeallocateToken());
		} else tokens.add(new ProceedToken());
		int argCount = WAMTokenizer.getArgumentCount((PredicateContext) head);
		String rule_functor = WAMTokenizer.predicateFunctor((PredicateContext)head)+"/"+argCount; 
		if(programTokens.get(rule_functor)==null)
			programTokens.put(rule_functor, new ArrayList<List<CompileToken>>());
		programTokens.get(rule_functor).add(tokens);
		return null;
	} 
	
	private void visitGoal(ParseTree goal, boolean isFirstGoalAfterTheHead){
		if(goal instanceof VariableContext){  
			tokens.add(new CallVariableToken(registerAssigner.getVarRegister(((VariableContext)goal).getText())));
		} else {
			int arity = WAMTokenizer.getArgumentCount((PredicateContext)goal);
			if(arity == 0 && WAMTokenizer.predicateFunctor((PredicateContext)goal).equals("!")){
				tokens.add(new CutToken());
			} else {
				queue.clear();
				registerAssigner.reset();
				if(!isFirstGoalAfterTheHead) registerAssigner.clearXRegisters();
				registerAssigner.setTopTerm(true, arity);	// The top level predicate(s) are top terms
				registerAssigner.visit(goal); 
				topTerm = true;
				visit(goal); 
				if(isQuery)
					tokens.add(new CallToken(WAMTokenizer.predicateFunctor((PredicateContext)goal), arity));
			}
		}
	}

	public Integer visitExpr(ExprContext ctx){
		visit(ctx.getChild(0));
		return null;
	}  

	public Integer visitPredicate(PredicateContext ctx){
		boolean currentIsTopTerm = topTerm; // All children are visited as non-topterms
		topTerm = false;
		int arity = WAMTokenizer.getArgumentCount(ctx);
		if(arity > 0){ 
			if(isQuery) // For a query first visit the children in depth first order
				for(ParseTree child : WAMTokenizer.arguments(ctx))
					visit(child);
			
			if(!currentIsTopTerm){ // If the current term is a top term then we do not need a token for it
				tokens.add(new Structure(registerAssigner.getRegister(ctx), WAMTokenizer.predicateFunctor(ctx), arity)); 
			 
				for(ParseTree child : WAMTokenizer.arguments(ctx)){ 
					argumentTree(child.getChild(0));
				}
			}
			
			if(!isQuery){ // For a program visit the children after the parent in breadth first order
				for(ParseTree child : WAMTokenizer.arguments(ctx))
					queue.add(child);
			}
		} else if(!currentIsTopTerm){ // Arity is zero hence it's a constant
			Integer register = registerAssigner.getRegister(ctx); // If it was a non-argument constant then it won't have a register
			if(register!=null) // If this is non-argument then it is already handled, otherwise put in the constant
				tokens.add(new ConstantToken(ctx.getChild(0).getText(),register)); 
		}
		if(!isQuery && !queue.isEmpty())visit(queue.remove());
		return null;
	}
	
	public Integer visitNumber(NumberContext ctx){ // Numbers can only pass by as arguments of (sub)terms, hence their tokens are already created in the predicate and list methods
		Integer register = registerAssigner.getRegister(ctx); // If it was a non-argument number then it won't have a register
		if(register!=null){
			String str = (ctx.getChildCount()==1?ctx.getChild(0).getText():("-"+ctx.getChild(1))); // positive and negative number
			tokens.add(new NumberToken(Double.parseDouble(str), register)); 
		}
		if(!isQuery && !queue.isEmpty())visit(queue.remove());
		return null;
	}
	
	public Integer visitList(ListContext ctx){
		if(ctx.getChildCount() == 2){ // Constant case
			Integer register = registerAssigner.getRegister(ctx); // If it was a non-argument number then it won't have a register
			if(register!=null)
				tokens.add(new ConstantToken("[]", register));
		} else {
			if(isQuery){
				visit(ctx.getChild(1).getChild(0).getChild(0));
				visit(ctx.getChild(3));
			}
			tokens.add(new ListToken(registerAssigner.getRegister(ctx)));
			argumentTree(ctx.getChild(1).getChild(0).getChild(0)); // Element
			argumentTree(ctx.getChild(3)); // Tail
			if(!isQuery){
				queue.add(ctx.getChild(1).getChild(0).getChild(0));
				queue.add(ctx.getChild(3));
			}
		}
		if(!isQuery && !queue.isEmpty())visit(queue.remove());
		return null;
	}
	
	/**
	 * For lists and predicates handles one of the arguments. If it is a constant or number then it is added directly rather than
	 * the Y/X register.
	 * @param childtree
	 */
	public void argumentTree(ParseTree childtree){ 
		if(childtree instanceof PredicateContext && childtree.getChildCount()==1){
			Integer register = registerAssigner.getRegister(childtree);
			tokens.add(new ConstantToken(childtree.getChild(0).getText(), register==null?(-1):register));
		} else if(childtree instanceof NumberContext){
			Integer register = registerAssigner.getRegister(childtree);
			String str = (childtree.getChildCount()==1?childtree.getChild(0).getText():("-"+childtree.getChild(1))); // positive and negative number
			tokens.add(new NumberToken(Double.parseDouble(str), register==null?(-1):register));	
		} else if(childtree instanceof ListContext && childtree.getChildCount()==2){ // an empty list = constant
			Integer register = registerAssigner.getRegister(childtree);
			tokens.add(new ConstantToken("[]", register==null?(-1):register));
		} else tokens.add(new SubtermRegister(registerAssigner.getRegister(childtree)));	
	}

	public Integer visitVariable(VariableContext ctx){
		if(registerAssigner.getARegister(ctx)!=null) // The variable is an argument for a top term 
			tokens.add(new ArgumentVariable(registerAssigner.getPrimeRegister(ctx),registerAssigner.getARegister(ctx),preprocessor.getVarName(ctx))); 
		if(!isQuery&&!queue.isEmpty()) visit(queue.remove());
		return null;
	}  

	public void outputTokens(){
		String str = "Token stream: ";
		for(CompileToken t : tokens)
			str += " "+ t;
		System.out.println(str); 
	}
	
	public Map<String, List<List<CompileToken>>> getTokens(){ return programTokens; }
	public List<CompileToken> getQuery(){ return query; }
	public RegisterAssigner getRegisterAssigner(){ return registerAssigner; }
	 
	// Some auxiliary functions to make the tokenize classes more readable and for the sake of maintenance they are grouped together here
	public static int getArgumentCount(PredicateContext ctx){
		if(ctx.getChildCount() == 1) return 0; // If the only child is the predicate name then there are no arguments for this predicate.
		else {
			ParseTree parenthesized = ctx.getChild(1); // The parenthesized arguments of the predicate
			ParseTree argSeq = parenthesized.getChild(1); 	   // The argument sequence, each argument is separated by a ',' child
			return (argSeq.getChildCount()-1)/2 + 1;
		}
	}
	
	public static int getGoalCount(QueryContext ctx){
		if(ctx.getChildCount() == 1) return 0; // If the only child is the predicate name then there are no arguments for this predicate.
		else {
			ParseTree argSeq = ctx.getChild(1); 	   // The argument sequence, each argument is separated by a ',' child
			return (argSeq.getChildCount()-1)/2 + 1;
		}
	}
	
	public static int getGoalCount(PlruleContext ctx){
		if(ctx.getChildCount()==2) return 0; // If the only children are the head and '.' then there are no sub goals.
		else {
			ParseTree argSeq = ctx.getChild(2); 	   // The argument sequence, each argument is separated by a ',' child
			return (argSeq.getChildCount()-1)/2 + 1;
		}
	}

	public static List<ParseTree> arguments(PredicateContext ctx){
		List<ParseTree> r = new ArrayList<ParseTree>();
		if(ctx.getChildCount()> 1){ 							// If the only child is the predicate name then there are no arguments for this predicate.
			ParseTree parenthesized = ctx.getChild(1); 			// The parenthesized arguments of the predicate
			ParseTree argSeq = parenthesized.getChild(1); 	   	// The argument sequence, each argument is separated by a ',' child
			int amount = (argSeq.getChildCount()-1)/2 + 1;
			for(int i = 0; i < amount; i++)
				r.add(argSeq.getChild(i*2));
		}
		return r;
	}
	
	public static List<ParseTree> subGoals(QueryContext ctx){
		List<ParseTree> r = new ArrayList<ParseTree>();
		if(ctx.getChildCount()> 2){ 							// If the query is "?-"  and "." only then there are no subgoals
			ParseTree argSeq = ctx.getChild(1); 	   			// The argument sequence, each argument is separated by a ',' child
			int amount = (argSeq.getChildCount()-1)/2 + 1;
			for(int i = 0; i < amount; i++)
				r.add(argSeq.getChild(i*2));
		}
		return r;
	}
	
	public static List<ParseTree> subGoals(PlruleContext ctx){
		List<ParseTree> r = new ArrayList<ParseTree>();
		if(ctx.getChildCount()> 2){ 							// If the only child is the head and "."
			ParseTree argSeq = ctx.getChild(2); 	   			// The argument sequence, each argument is separated by a ',' child
			int amount = (argSeq.getChildCount()-1)/2 + 1;
			for(int i = 0; i < amount; i++)
				r.add(argSeq.getChild(i*2));
		}
		return r;
	}
	
	public static String predicateFunctor(PredicateContext ctx){
		if(ctx.getChild(0) instanceof PrednameContext){ // Otherwise just parenthesis without any functor name as in p(a,(b,c)).
			return ctx.getChild(0).getChild(0).getText();
		} else return "";
	}
	
	public static int stackIndexToPrimeRegister(int stackIndex){
		return (stackIndex+1)*-1;
	}
	
	public static int primeRegisterToStackIndex(int register){ return (register*-1)-1; }
	
	public static String varRegisterToString(int register){
		if(register < 0) return "Y"+(stackIndexToPrimeRegister(register)+1);
		else return "X"+(register+1);
	}
	
	/** So we can obtain values later. */
	public Map<String, Integer> getQueryVariablePositions(){
		return preprocessor.getQueryVariablePositions();
	}
}
