package instructions;

import runtime.CellAddress;
import runtime.PrologRuntime;
import compiler.Compiler;
import compiler.WAMTokenizer;

public class GetValue implements Instruction {
	private CellAddress primeRegister = new CellAddress(), 
						argumentRegister = new CellAddress(PrologRuntime.REGISTERS,0,0);
	
	public GetValue(int primeRegister, int argumentRegister){
		if(primeRegister < 0){
			this.primeRegister.set(PrologRuntime.STACK,0,WAMTokenizer.primeRegisterToStackIndex(primeRegister));
		} else this.primeRegister.set(PrologRuntime.REGISTERS, 0, primeRegister);
		this.argumentRegister.setIndex(argumentRegister);
	}
	
	public void execute(PrologRuntime runtime){
		if(primeRegister.getDomain() == PrologRuntime.STACK) // Refers to stack so update with latest environment
			this.primeRegister.setFrame(runtime.getE());
		if(runtime.unify(primeRegister.getDomain(),primeRegister.getFrame(),primeRegister.getIndex(), 
				         argumentRegister.getDomain(),argumentRegister.getFrame(),argumentRegister.getIndex())) 
			runtime.increaseP();
		else runtime.backtrack();
	}

	public String toString(){ return "get_value " + WAMTokenizer.varRegisterToString(primeRegister.getIndex()) + " " + "A"+(argumentRegister.getIndex()+1); }
}
