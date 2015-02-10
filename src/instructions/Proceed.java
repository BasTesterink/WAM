package instructions;

import runtime.PrologRuntime;

public class Proceed implements Instruction {

	public void execute(PrologRuntime runtime) { 
		runtime.moveToContinuationInstruction();
	}
	
	public String toString(){ return "proceed";	}
}
