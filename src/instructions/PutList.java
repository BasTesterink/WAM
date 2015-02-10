package instructions;
 
import runtime.PrologRuntime;

public class PutList implements Instruction {
	private int register;  
	
	public PutList(int register){
		this.register = register; 
	}
	
	public void execute(PrologRuntime runtime){
		runtime.getRegisterCell(register).convertToListCell(runtime.getH());
		runtime.increaseP();
	} 
	
	public String toString(){ return "put_list " + "X" + (register+1); }
}