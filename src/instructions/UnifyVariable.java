package instructions;

import runtime.CellAddress;
import runtime.MemoryCell;
import runtime.PrologRuntime;
import compiler.Compiler;
import compiler.WAMTokenizer;

public class UnifyVariable implements Instruction {
	private int register; 
	
	public UnifyVariable(int register){
		this.register = register; 
	}
	
	private CellAddress h = new CellAddress(PrologRuntime.HEAP, 0, 0);
	public void execute(PrologRuntime runtime) {
		MemoryCell m;
		if(runtime.isInWriteMode()){
			h.setIndex(runtime.getH().getIndex());
			m = runtime.getNewHeapCell();
			m.convertToRefCell(h);
		} else m = runtime.getHeapCell(runtime.getS());
		if(register<0) runtime.setStackVariable(WAMTokenizer.primeRegisterToStackIndex(register), m);
		else runtime.setRegister(register, m);
		runtime.increaseS();
		runtime.increaseP();
	}
	
	public String toString(){ return "unify_variable " + WAMTokenizer.varRegisterToString(register); }
}