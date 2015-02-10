package instructions;

import runtime.PrologRuntime;

public class Deallocate implements Instruction {

	public void execute(PrologRuntime runtime) { 
		runtime.popEnvironment();
	}
	
	public String toString(){ return "deallocate";	}
}
