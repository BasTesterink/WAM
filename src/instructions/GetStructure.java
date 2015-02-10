package instructions;

import runtime.CellAddress;
import runtime.MemoryCell;
import runtime.PrologRuntime;

public class GetStructure implements Instruction {
	private int arity; 
	private String functor;
	private int register;
	
	public GetStructure(int register, String functor, int arity){;
		this.register = register;
		this.functor = functor;
		this.arity = arity;
	}
	
	private static CellAddress h = new CellAddress(PrologRuntime.HEAP,-1,0);
	public void execute(PrologRuntime runtime){
		CellAddress a = runtime.deref(PrologRuntime.REGISTERS,-1,register);
		MemoryCell m = runtime.getCell(a);
		boolean fail = false;
		if(m.getType()==MemoryCell.REF){
			h.setIndex(runtime.getH().getIndex());
			runtime.getNewHeapCell().convertToStructureCell(runtime.getH());
			runtime.getNewHeapCell().convertToFunctorCell(functor, arity);
			runtime.bind(a, h);
			runtime.setWriteMode(true);
		} else if(m.getType()==MemoryCell.STR){
			a.set(m.getPointerDomain(), m.getPointerFrame(), m.getPointerIndex());
			m = runtime.getCell(a);
			if(m.getType()==MemoryCell.FN && m.getFunctor().equals(functor) && m.getArgCount()==arity){
				runtime.setS(a.getIndex()+1);
				runtime.setWriteMode(false);
			} else fail = true;
		} else fail = true;
		if(fail) runtime.backtrack();
		else runtime.increaseP();
	} 
	
	public String toString(){ return "get_structure " + functor + "/" + arity + " X" + (register+1); }
}
