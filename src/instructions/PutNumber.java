package instructions;

import runtime.CellAddress;
import runtime.PrologRuntime;

public class PutNumber implements Instruction {
	private int register; 
	private double number;
	
	public PutNumber(int register, double number){
		this.register = register;
		this.number = number; 
	}
	
	//private CellAddress h = new CellAddress(PrologRuntime.HEAP,0,0);
	public void execute(PrologRuntime runtime){
		runtime.getRegisterCell(register).convertToNumberCell(number);
		runtime.increaseP();
	} 
	
	public String toString(){ return "put_number " + number + " " + "X" + (register+1); }
}
