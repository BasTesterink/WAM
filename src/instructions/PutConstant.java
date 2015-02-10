package instructions;

import runtime.CellAddress;
import runtime.PrologRuntime;

public class PutConstant implements Instruction {
	private int register; 
	private String name;
	
	public PutConstant(int register, String name){
		this.register = register;
		this.name = name; 
	}
	
	public void execute(PrologRuntime runtime){
		runtime.getRegisterCell(register).convertToConstantCell(name);  
		runtime.increaseP();
	} 
	
	public String toString(){ return "put_constant " + name + " " + "A" + (register+1); }
}