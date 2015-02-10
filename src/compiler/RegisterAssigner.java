package compiler;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;

import parser.PrologBaseVisitor;
import parser.PrologParser.ExprContext;
import parser.PrologParser.ListContext;
import parser.PrologParser.NumberContext;
import parser.PrologParser.PredicateContext;
import parser.PrologParser.VariableContext;

/**
 * The register assigner determines the registers for subtrees of the parse tree.
 * 
 * Some terminology and notes on the implementation:
 * Given a rule p(..):- q(..), r(..). or query ?- p(..), q(..), r(..). we call p, q and r top terms. For a top term the predicate itself (i.e. p/q/r) does
 * not get a register and the arguments of these predicates get special argument registers.
 * 
 * Registers are assigned in a breadth-first order. For variables there can be two registers if they are arguments. The first (or: prime) register is then either
 * the variable's stack- or X-register and the second is its argument register. The stack register is determined by the <code>PreProcessor</code>. 
 * 
 * IMPORTANT: The preprocessor is assumed to have been run on the query/rule/fact before the register assigner's visit method is called. 
 * 
 * @author Bas Testerink
 *
 */

public class RegisterAssigner extends PrologBaseVisitor<Integer> {
	private int counter = 0, nrOfArgs = 0; 													// Counter of argument and x-registers and the number of arguments of the top term
	private Queue<ParseTree> queue = new LinkedList<ParseTree>();							// Visitation queue
	private Map<ParseTree, Integer> xRegisters = new HashMap<ParseTree, Integer>();			// All the X and argment-registers 
	private Map<ParseTree, Integer> aRegisters = new HashMap<ParseTree, Integer>();			// All the argument-registers of the top term
	private Map<String, Integer> varPrimeRegisters = new HashMap<String, Integer>();		// Prime registers of variables, per variable name only 1 prime register
	private boolean topTerm; 																// True if the current predicate that is being visited is the top term
	private PreProcessor preProcessor;														// Contains the stack positions of variables if they are permanent variables
	 
	public RegisterAssigner(PreProcessor preProcessor){
		this.preProcessor = preProcessor;
	}
	
	/** Resets the assigner for the next goal to assign register to. */
	public void reset(){
		counter = nrOfArgs = 0;
		queue.clear(); 
		aRegisters.clear();
	} 
	
	// Every goal gets its own Xregisters, but the first goal after the head doesn't introduce new Xregisters, 
	// hence this method is not called for the first goal after the head.
	public void clearXRegisters(){
		xRegisters.clear();
		varPrimeRegisters.clear();
	}
	
	public Integer visitExpr(ExprContext ctx){
		visit(ctx.getChild(0));
		return null;
	} 
	
	public Integer visitList(ListContext ctx){
		if(ctx.getChildCount()==2){ 				// The list is [], i.e. treat as a constant
			if(counter < nrOfArgs){					
				aRegisters.put(ctx, counter);		// Only argument constants get a register
				counter++;
			}
			if(counter == nrOfArgs) counter = nrOfArgs+xRegisters.size(); // If this was the last argument then the xregister counter is set to the position after the x registers of temporary argument variables 	
		} else {
			if(counter < nrOfArgs){					// List is an argument
				aRegisters.put(ctx, counter);		// Then the list gets an argument register
				counter++;
			} else { 								// If this isn't a non-argument list (those do not get an x register)
				xRegisters.put(ctx, counter);		// Then assign a register.
				counter++;
			}
			if(counter == nrOfArgs) counter = nrOfArgs+xRegisters.size(); // If this was the last argument then the xregister counter is set to the position after the x registers of temporary argument variables 
			queue.add(ctx.getChild(1).getChild(0)); // Add the head
			queue.add(ctx.getChild(3)); 			// Add the tail
		} 
		if(!queue.isEmpty()) visit(queue.remove());	// Visit the next element
		return null;
	}
	
	public Integer visitNumber(NumberContext ctx){
		if(counter < nrOfArgs){
			aRegisters.put(ctx, counter);				// Only argument numbers get a register 
			counter++;
		}
		if(counter == nrOfArgs) counter = nrOfArgs+xRegisters.size(); // If this was the last argument then the xregister counter is set to the position after the x registers of temporary argument variables 
		if(!queue.isEmpty()) visit(queue.remove());		// Visit the next element
		return null;
	}
	
	public Integer visitPredicate(PredicateContext ctx){
		boolean isConstant = WAMTokenizer.arguments(ctx).size()==0; // This predicate is a constant.
		if(!topTerm){ 											 // If the visited predicate is not the top term
			if(counter < nrOfArgs){
				aRegisters.put(ctx, counter);					 // Then the predicate gets an argument register if it is an argument
				counter++;
			} else if(!isConstant){ 							// If this isn't a non-argument constant (those do not get an x register)
				xRegisters.put(ctx, counter);					// Then assign a register.
				counter++;
			}
		}
		if(counter == nrOfArgs) counter = nrOfArgs+xRegisters.size();		// If this was the last argument then the xregister counter is set to the position after the x registers of temporary argument variables 
		topTerm = false;													// If this was the top term then all predicates after it will be treated as the non-top-term
		for(ParseTree argument : WAMTokenizer.arguments(ctx))					// Add each argument into the queue
			queue.add(argument);
		if(!queue.isEmpty()) visit(queue.remove());							// Visit the next element
		return null;
	}

	public Integer visitVariable(VariableContext ctx){ 
		if(counter < nrOfArgs){													// The variable is an argument
			aRegisters.put(ctx, counter); 										// Add the variable as an argument
			counter++;	
			if(counter == nrOfArgs) counter = nrOfArgs + xRegisters.size(); 	// If this was the last argument then put the counter after the argument-temporary variable registers
		}
		Integer primeRegister = varPrimeRegisters.get(preProcessor.getVarName(ctx));		// First see if there was already a prime register assigned to this variable
		if(primeRegister == null){															// If not:
			primeRegister = preProcessor.getStackPosition(preProcessor.getVarName(ctx));	// Check whether the variable is permanent, if so, then we use that as its prime register
			if(primeRegister == null && counter < nrOfArgs){
				primeRegister = nrOfArgs + xRegisters.size(); 								// Create an X register for temporary variables that are arguments
				xRegisters.put(ctx, primeRegister);
			} else if(primeRegister == null && counter >= nrOfArgs){
				primeRegister = counter;
				counter++;
				xRegisters.put(ctx, primeRegister);
			} else primeRegister = WAMTokenizer.stackIndexToPrimeRegister(primeRegister);		// Make a conversion to distinguish that this is a Y-address instead of X-address
			varPrimeRegisters.put(preProcessor.getVarName(ctx), primeRegister);
		}
		
		if(!queue.isEmpty()) visit(queue.remove()); // Visit the next element
		return null;
	} 
	/** Needed for call variables (WAMTokenizer.visitGoal()). The variable should already have a register to be called so here is how we get it.*/
	public Integer getVarRegister(String varname){
		return varPrimeRegisters.get(varname);
	}
	

	/** Get the prime register of a subpart of a prolog term.  */
	public Integer getPrimeRegister(ParseTree tree){
		if(tree instanceof VariableContext)
			return varPrimeRegisters.get(preProcessor.getVarName(tree));
		return xRegisters.get(tree); 
	}
	
	/** Get the argument register of a subpart of a prolog term. */
	public Integer getARegister(ParseTree tree){
		return aRegisters.get(tree); 
	}
	
	/** Get the prime register of a subterm. Or if the term is an argument and not a variable, the argument register. */
	public Integer getRegister(ParseTree tree){
		Integer r = getPrimeRegister(tree);
		if(r==null) return getARegister(tree);
		else return r;
	}
	
	//Setter
	public void setTopTerm(boolean b, int nrOfArgs){ this.topTerm = b; this.nrOfArgs = nrOfArgs; }

	public void outputRegisterAssignment(Parser p){
		Map<Integer, String> str = new HashMap<Integer, String>();
		for(ParseTree tree : aRegisters.keySet()){
			str.put(aRegisters.get(tree), regStr(tree,p));
		}
		for(ParseTree tree : xRegisters.keySet()){
			if(xRegisters.get(tree)>=0) str.put(xRegisters.get(tree), regStr(tree,p)); 
		}
		for(int i = 0; i < str.size(); i++){ 
			System.out.println((i<nrOfArgs?"A":"X")+(i+1)+" = "+str.get(i));
		}
	}
	private String regStr(ParseTree tree, Parser p){
		if(tree instanceof VariableContext){
			return preProcessor.getVarName(tree);
		} else if(tree instanceof PredicateContext){
			String functor = WAMTokenizer.predicateFunctor((PredicateContext)tree);
			if(tree.getChildCount()>1){
				functor += "(";
				ParseTree parenthesized = tree.getChild(1);
				ParseTree argseq = parenthesized.getChild(1);
				int nrArgs = ((argseq.getChildCount()-1)/2) + 1;
				for(int j = 0; j < nrArgs; j++){ 
					Integer child = getPrimeRegister(argseq.getChild(j*2).getChild(0));
					String childReg = child<0?("Y"+(child*-1)):"X"+(child+1);
					functor += childReg+(j!=(nrArgs-1)?",":"");
				}
				functor += ")";
			}
			return functor;
		}
		return " error ";
	}

}
