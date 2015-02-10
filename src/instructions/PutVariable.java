package instructions;

import runtime.CellAddress;
import runtime.MemoryCell;
import runtime.PrologRuntime;
import compiler.Compiler;
import compiler.WAMTokenizer;

public class PutVariable implements Instruction {
	private int primeRegister, argumentRegister;
	
	public PutVariable(int primeRegister, int argumentRegister){
		this.primeRegister = primeRegister;
		perm.setIndex(WAMTokenizer.primeRegisterToStackIndex(primeRegister));
		this.argumentRegister = argumentRegister;
	}
	
	private CellAddress h = new CellAddress(PrologRuntime.HEAP,0,0);
	private CellAddress perm = new CellAddress(PrologRuntime.STACK,0,0);
	public void execute(PrologRuntime runtime){
		MemoryCell m;
		if(primeRegister < 0){
			perm.setFrame(runtime.getE());
			m = runtime.getCell(perm);
			m.convertToRefCell(perm);
		} else {
			h.setIndex(runtime.getH().getIndex());
			m = runtime.getNewHeapCell();
			m.convertToRefCell(h);
			runtime.setRegister(primeRegister, m);
		}
		runtime.setRegister(argumentRegister, m);
		runtime.increaseP();
	}

	public String toString(){ return "put_variable " + WAMTokenizer.varRegisterToString(primeRegister) + " A" + (argumentRegister+1); }
}