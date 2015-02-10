package compiler;

import instructions.Instruction;

import java.util.List;
/**
 * This class is the result of the compilation process. It contains WAM instructions, a string representation of the code and the functor/arity. 
 * This class can be removed in the future as <code>CodeClause</code> instances can also be used instead of CompiledClause. 
 * 
 * @author Bas Testerink
 */
public class CompiledClause {
	private String prologString;
	private String functor;
	private List<Instruction> instructions;
	
	public CompiledClause(String prologString, String functor, List<Instruction> instructions){
		this.prologString = prologString;
		this.functor = functor;
		this.instructions = instructions;
	}
	
	public String getPrologString(){ return prologString; }
	public String getFunctor(){ return functor; }
	public List<Instruction> getInstructions(){ return instructions; }
	
	public String toString(){
		StringBuffer r = new StringBuffer();
		r.append(functor+"\r\n");
		r.append(prologString+"\r\n");
		r.append(instructions+"\r\n");
		return r.toString();
	}
}
