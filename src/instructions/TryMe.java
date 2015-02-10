package instructions;

import runtime.PrologRuntime;

public class TryMe implements Instruction {
	private int arity;
	
	public TryMe(int arity){
		this.arity = arity; 
	}
	
	public void execute(PrologRuntime runtime){
		runtime.newChoicePoint(arity);
		runtime.setHBtoH();
		runtime.increaseP();
	}
	
	public int getArity(){ return arity; }
	public String toString(){ return "try_me " + arity;	}
 
}
