package instructions;

import runtime.CellAddress;
import runtime.MemoryCell;
import runtime.PrologRuntime;

public class GetList implements Instruction {
	private int register;
	
	public GetList(int register){
		this.register = register; 
	}
	
	private CellAddress h = new CellAddress(PrologRuntime.HEAP,0,0);
	public void execute(PrologRuntime runtime){
		CellAddress d = runtime.deref(PrologRuntime.REGISTERS, 0, register);
		MemoryCell m = runtime.getCell(d);
		if(m.getType() == MemoryCell.REF){
			h.setIndex(runtime.getH().getIndex()+1);
			runtime.getNewHeapCell().convertToListCell(h);
			h.setIndex(h.getIndex()-1);
			runtime.bind(d, h);
			runtime.setWriteMode(true);
			runtime.increaseP();
		} else if(m.getType() == MemoryCell.LIS){
			runtime.setS(m.getPointerIndex());
			runtime.setWriteMode(false);
			runtime.increaseP();
		} else runtime.backtrack();
	} 
	
	public String toString(){ return "get_list " + "X" + (register+1); }
}