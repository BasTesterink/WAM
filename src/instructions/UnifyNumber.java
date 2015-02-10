package instructions;

import runtime.CellAddress;
import runtime.MemoryCell;
import runtime.PrologRuntime;

import compiler.Compiler;

public class UnifyNumber implements Instruction {
	private double number;
	
	public UnifyNumber(double number){
		this.number = number;
	}

	public void execute(PrologRuntime runtime){
		boolean fail = false;
		if(runtime.isInWriteMode()){
			runtime.getNewHeapCell().convertToNumberCell(number);
		} else { 
			CellAddress d = runtime.deref(PrologRuntime.HEAP,0,runtime.getS());
			MemoryCell m = runtime.getCell(d);
			if(m.getType() == MemoryCell.REF){
				m.convertToNumberCell(number);
				runtime.trail(d);
			} else if(m.getType() != MemoryCell.NUM || m.getNumber() != number) {
				fail = true;
			}
			if(!fail)runtime.increaseS();
		}
		if(fail) runtime.backtrack();
		else runtime.increaseP();
	}

	public String toString(){ return "unify_number " + number; }
}