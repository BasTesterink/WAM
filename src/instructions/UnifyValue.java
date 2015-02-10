package instructions;

import runtime.CellAddress;
import runtime.PrologRuntime;
import compiler.Compiler;
import compiler.WAMTokenizer;

public class UnifyValue implements Instruction {
	private CellAddress register = new CellAddress();
	
	public UnifyValue(int register){
		if(register < 0)
			this.register.set(PrologRuntime.STACK,0,WAMTokenizer.primeRegisterToStackIndex(register));
		else this.register.set(PrologRuntime.REGISTERS, 0, register);
	}
	
	public void execute(PrologRuntime runtime) {
		if(register.getDomain()==PrologRuntime.STACK) register.setFrame(runtime.getE());
		boolean fail = false;
		if(runtime.isInWriteMode())
			runtime.getNewHeapCell().copyFrom(runtime.getCell(register));
		else fail = !runtime.unify(register.getDomain(),register.getFrame(),register.getIndex(),PrologRuntime.HEAP,0,runtime.getS());
		runtime.increaseS();
		if(fail) runtime.backtrack();
		else runtime.increaseP();
	}
	
	public String toString(){ return "unify_value " + (register); }
}