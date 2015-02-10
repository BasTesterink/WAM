package instructions;

import runtime.CellAddress;
import runtime.MemoryCell;
import runtime.PrologRuntime;

public class PutStructure implements Instruction {
	private int register, arity; 
	private String functor;
	
	public PutStructure(int register, String functor, int arity){;
		this.register = register;
		this.functor = functor;
		this.arity = arity;
	}
	
	private CellAddress h = new CellAddress(PrologRuntime.HEAP,0,0);
	public void execute(PrologRuntime runtime){
		h.setIndex(runtime.getH().getIndex()); 
		runtime.getNewHeapCell().convertToFunctorCell(functor, arity);
		runtime.getRegisterCell(register).convertToStructureCell(h); 
		runtime.increaseP();
	} 
	
	public String toString(){ return "put_structure " + functor + "/" + arity + " X" + (register+1); }
}
