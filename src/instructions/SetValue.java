package instructions;

import compiler.Compiler;
import compiler.WAMTokenizer;
import runtime.PrologRuntime;

public class SetValue implements Instruction {
	private int register; 
	
	public SetValue(int register){
		this.register = register; 
	}
	
	public void execute(PrologRuntime runtime) {
		if(register < 0){
			runtime.getNewHeapCell().copyFrom(runtime.getStackVariable(WAMTokenizer.primeRegisterToStackIndex(register)));
		} else runtime.getNewHeapCell().copyFrom(runtime.getRegisterCell(register));
		runtime.increaseP();
	}
	
	public String toString(){ return "set_value " + WAMTokenizer.varRegisterToString(register); }
}
