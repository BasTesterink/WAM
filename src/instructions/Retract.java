package instructions;

import java.util.HashMap;

import compiler.Compiler;

import runtime.CellAddress;
import runtime.MemoryCell;
import runtime.PrologRuntime;

public class Retract implements Instruction {

	private CellAddress addr = new CellAddress(PrologRuntime.REGISTERS,0,0);
	public void execute(PrologRuntime runtime){  
		String functor = "";
		MemoryCell strCell = runtime.getCell(addr);
		if(strCell.getType() == MemoryCell.CON){
			functor = strCell.getFunctor()+"/0";
		} else if(strCell.getType() == MemoryCell.STR){ 
			MemoryCell fnCell = runtime.getCell(strCell.getPointerDomain(), strCell.getPointerFrame(), strCell.getPointerIndex());
			functor = fnCell.getFunctor()+"/"+fnCell.getArgCount();
		}
		
		StringBuffer r = new StringBuffer();
		runtime.cellToPrologString(addr, r, new HashMap<CellAddress, Integer>());
		runtime.getCodeBase().removeFact(functor, r.toString());
		runtime.increaseP();
	}
	
	public String toString(){ return "retract";	}
}
