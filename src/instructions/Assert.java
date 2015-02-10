package instructions;

import java.util.HashMap;

import compiler.Compiler;

import runtime.CellAddress;
import runtime.MemoryCell;
import runtime.PrologRuntime;

public class Assert implements Instruction {
	private CellAddress addr = new CellAddress(PrologRuntime.REGISTERS,0,0);
	public void execute(PrologRuntime runtime){  
		MemoryCell strCell = runtime.getCell(addr);
		if(strCell.getType()!=MemoryCell.CON && strCell.getType()!=MemoryCell.STR)
			runtime.backtrack();
		else { 
			StringBuffer r = new StringBuffer();
			runtime.cellToPrologString(addr, r, new HashMap<CellAddress, Integer>());
			r.append(".");
			String functor = "";
			if(strCell.getType() == MemoryCell.CON){
				functor = strCell.getFunctor()+"/0";
			} else if(strCell.getType() == MemoryCell.STR){ 
				MemoryCell fnCell = runtime.getCell(strCell.getPointerDomain(), strCell.getPointerFrame(), strCell.getPointerIndex());
				functor = fnCell.getFunctor()+"/"+fnCell.getArgCount();
			}
			runtime.getCodeBase().addCodeClause(Compiler.compileStringFact(functor, r.toString()));
			runtime.moveToContinuationInstruction(); // continue
		}
		
	}
	
	public String toString(){ return "assert";	}
}