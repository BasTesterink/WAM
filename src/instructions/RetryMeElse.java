package instructions;

import runtime.PrologRuntime;

public class RetryMeElse implements Instruction {
	 
	public void execute(PrologRuntime runtime){ 
		runtime.retryChoicePoint();
		runtime.setHBtoH();
		runtime.increaseP();
	}
	
	public String toString(){ return "retry_me";	}

}