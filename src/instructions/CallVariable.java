package instructions;

import runtime.CellAddress;
import runtime.MemoryCell;
import runtime.PrologRuntime;
import compiler.Compiler;
import compiler.WAMTokenizer;

public class CallVariable implements Instruction {
	private int primeRegister;
	
	public CallVariable(int primeRegister){
		this.primeRegister = primeRegister; 
	}
	
	private CellAddress address = new CellAddress();
	public void execute(PrologRuntime runtime){ 
		if(primeRegister < 0){
			address.copyFrom(runtime.deref(PrologRuntime.STACK, runtime.getE(), WAMTokenizer.primeRegisterToStackIndex(primeRegister))); 
		} else {
			address.copyFrom(runtime.deref(PrologRuntime.REGISTERS, 0, primeRegister)); 
		}
		MemoryCell strCell = runtime.getCell(address);
		if(strCell.getType() == MemoryCell.CON){
			runtime.setCPToNextInstruction();
			runtime.goToClause(strCell.getFunctor()+"/0"); 
		} else if(strCell.getType() == MemoryCell.STR){
			runtime.setCPToNextInstruction();
			address.set(strCell.getPointerDomain(), strCell.getPointerFrame(), strCell.getPointerIndex()); // go to FN cell
			MemoryCell fnCell = runtime.getCell(address);
			for(int i = 0; i < fnCell.getArgCount(); i++){
				runtime.setRegister(i, runtime.getCell(address.getDomain(),address.getFrame(),address.getIndex()+1+i));
			}
			runtime.goToClause(fnCell.getFunctor()+"/"+fnCell.getArgCount()); 
		} else runtime.backtrack();
	}

	public String toString(){ return "call " + WAMTokenizer.varRegisterToString(primeRegister); }
	public CellAddress getVariableAddress(PrologRuntime runtime){
		if(primeRegister < 0){
			return (runtime.deref(PrologRuntime.STACK, runtime.getE(), WAMTokenizer.primeRegisterToStackIndex(primeRegister))); 
		} else {
			return (runtime.deref(PrologRuntime.REGISTERS, 0, primeRegister)); 
		}
	}
}