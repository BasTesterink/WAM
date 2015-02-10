package instructions;

import compiler.Compiler;
import compiler.WAMTokenizer;
import runtime.PrologRuntime;

public class GetVariable implements Instruction {
	private int primeRegister, argumentRegister;
	
	public GetVariable(int primeRegister, int argumentRegister){
		this.primeRegister = primeRegister;
		this.argumentRegister = argumentRegister;
	}
	
	public void execute(PrologRuntime runtime){
		if(primeRegister < 0){
			runtime.setStackVariable(WAMTokenizer.primeRegisterToStackIndex(primeRegister), runtime.getRegisterCell(argumentRegister));
		} else runtime.setRegister(primeRegister, runtime.getRegisterCell(argumentRegister));
		runtime.increaseP();
	}

	public String toString(){ return "get_variable " + WAMTokenizer.varRegisterToString(primeRegister) + " A" + (argumentRegister+1); }
}
