package instructions;

import runtime.CellAddress;
import runtime.MemoryCell;
import runtime.PrologRuntime;

public class UnifyConstant implements Instruction {
	private CellAddress s = new CellAddress(PrologRuntime.HEAP, 0, 0);

	private String name;
	public UnifyConstant(String name){
		this.name = name;
	}

	public void execute(PrologRuntime runtime){
		boolean fail = false;
		if(runtime.isInWriteMode()){
			runtime.getNewHeapCell().convertToConstantCell(name);
		} else {
			CellAddress d = runtime.deref(PrologRuntime.HEAP, 0, runtime.getS());
			MemoryCell m = runtime.getCell(d);
			if(m.getType() == MemoryCell.REF){
				m.convertToConstantCell(name);
				runtime.trail(d);
			} else if(m.getType() != MemoryCell.CON || !m.getFunctor().equals(name)) {
				fail = true;
			}
			if(!fail) runtime.increaseS();
		}
		if(fail) runtime.backtrack();
		else runtime.increaseP();
	}

	public String toString(){ return "unify_constant " + name; }
}