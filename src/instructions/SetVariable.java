package instructions;

import runtime.CellAddress;
import runtime.MemoryCell;
import runtime.PrologRuntime;
import compiler.Compiler;
import compiler.WAMTokenizer;

public class SetVariable implements Instruction {
	private int register; 
	
	public SetVariable(int register){
		this.register = register; 
	}
	
	private CellAddress h = new CellAddress(PrologRuntime.HEAP, 0, 0);
	public void execute(PrologRuntime runtime) {
		h.setIndex(runtime.getH().getIndex());
		MemoryCell refCell = runtime.getNewHeapCell();
		refCell.convertToRefCell(h); // refer to itself
		if(register < 0){
			runtime.setStackVariable(WAMTokenizer.primeRegisterToStackIndex(register), refCell);
		} else runtime.setRegister(register, refCell);
		runtime.increaseP();
	}
	
	public String toString(){ return "set_variable " + WAMTokenizer.varRegisterToString(register); }
}
