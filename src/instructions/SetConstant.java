package instructions;

import runtime.PrologRuntime;

public class SetConstant implements Instruction { 
	private String name;

	public SetConstant(String name){
		this.name = name;
	}

	public void execute(PrologRuntime runtime){
		runtime.getNewHeapCell().convertToConstantCell(name);
		runtime.increaseP();
	}

	public String toString(){ return "set_constant " + name; }
}