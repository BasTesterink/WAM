package instructions;

import runtime.PrologRuntime;

public class Cut implements Instruction {

	public void execute(PrologRuntime runtime) { 
		// The choice point at the moment of the call of the current procedure becomes the top choicepoint
		// B0 is this choice point
		runtime.setB0AsCurrentChoicePoint();
		runtime.tidyTrail();
		runtime.increaseP();
	}
	
	public String toString(){ return "cut";	}
}