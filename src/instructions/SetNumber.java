package instructions;

import runtime.CellAddress;
import runtime.PrologRuntime;

public class SetNumber implements Instruction {

	private double number;
	public SetNumber(double number){
		this.number = number;
	} 
	public void execute(PrologRuntime runtime){
		runtime.getNewHeapCell().convertToNumberCell(number);
		runtime.increaseP();
	}

	public String toString(){ return "set_number " + number; }
}
