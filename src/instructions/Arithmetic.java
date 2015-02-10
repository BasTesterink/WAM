package instructions;

import runtime.CellAddress;
import runtime.MemoryCell;
import runtime.PrologRuntime;

public class Arithmetic implements Instruction {
	private boolean fail;
	
	public void execute(PrologRuntime runtime){ 
		fail = false;
		double number = evaluate(runtime, PrologRuntime.REGISTERS,0,1); // Evaluate second argument
		if(!fail) bindNumber(runtime,number); 
		else runtime.backtrack();
	}
	 
	private double evaluate(PrologRuntime runtime, int domain, int frame, int index){
		CellAddress d = new CellAddress(); // TODO: do different perhaps for speed?
		d.copyFrom(runtime.deref(domain, frame, index));
		MemoryCell m = runtime.getCell(d); 
		double r = 1;
		if(m.getType() == MemoryCell.NUM){
			return m.getNumber();
		} else if(m.getType() == MemoryCell.STR){
			// Go to functor cell
			d.set(m.getPointerDomain(), m.getPointerFrame(), m.getPointerIndex());
			m = runtime.getCell(d);
			String functor = m.getFunctor();
			if(functor.equals("+")){
				return evaluate(runtime,d.getDomain(),d.getFrame(),d.getIndex()+1)+
						evaluate(runtime,d.getDomain(),d.getFrame(),d.getIndex()+2);
			} else if(functor.equals("*")){
				return evaluate(runtime,d.getDomain(),d.getFrame(),d.getIndex()+1)*
						evaluate(runtime,d.getDomain(),d.getFrame(),d.getIndex()+2);
			}  else if(functor.equals("-")){
				return evaluate(runtime,d.getDomain(),d.getFrame(),d.getIndex()+1)-
						evaluate(runtime,d.getDomain(),d.getFrame(),d.getIndex()+2);
			}   else if(functor.equals("/")){
				return evaluate(runtime,d.getDomain(),d.getFrame(),d.getIndex()+1)/
						evaluate(runtime,d.getDomain(),d.getFrame(),d.getIndex()+2);
			} else if(functor.equals("^")){
				return Math.pow(evaluate(runtime,d.getDomain(),d.getFrame(),d.getIndex()+1),
						evaluate(runtime,d.getDomain(),d.getFrame(),d.getIndex()+2));
			} else if(functor.equals("")){ // Parenthesized formula
				return evaluate(runtime,d.getDomain(),d.getFrame(),d.getIndex()+1);
			} else { 
				fail = true;
			}
		} else fail = true; 
		return r;
	}
	
	private void bindNumber(PrologRuntime runtime, double number){ 
		CellAddress d = runtime.deref(PrologRuntime.REGISTERS, 0, 0); // Get the first argument
		MemoryCell m = runtime.getCell(d);
		boolean fail = false;
		if(m.getType() == MemoryCell.REF){ // If the first argument is a variable then bind the result to it
			m.convertToNumberCell(number);
			runtime.trail(d);
		} else if(m.getType() != MemoryCell.NUM || m.getNumber() != number) {
			fail = true;
		}
		if(fail) runtime.backtrack();
		else runtime.moveToContinuationInstruction();
	}

	public String toString(){return "arithmetic";}
}
