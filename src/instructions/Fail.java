package instructions;

import runtime.PrologRuntime;

public class Fail implements Instruction {
	
	public void execute(PrologRuntime runtime){ 
		runtime.fail();
	}
	
	public String toString(){ return "fail"; }
}
