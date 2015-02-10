package instructions;

import runtime.PrologRuntime;
import compiler.Compiler;
import compiler.WAMTokenizer;

public class PutValue implements Instruction {
	private int primeRegister, argumentRegister;
	
	public PutValue(int primeRegister, int argumentRegister){
		this.primeRegister = primeRegister;
		this.argumentRegister = argumentRegister;
	}
	
	public void execute(PrologRuntime runtime){
		if(primeRegister < 0){
			runtime.setRegister(argumentRegister, runtime.getStackVariable(WAMTokenizer.primeRegisterToStackIndex(primeRegister)));
		} else runtime.setRegister(argumentRegister, runtime.getRegisterCell(primeRegister));
		runtime.increaseP();
	}

	public String toString(){ return "put_value " + WAMTokenizer.varRegisterToString(primeRegister) + " A" + (argumentRegister+1); }
}
