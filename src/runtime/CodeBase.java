package runtime;

import instructions.Allocate;
import instructions.Arithmetic;
import instructions.Assert;
import instructions.CallVariable;
import instructions.Cut;
import instructions.Deallocate;
import instructions.GetVariable;
import instructions.Instruction;
import instructions.Proceed;
import instructions.PutValue;
import instructions.Retract;
import instructions.RetryMeElse;
import instructions.TrustMe;
import instructions.TryMe;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import compiler.CompiledClause;
import compiler.tokens.CompileToken;

/**
 * This class contains all the compiled Prolog code that the engine uses to answer queries.
 * @author Bas Testerink
 *
 */
public class CodeBase {
	private Map<String, List<List<CompileToken>>> tokens = new HashMap<String, List<List<CompileToken>>>(); // Contains all the compile tokens of the code, can be used for changing the code base and recompile it
	private Map<String, CodeClause> clauses = new HashMap<String, CodeClause>();
	private Map<String, CodeClause> builtin = new HashMap<String, CodeClause>();
	private CodeClause query = new CodeClause();							// The latest compiled query

	public CodeBase(){ makeBuiltIn(); }

	/** Add all the input tokens to the token based (appending after the current tokens in case of same predicates). */
	public Map<String, List<List<CompileToken>>> mergeTokens(Map<String, List<List<CompileToken>>> input){
		for(String f : input.keySet()){
			List<List<CompileToken>> clauses = tokens.get(f);
			if(clauses == null){
				clauses = new ArrayList<List<CompileToken>>();
				tokens.put(f, clauses);
			}
			clauses.addAll(input.get(f));
		}
		return tokens;
	}

	/** Override the current query. */
	public void setQuery(CompiledClause query){
		this.query.reset();
		this.query.setInstructions(query.getInstructions());
		this.query.setPrologString(query.getPrologString());
	}

	/** Override the current code. */
	public void setCode(Map<String,List<CompiledClause>> code){
		for(String f : code.keySet()){
			boolean initial = true;
			CodeClause clause = null; 
			List<CompiledClause> clauses = code.get(f); 
			for(CompiledClause c : clauses){
				CodeClause newClause = new CodeClause(); 
				newClause.belongsTo = c.getFunctor();
				newClause.setInstructions(c.getInstructions());
				newClause.setPrologString(c.getPrologString()); 
				newClause.setPrevious(clause);
				if (clause != null) clause.setNext(newClause); 
				if(initial){
					this.clauses.put(f, newClause); 
					initial = false;
				}
				clause = newClause;
			}
		}
	}

	/** Remove a fact. Used by retract. It is assumed that the to-be-retracted fact is fully substituted with
	 * the first possible fact. */
	public void removeFact(String functor, String fact){ 
		CodeClause clause = clauses.get(functor); 
		while(!clause.getPrologString().equals(fact)){
			clause = clause.getNext(); 
			if(clause == null) return;
		}
		Instruction i = clause.getInstruction(0);

		if(i instanceof TryMe){
			Instruction i2 = clause.getNext().getInstruction(0);
			if(i2 instanceof RetryMeElse){
				// Remove first of >2 clauses
				clause.getNext().getInstructions().set(0, new TryMe(((TryMe)i).getArity()));
			} else if(i2 instanceof TrustMe){
				// Remove first of 2 clauses
				clause.getNext().getInstructions().remove(0); // Remove the TryMe instruction
			}
			clauses.put(functor, clause.getNext()); // Replace the top-of-list pointer
		} else if(i instanceof RetryMeElse){
			// Remove middle of >2 (do nothing, just remove the clause)
		} else if(i instanceof TrustMe){ 
			Instruction i2 = clause.getPrevious().getInstruction(0);
			if(i2 instanceof RetryMeElse){
				// Remove last of >2 clauses
				clause.getPrevious().getInstructions().set(0, new TrustMe()); // Set retry to trust  
			} else if(i2 instanceof TryMe){
				clause.getPrevious().getInstructions().remove(0); // Remove the try_me instruction
			}
		} else {
			// Removed the last fact
			clauses.remove(functor);
		}
		clause.remove(); // Simply remove the clause
	}
	
	/** Add a code clause (used by assert). */
	public void addCodeClause(CompiledClause c){
		CodeClause newClause = new CodeClause();
		newClause.setInstructions(c.getInstructions());
		newClause.setPrologString(c.getPrologString());
		newClause.belongsTo = c.getFunctor();
		CodeClause clause = clauses.get(c.getFunctor()); 
		if(clause==null){
			// first of its kind
			clauses.put(c.getFunctor(), newClause);
		} else {
			while(clause.getNext()!=null)
				clause = clause.getNext(); // Go to last clause
			Instruction i = clause.getInstruction(0);
			if(i instanceof TrustMe){ // There was already a bunch of facts, so make new trust entry.
				clause.getInstructions().set(0,new RetryMeElse());
				newClause.getInstructions().add(0,new TrustMe());
			} else { // There was only one fact, add tryme and trustme
				int arity = Integer.parseInt(c.getFunctor().substring(c.getFunctor().lastIndexOf('/')+1,c.getFunctor().length()));
				clause.getInstructions().add(0,new TryMe(arity));
				newClause.getInstructions().add(0,new TrustMe());
			}
			clause.setNext(newClause);
			newClause.setPrevious(clause);
		}
	}

	// Setters/getters
	public void setQuery(CodeClause query){ this.query = query; }
	public CodeClause getQueryClause(){ return query; }

	/** Get the top clause for a functor. */
	public CodeClause getClause(String functor){
		CodeClause code = clauses.get(functor); 
		while(code != null && code.isRetracted())
			code = code.getNext();
		if(code == null){
			return builtin.get(functor);
		}
		return code; 
	}

	/** Make the special built-in code. */
	private void makeBuiltIn(){
		// Arithmetic
		CodeClause c = new CodeClause();
		List<Instruction> instr = new ArrayList<Instruction>();
		instr.add(new Arithmetic());
		c.setInstructions(instr);
		c.belongsTo = " is /2";
		builtin.put(" is /2", c); 

		// Retract
		c = new CodeClause();
		instr = new ArrayList<Instruction>();
		instr.add(new TryMe(1));
		instr.add(new Allocate(1));
		instr.add(new GetVariable(-1, 0));
		instr.add(new CallVariable(-1));
		instr.add(new Cut());
		instr.add(new PutValue(-1, 0));
		instr.add(new Retract());
		instr.add(new Deallocate());
		instr.add(new TrustMe());
		instr.add(new Proceed());
		c.setInstructions(instr);
		c.belongsTo = "retract/1";
		builtin.put("retract/1", c);  

		// Assert
		c = new CodeClause();
		instr = new ArrayList<Instruction>();
		instr.add(new Assert());
		c.setInstructions(instr);
		c.belongsTo = "assert/1";
		builtin.put("assert/1", c); 
	}

	public String toString(){ 
		String s = "";
		for(String f : clauses.keySet()){
			s += f+":\r\n";
			CodeClause clause = clauses.get(f);
			int c = 0;
			while(clause != null){ 
				Instruction instruction;
				s += "\t Prolog: " + clause.getPrologString()+"\r\n";
				for(int i = 0; i < clause.size(); i++){
					instruction = clause.getInstruction(i);
					s += "\t" + c + ": "+ instruction + "\r\n"; 
					c++;
				}
				clause = clause.getNext(); 
			}
		}
		s +="Current query: "+query.getPrologString()+"\r\n";
		int c = 0; 
		Instruction instruction;
		for(int i = 0; i < query.size(); i++){
			instruction = query.getInstruction(i);
			s += "\t" + c + ": "+ instruction + "\r\n"; 
			c++;
		}
		return s;
	}

} 