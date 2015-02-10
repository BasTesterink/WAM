package instructions;

import runtime.PrologRuntime;

public interface Instruction {
	/** See the WAM tutorial for explanation on the execute functionality of each instruction. */
	public void execute(PrologRuntime runtime);
}
