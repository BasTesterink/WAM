package instructions;

import runtime.CellAddress;
import runtime.MemoryCell;
import runtime.PrologRuntime;

public class GetConstant implements Instruction {
	private int register; 
	private String name;

	public GetConstant(int register, String name){
		this.register = register;
		this.name = name; 
	}


	public void execute(PrologRuntime runtime){
		CellAddress d = runtime.deref(PrologRuntime.REGISTERS, 0, register);
		MemoryCell m = runtime.getCell(d);
		boolean fail = false;
		if(m.getType() == MemoryCell.REF){
			m.convertToConstantCell(name);
			runtime.trail(d);
		} else if(m.getType() != MemoryCell.CON || !m.getFunctor().equals(name)) {
			fail = true;
		}
		if(fail) runtime.backtrack();
		else runtime.increaseP();
	} 

	public String toString(){ return "get_constant " + name + " " + "X" + (register+1); }
}