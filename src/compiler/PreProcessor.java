package compiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.antlr.v4.runtime.CommonToken; 
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;


import parser.PrologBaseVisitor;
import parser.PrologParser.ArgseqContext;
import parser.PrologParser.ExprContext;
import parser.PrologParser.ListContext;
import parser.PrologParser.NumberContext;
import parser.PrologParser.ParenthesizedContext;
import parser.PrologParser.PlruleContext;
import parser.PrologParser.PredicateContext;
import parser.PrologParser.PrednameContext;
import parser.PrologParser.QueryContext;
import parser.PrologParser.VariableContext;

/**
 * The pre-processor has several functionalities:
 * 	1) Variables are given an internal name (for casting a tree to a Prolog string, needed for retract)
 *  2) Variables are given a compiler name (so that '_' variables are not all treated as one and the same)
 *  3) Variables are quantified as being permanent or temporary
 *  4) Lists that are notated as [a,b,c] (or differently notated) are transformed to the format [a[b[c|[]]]] (to ease the compilation process).
 *  5) Infix expressions are transformed to prefix expressions (to ease the compilation process)  
 *  6) Parenthesis-predicates (e.g. p((a,b))) are transformed to predicates. They are treated as predicates with empty-string functor.
 * 
 * The implementations performs a depth-first walk through the tree. 
 * 
 * @author Bas Testerink
 */

public class PreProcessor extends PrologBaseVisitor<Integer> {
	private Set<String> isPermanent = new HashSet<String>();						// The permanent variables are stored here.
	private Map<String, Integer> lastOccurrence = new HashMap<String, Integer>(); 	// Last body part occurence, head and first count as one.
	private Map<String, Integer> stackPosition = new HashMap<String, Integer>(); 	// Position in the environments on the stack.
	private Map<ParseTree, String> varNames = new HashMap<ParseTree, String>();		// Compilation name of variables.
	private int currentGoal = 0;													// Current body goal that the preprocessor is walking through.
	private boolean allPermanent = false; 											// For queries we make all vars permanent so we can get their final bindings
	private Map<String, String> internalVarNames = new HashMap<String, String>();   // For retract: keeps the names of variables.
	private StringBuffer string = new StringBuffer();								// For retract: the Prolog string representation of the tree.

	// Should be reset before every rule and query
	private void reset(){
		isPermanent.clear();
		lastOccurrence.clear();
		stackPosition.clear();
		varNames.clear();
		currentGoal = 0;
		string = new StringBuffer();
		internalVarNames.clear();
		allPermanent = false;
	}
 
	// For queries simply visit each child.
	public Integer visitQuery(QueryContext ctx){
		reset();											// Reset to defaults
		allPermanent = true;
		int goalCount = WAMTokenizer.getGoalCount(ctx);		
		for(ParseTree subgoal : WAMTokenizer.subGoals(ctx)){ 	// Visit the subgoals
			visit(subgoal);
			currentGoal++; 										
		}
		assignStackPositions(goalCount); 					// Give each permanent variable a stack position
		return null;
	}

	// For rules first the head is visited, then the goals. After the first goal we increment the "currentgoal" parameter. 
	public Integer visitPlrule(PlruleContext ctx){ 
		reset();											// Reset to defaults
		int nrOfGoals = WAMTokenizer.getGoalCount(ctx);
		visit(ctx.getChild(0)); 							// Visit the head of the rule
		if(ctx.getChildCount()>2) string.append(":-");
		for(ParseTree subgoal : WAMTokenizer.subGoals(ctx)){
			visit(subgoal); 								// Visit each goal
			string.append(",");
			currentGoal++; 									// Start incrementing after the first subgoal
		} 
		if(ctx.getChildCount()>2)
			string.deleteCharAt(string.length()-1); 		// Remove the final comma if necessary 
		assignStackPositions(nrOfGoals);   					// Give each permanent variable a stack position
		return null;
	} 

	// I suggest looking at ANTLR visual parsetrees to really see what is going on here. The summary is that
	// we fix infix expressions to prefix, and nameless predicates are properly represented with a functor name (which
	// is the empty string). 
	public Integer visitExpr(ExprContext ctx){
		if(ctx.getChildCount()==3){ 						// This expression is an infix operator
			fixExpr(ctx); 									// Transform to prefix
		} else if (ctx.getChild(0) instanceof ParenthesizedContext){ // Predicate without name
			fixParenthesized(ctx);									 // Give it a name ""
		}
		visit(ctx.getChild(0));								// Visit the subtree below it, which is now a predicate
		return null;
	}  

	// Transforms an infix expression to prefix. This is only necessary to ease compilation later on.
	public static void fixExpr(ExprContext ctx){ 
		PredicateContext pred = new PredicateContext(ctx, 0);
		PrednameContext functor = new PrednameContext(pred, 0);
		ParenthesizedContext paren = new ParenthesizedContext(pred, 0);
		ArgseqContext args = new ArgseqContext(paren, 0);

		functor.children = new ArrayList<ParseTree>();
		paren.children = new ArrayList<ParseTree>();
		pred.children = new ArrayList<ParseTree>();
		args.children = new ArrayList<ParseTree>();

		pred.children.add(functor);
		pred.children.add(paren);

		functor.children.add(ctx.getChild(1)); 			// The operator becomes the functor
		paren.children.add(null); 						// The ( placeholder
		paren.children.add(args);
		paren.children.add(null); 						// The ) placeholder
		args.children.add(ctx.getChild(0));
		args.children.add(null); 						// The comma placeholder
		args.children.add(ctx.getChild(2)); 

		ctx.children.remove(2);
		ctx.children.remove(1);
		ctx.children.set(0, pred);
	}

	// Transforms a nameless predicate to a proper predicate tree.
	public static void fixParenthesized(ExprContext ctx){
		PredicateContext pred = new PredicateContext(ctx, 0);
		PrednameContext functor = new PrednameContext(pred, 0);
		ParenthesizedContext paren = (ParenthesizedContext)ctx.getChild(0);

		functor.children = new ArrayList<ParseTree>(); 
		pred.children = new ArrayList<ParseTree>();

		functor.children.add(new TerminalNodeImpl(new CommonToken(0,""))); // Nameless functor

		pred.children.add(functor);
		pred.children.add(paren);

		ctx.children.set(0, pred);
	}

	public Integer visitNumber(NumberContext ctx){ 
		Double d = Double.parseDouble(ctx.getChild(0).getText());
		string.append(d.toString()); // The reason why it is first cast to Double is so that it always has the single digit precision.
		return null; 
	}

	public Integer visitList(ListContext ctx){ 
		if(ctx.getChildCount() > 2){ // Otherwise this is the empty list
			fixList(ctx); 			 // Makes sure the list is of the format [ headElement | tailVar/List ]
			string.append('[');
			visit(ctx.getChild(1));  // Visit the head
			string.append('|');
			visit(ctx.getChild(3));  // Visit the tail
			string.append(']');
		} else string.append("[]");
		return null;
	}

	// Convert any list to [a[b[c|[]]]] format to ease compilation.
	private void fixList(ListContext ctx){
		ArgseqContext args = (ArgseqContext)ctx.getChild(1); 
		if(ctx.getChildCount() == 3){ 						// Three children means 1:[ 2: argseq 3:] so no tail 
			replaceFirstElement(ctx, args); 				// convert [a,b,c] to [a]
			ctx.children.add(2, null); 						// | replacement
			ctx.children.add(3,makeList(ctx, args, null)); 	// Turn args= [a0a1a2...] into list [a1a2...|[]]
		} else { 											// Otherwise [ argseq | tail ], argsequence may still be faulty though
			if(args.getChildCount()==1) return; 			// Arg sequence is good
			replaceFirstElement(ctx, args); 				// put the first element as the element
			ctx.children.set(3, makeList(ctx, args, ctx.getChild(3))); // Other elements get put into the tail list
		}
	}

	// Given a sequence a0a1a2..., returns a list [a1a2...], used for fixing lists.
	private ListContext makeList(ListContext parent, ArgseqContext elems, ParseTree tail){
		if(elems.getChildCount() == 1){ 							// No elements left, so return empty list
			return emptyList(parent);
		} else {
			ListContext l = new ListContext(parent, 0);
			l.children = new ArrayList<ParseTree>();
			l.children.add(null); 									// [ replacement
			ParserRuleContext a = new ArgseqContext(parent, 0);
			a.children = new ArrayList<ParseTree>();
			for(int i = 2; i < elems.getChildCount(); i++){ 		// Skip the first element and comma
				a.children.add(elems.getChild(i));
			}
			l.children.add(a); 										// Add the elements
			l.children.add(null); 									// | replacement
			if(tail == null) l.children.add(emptyList(l)); 			// Was no tail list given, so use emptylist
			else l.children.add(tail); 								// A tail list was given so use it.
			l.children.add(null); 									// ] replacement
			return l;
		} 
	}

	// Set the children of a list to its first argument. Used for fixing lists.
	private void replaceFirstElement(ListContext list, ArgseqContext args){
		if(args.getChildCount()>1){ 
			ArgseqContext firstArgument = new ArgseqContext(list, 0);
			firstArgument.children = new ArrayList<ParseTree>();
			firstArgument.children.add(args.getChild(0));
			list.children.set(1, firstArgument);
		}
	}

	// Make an emptylist tree.
	private ListContext emptyList(ParserRuleContext parent){
		ListContext c = new ListContext(parent, 0);
		c.children = new ArrayList<ParseTree>();
		c.children.add(null);
		c.children.add(null);
		return c;
	}

	public Integer visitPredicate(PredicateContext ctx){ 
		string.append(WAMTokenizer.predicateFunctor(ctx));
		List<ParseTree> list = WAMTokenizer.arguments(ctx);
		if(list.size()>0) string.append("(");
		for(ParseTree t : WAMTokenizer.arguments(ctx)){ 		// For predicates just visit each child
			visit(t);	 
			string.append(",");
		}
		if(list.size()>0){
			string.deleteCharAt(string.length()-1);			// Remove last comma
			string.append(")");
		}
		return null;
	}

	public Integer visitVariable(VariableContext ctx){  
		// Determine variable name for the compilation process.
		if(ctx.getText().equals("_")) varNames.put(ctx, "_anon"+varNames.size());
		else varNames.put(ctx,ctx.getText());
		
		// Get name for internal prolog string representation (needed for retract).
		String internalName = internalVarNames.get(varNames.get(ctx));
		if(internalName == null){
			internalName = "_V"+internalVarNames.size();
			internalVarNames.put(varNames.get(ctx), internalName);
		}
		string.append(internalName);
		
		Integer last = lastOccurrence.get(varNames.get(ctx));	// Determine whether the variable is permanent
		if((allPermanent || (last != null && currentGoal!=last)) 
				&& !isPermanent.contains(varNames.get(ctx))){
			isPermanent.add(varNames.get(ctx));
		}
		lastOccurrence.put(varNames.get(ctx), currentGoal);     // Update the last occurrence
		return null;
	}  

	public int nrOfPermVariables(){ return isPermanent.size(); }
	public String getVarName(ParseTree p){ return varNames.get(p); }
	public String getStringRepresentation(){ return string.toString(); }

	/**
	 * Returns the stack position of a variable, and NULL if the variable is not permanent.
	 * Used by the register assigner and for determining the first register of put/set/get/unify instructions.
	 * @param variable Variable name.
	 * @return Position in the stack (ordered on the latest occurrence in a subgoal in the rule/query). 
	 */
	public Integer getStackPosition(String variable){
		return stackPosition.get(variable);
	}

	/**
	 * The stack positions are ordered by last occurrence in a rule/query. This can be used for environment trimming
	 * but I'll probably not include that in the first version (update: I didn't, but anyone is welcome to implement it).
	 * @param nrOfGoals
	 */
	private void assignStackPositions(int nrOfGoals){
		for(int i = nrOfGoals-1; i >= 0; i--){
			for(String v : isPermanent){
				if(lastOccurrence.get(v)==i){
					stackPosition.put(v, stackPosition.size());
				}
			}
		}
	}

	/**
	 * Ouput the stack positions (for debugging).
	 */
	public void outputStackPositions(){
		for(int i = 0; i < isPermanent.size(); i++){ 
			for(String v : isPermanent){
				if(i==getStackPosition(v))System.out.println(v+"="+"Y"+getStackPosition(v));
			}
		}
	}
	
	/** So we can obtain values later. */
	public Map<String, Integer> getQueryVariablePositions(){
		Map<String, Integer> r = new HashMap<String, Integer>();
		r.putAll(stackPosition);
		return r;
	}
}
