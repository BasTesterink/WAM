package instructions;

import runtime.PrologRuntime;

public class EndOfQuery implements Instruction {
	
	public void execute(PrologRuntime runtime) { 
		runtime.setFinished(true);
	}
	
	public String toString(){ return "End of Query";	}
}