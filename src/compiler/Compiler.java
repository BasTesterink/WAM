package compiler;

import instructions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map; 
import java.util.Set;
  


import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import parser.PrologLexer;
import parser.PrologParser;
import compiler.tokens.*;
import runtime.CodeBase;
/**
 * I have never encountered a document that explains how to compile Prolog for the WAM. The compilation process I devised is based on 
 * what appears to be patterns from the tutorial book on the WAM. For instance, the assignment of registers to subterms is done in 
 * breadth first order, even though it says nowhere that this is the correct way to do it. If anyone knows a good way to compile Prolog
 * then please let me know.
 * 
 * Notes on the implementation:
 * Input is first converted into tokens. Tokens are created in three steps 1) a preprocessor checks what variables are permanent and fixes
 * various parsetree issues, 2) a register assigner gives each subterm a register, 3) the WAMTokenizer travels the tree and creates a list of
 * tokens. The tokens are then handled here as per the explanations in the WAM tutorial book. 
 * 
 * @author Bas Testerink
 */
public class Compiler {

	/** Compile a file and add it to the code base. Redefinitions of predicates append to the old ones (so they do not override!). */
	public static void compileFile(String file, CodeBase codebase){
		Map<String, List<List<CompileToken>>> tokens = codebase.mergeTokens(tokenizeFile(file));
		codebase.setCode(compile(tokens));
	}
	
	/** Compile the lists of tokens (one for each clause) for predicates. */
	private static Map<String,List<CompiledClause>> compile(Map<String, List<List<CompileToken>>> tokens){
		Map<String,List<CompiledClause>> result = new HashMap<String,List<CompiledClause>>();
		for(String functor : tokens.keySet()){
			if(!functor.equals("fail/0")){ // Cannot define the fail predicate as it should always fail
				List<CompiledClause> clauses = new ArrayList<CompiledClause>();
				List<List<CompileToken>> clauseTokens = tokens.get(functor);
				int arity = Integer.parseInt(functor.substring(functor.lastIndexOf('/')+1));
				for(int i = 0; i < clauseTokens.size(); i++){ 
					CompiledClause compiledClause = compileSingleClause(clauseTokens.get(i), functor, false);
					if(i == 0 && clauseTokens.size() > 1) compiledClause.getInstructions().add(0, new TryMe(arity));
					if(i > 0 && clauseTokens.size() > 2 && clauseTokens.size()-1 > i) compiledClause.getInstructions().add(0, new RetryMeElse());
					if(i > 0 && clauseTokens.size()-1 == i) compiledClause.getInstructions().add(0, new TrustMe());
					clauses.add(compiledClause);
				}
				result.put(functor, clauses);
			}
		}
		return result;
	}
	
	/** Compile a single fact (used by assert). */
	public static CompiledClause compileStringFact(String functor, String fact){ 
		return compileSingleClause(tokenizeFactString(fact), functor, false);
	}
	
	/** Compile a single clause. So no try/retry/trust/indexing instructions are added here. */
	private static CompiledClause compileSingleClause(List<CompileToken> tokens, String functor, boolean isQuery){
		List<Instruction> instructions = new ArrayList<Instruction>();
		Set<Integer> encountered = new HashSet<Integer>(); 
		for(int c = 1; c < tokens.size(); c++){
			CompileToken t = tokens.get(c);
			if(t instanceof AllocateToken){
				instructions.add(new Allocate(((AllocateToken)t).getNrOfVariables()));
			} else if(t instanceof DeallocateToken){
				instructions.add(new Deallocate());
			}  else if(t instanceof CutToken){
				instructions.add(new Cut());
			} else if(t instanceof ProceedToken){
				instructions.add(new Proceed());
			} else if(t instanceof CallVariableToken){ 
				instructions.add(new CallVariable(((CallVariableToken)t).getRegister()));
				Set<Integer> toremove = new HashSet<Integer>();
				for(int i : encountered){ // needed? A variable call always immediately follows some other call, or is the body of a chain rule.
					if(i>=0)toremove.add(i);
				}
				encountered.removeAll(toremove);
			} else if(t instanceof CallToken){
				instructions.add(new Call(((CallToken)t).getFunctor(),((CallToken)t).getArity()));
				// After a call you have to build the next query. XRegisters are reset after
				// each top term. So you need to remove their occurrences.
				Set<Integer> toremove = new HashSet<Integer>();
				for(int i : encountered){
					if(i>=0)toremove.add(i);
				}
				encountered.removeAll(toremove);
			} else if(t instanceof EndOfHead){
				isQuery = true;
			} else if(isQuery) instructions.add(processQueryTermToken(t, encountered));
			else instructions.add(processProgramTermToken(t, encountered));
		}
		return new CompiledClause(((StringRepresentationToken)tokens.get(0)).getValue(), functor, instructions);
	}

	/** Handle the encounter of a register inside a fact/program context. */
	private static Instruction processProgramTermToken(CompileToken t, Set<Integer> encountered){
		if(t instanceof ListToken){
			return new GetList(((ListToken) t).getRegister());
		} else if(t instanceof Structure){
			Structure s = (Structure)t;
			return new GetStructure(s.getRegister(), s.getFunctor(), s.getArity());
		} else if(t instanceof ConstantToken){
			ConstantToken s = (ConstantToken)t;
			if(s.getArgument()<0) return new UnifyConstant(s.getName());
			else return new GetConstant(s.getArgument(), s.getName()); 
		} else if(t instanceof NumberToken){
			NumberToken s = (NumberToken)t;
			if(s.getArgument()<0) return new UnifyNumber(s.getNumber());
			else return new GetNumber(s.getArgument(), s.getNumber()); 
		} else if(t instanceof SubtermRegister) {
			SubtermRegister s = (SubtermRegister)t;
			if(encountered.contains(s.getRegister())){
				return new UnifyValue(s.getRegister());
			} else {
				encountered.add(s.getRegister());
				return new UnifyVariable(s.getRegister());
			}
		} else if(t instanceof ArgumentVariable){
			ArgumentVariable s = (ArgumentVariable) t;
			if(encountered.contains(s.getPrimeRegister())){
				return new GetValue(s.getPrimeRegister(), s.getArgumentRegister());
			} else {
				encountered.add(s.getPrimeRegister());
				return new GetVariable(s.getPrimeRegister(), s.getArgumentRegister());
			}
		}
		return null;
	} 

	/** Handle the encounter of a register inside a body goal/query context. */
	private static Instruction processQueryTermToken(CompileToken t, Set<Integer> encountered){
		if(t instanceof ListToken){
			encountered.add(((ListToken) t).getRegister());
			return new PutList(((ListToken) t).getRegister());
		} else if(t instanceof Structure){
			Structure s = (Structure)t;
			encountered.add(((Structure) t).getRegister());
			return new PutStructure(s.getRegister(), s.getFunctor(), s.getArity());
		} else if(t instanceof ConstantToken){
			ConstantToken s = (ConstantToken)t;
			if(s.getArgument()<0) return new SetConstant(s.getName());
			else return new PutConstant(s.getArgument(), s.getName()); 
		} else if(t instanceof NumberToken){
			NumberToken s = (NumberToken)t;
			if(s.getArgument()<0) return new SetNumber(s.getNumber());
			else return new PutNumber(s.getArgument(), s.getNumber()); 
		} else if(t instanceof SubtermRegister) {
			SubtermRegister s = (SubtermRegister)t;
			if(encountered.contains(s.getRegister())){
				return new SetValue(s.getRegister());
			} else {
				encountered.add(s.getRegister());
				return new SetVariable(s.getRegister());
			}
		} else if(t instanceof ArgumentVariable){
			ArgumentVariable s = (ArgumentVariable) t;
			if(encountered.contains(s.getPrimeRegister())){
				return new PutValue(s.getPrimeRegister(), s.getArgumentRegister());
			} else {
				encountered.add(s.getPrimeRegister());
				return new PutVariable(s.getPrimeRegister(), s.getArgumentRegister());
			}
		}
		return null;
	} 

	/** Compile a query. Will overwrite the last compiled query. 
	 * @return */
	public static Map<String, Integer> compileQuery(String query, CodeBase codebase){
		WAMTokenizer tokenizer = visitor(new ANTLRInputStream(query));
		List<CompileToken> tokens = tokenizer.getQuery(); 
		CompiledClause queryInstructions = compileSingleClause(tokens,"query",true);
		queryInstructions.getInstructions().add(new EndOfQuery());
		codebase.setQuery(queryInstructions); 
		return tokenizer.getQueryVariablePositions();
	}
	
	// Some calls for tokenizing input.
	
	public static Map<String, List<List<CompileToken>>> tokenizeFile(String file){
		try {
			ANTLRFileStream stream = new ANTLRFileStream(file);
			return visitor(stream).getTokens();
		} catch (IOException e) { 
			e.printStackTrace();
		}
		return null;
	} 
	
	// For assert
	public static List<CompileToken> tokenizeFactString(String fact){
		ANTLRInputStream stream = new ANTLRInputStream(fact);
		Map<String,List<List<CompileToken>>> map = visitor(stream).getTokens();
		return map.get(map.keySet().iterator().next()).get(0);
	}
	
	/** Create a visitor for a char stream. ANTLR will create a parsetree and then the visitor can convert it into tokens. */
	private static WAMTokenizer visitor(CharStream stream){
		PrologLexer lexer = new PrologLexer(stream); 
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		PrologParser parser = new PrologParser(tokens);
		ParseTree tree = parser.toprule(); 
		WAMTokenizer visitor = new WAMTokenizer(); 
		visitor.p = parser; // For debugging, needed for outputting register assignment
		visitor.visit(tree);	 
		return visitor;
	} 
}
