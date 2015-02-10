package instructions;

import runtime.PrologRuntime;

public class Call implements Instruction {
	private String functor; 
	
	public Call(String functor, int arity){
		this.functor = functor+"/"+arity; 
	}
	
	public void execute(PrologRuntime runtime){
		runtime.setCPToNextInstruction();
		runtime.setB0();
		runtime.goToClause(functor);
	} 
	
	public String toString(){ return "call " + functor; }
	public String getFunctor(){ return functor; }
}
