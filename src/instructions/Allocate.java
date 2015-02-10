package instructions;

import runtime.PrologRuntime;

public class Allocate implements Instruction {
	private int nrOfVariables;
	
	public Allocate(int nrOfVariables){
		this.nrOfVariables = nrOfVariables;
	}

	public void execute(PrologRuntime runtime) { 
		runtime.newEnvironment(nrOfVariables); 
		runtime.increaseP();
	}
	
	public String toString(){ return "allocate "+nrOfVariables;	}
 
}
