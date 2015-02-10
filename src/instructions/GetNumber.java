package instructions;

import runtime.CellAddress;
import runtime.MemoryCell;
import runtime.PrologRuntime;

public class GetNumber implements Instruction {
	private int register; 
	private double number;
	
	public GetNumber(int register, double number){
		this.register = register;
		this.number = number; 
	}
	
	public void execute(PrologRuntime runtime){
		CellAddress d = runtime.deref(PrologRuntime.REGISTERS, 0, register);
		MemoryCell m = runtime.getCell(d);
		boolean fail = false;
		if(m.getType() == MemoryCell.REF){
			m.convertToNumberCell(number);
			runtime.trail(d);
		} else if(m.getType() != MemoryCell.NUM || m.getNumber() != number) {
			fail = true;
		}
		if(fail) runtime.backtrack();
		else runtime.increaseP();
	} 
	
	public String toString(){ return "get_number " + number + " " + "X" + (register+1); }
}
