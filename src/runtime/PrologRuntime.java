package runtime;

import instructions.Call;
import instructions.CallVariable;
import instructions.Instruction;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contains all the runtime variables and functionalities for a Prolog instantiation.
 * @author Bas Testerink
 */
public class PrologRuntime {
	public static final int HEAP=0, STACK=1, TRAIL=2, REGISTERS=3;				// Types of memory
	private CodeBase codebase;													// Reference to the compiled code
	private List<MemoryCell>		heap = new ArrayList<MemoryCell>(),  		// Main memory space
									registers = new ArrayList<MemoryCell>(); 	// The argument and X registers
	private List<CellAddress>		trail = new ArrayList<CellAddress>();		// Bound variables
	private CellAddress h = new CellAddress(), hb = new CellAddress();			// Points to the top of the heap currently and at last made choicepoint
	private int tr, b0;															// Trail pointer and choice point at moment of current clause call
	private boolean isInWriteMode = false; 										// Whether program instructions should write on the heap or not
	private boolean failed = false; 											// Query could not be answered with yes. I.e. computer says ``no". 
	private boolean finished = false;											// Whether the engine is finished with a query
	private int s; 																// Points to arguments of a predicate that is being unified
	private FrameStack frameStack = new FrameStack();							// Stack of environments and choice points
	private CodeClause p_clause = null, cp_clause = null; 						// Current code clause
	private int p_index, cp_index; 												// Current index in the clause
	private List<String> trace = new ArrayList<String>();						// For the gui, trace of instructions + some other outputs
	private boolean debugMode = false;											// Whether engine is in debug mode.
	
	/**
	 * 
	 * @param codebase Code base with which this runtime operates. 
	 */
	public PrologRuntime(CodeBase codebase){
		this.codebase = codebase; 
		reset();
	}
	

	/**
	 * First resets the runtime environment and then executes the last compiled query.
	 * @return True if the query was true and false otherwise. 
	 */
	public boolean execute(boolean once){
		while(!isFinished()){
			Instruction i = p_clause.getInstruction(p_index);
			if(debugMode){										// Some debug code
				StringBuffer r = new StringBuffer();
				if(i instanceof Call){
					r.append(": ");
					String functor = ((Call)i).getFunctor();
					r.append(functor.substring(0, functor.indexOf('/')) + "(");
					CellAddress addr = new CellAddress(REGISTERS, 0, 0);
					int argcount = Integer.parseInt(functor.substring(functor.indexOf('/') + 1));
					Map<CellAddress, Integer> vars = new HashMap<CellAddress, Integer>();
					for (int j = 0; j < argcount; j++) {
						addr.setIndex(j);
						cellToPrologString(addr, r, vars);
						if (j < argcount - 1)
							r.append(",");
					}
					r.append(")");
					if(argcount==0) r.setLength(r.length()-2); 	// Remove parenthesis after constants
				} else if(i instanceof CallVariable){
					r.append(": ");
					cellToPrologString(((CallVariable)i).getVariableAddress(this), r, new HashMap<CellAddress,Integer>());
				}
				trace.add("\t"+i+" "+r.toString());
			}
			i.execute(this); 
			if(once) break; 
		} 
		return !failed;
	}
	
	/**
	 * Resets the runtime environment to answer a new query.
	 */
	public void reset(){
		failed = false;
		h.set(HEAP, -1, 0);
		hb.set(HEAP, -1, 0);
		s = 0;  
		tr = 0;
		b0 = 0;
		isInWriteMode = false;
		finished = false;
		p_index = 0;
		cp_index = 0;
		p_clause = codebase.getQueryClause();
		cp_clause = codebase.getQueryClause();
		frameStack.reset();
		trace.clear(); 
	}
	
	/**
	 * Creates or reuses a new heap cell. This will automatically scale the heap if all available cells are in use.
	 * @return The memory cell from the top of the heap that can be considered a new cell.
	 */
	public MemoryCell getNewHeapCell(){
		scale(heap, h.getIndex()); 				// Scale the heap list to the proper size
		MemoryCell result = getCell(h);			// Get the top heap cell
		h.incrementIndex();						// Increase the top of heap pointer
		//result.reset();						// Reset the cell (not necessary for answering queries but helps for the GUI) 
		return result;
	}

	/**
	 * Copy the values of a memory cell into an argument or x register. The register will not point to the instance of
	 * the memory cell but simply copies all the data fields.
	 * @param register
	 * @param value
	 */
	public void setRegister(int register, MemoryCell value){
		scale(registers, register+1); // Scale the registers list to the proper size 
		registers.get(register).copyFrom(value); 
	}
	
	/**
	 * Copy the values of a memory cell into a stack variable from the topmost frame on the stack. 
	 * @param nr Number of the stack variable.
	 * @param value
	 */
	public void setStackVariable(int nr, MemoryCell value){
		frameStack.setVariable(nr, value);
	}
	
	/**
	 * Obtain the cell from a variable on the top most frame on the stack. 
	 * @param nr Number of the stack variable.
	 * @return
	 */
	public MemoryCell getStackVariable(int nr){
		return frameStack.getVariable(nr);
	}
	
	/**
	 * Get the cell from an argument/x register.
	 * @param register
	 * @return
	 */
	public MemoryCell getRegisterCell(int register){
		scale(registers, register+1); // Scale the registers list to the proper size
		return registers.get(register);
	}
	 
	/**
	 * Follow reference cells until either a self-reference occurs or a non-referencing cell is encountered. 
	 * To find the target memory and index where the search ended, use <code>getDerefTarget</code> and 
	 * <code>getDerefPointer</code> right after this method call (or: before the next call to this method). 
	 * @return The memory cell after dereferencing the input.
	 */
	private CellAddress derefresult = new CellAddress();
	public CellAddress deref(int domain, int frame, int index){  
		MemoryCell cell = getCell(domain, frame, index);
		if(cell.getType()==MemoryCell.REF && !(cell.getPointerDomain()==domain && cell.getPointerFrame()==frame && cell.getPointerIndex()==index)) 
			return deref(cell.getPointerDomain(),cell.getPointerFrame(),cell.getPointerIndex());
		else {
			derefresult.set(domain, frame, index);
			return derefresult; 
		}
	}
	
	/**
	 * Binds one variable to another cell. At least one target/pointer pair from the input must point to a reference cell. 
	 * @param target1
	 * @param pointer1
	 * @param target2
	 * @param pointer2
	 */
	public void bind(CellAddress a1, CellAddress a2){
		MemoryCell c1 = getCell(a1);
		MemoryCell c2 = getCell(a2); 
		if(c1.getType()==MemoryCell.REF && (c2.getType()!=MemoryCell.REF || a2.lowerThan(a1))){
			c1.copyFrom(c2);
			trail(a1);
		} else {
			c2.copyFrom(c1);
			trail(a2);
		}
	}
	
	/**
	 * Check whether a cell should be reset if the current choice doesn't pan out.
	 * @param a 
	 */
	public void trail(CellAddress a){ 
		if(a.lowerThan(hb)||(h.lowerThan(a) && a.lowerThan(getB()))){
			if(trail.size() == tr) trail.add(new CellAddress(a.getDomain(),a.getFrame(),a.getIndex()));
			else trail.get(tr).set(a.getDomain(), a.getFrame(), a.getIndex()); 
			tr++;
		}
	}
	
	/** Undo all variable bindings after a certain choicepoint. */
	private void unwindTrail(int a, int b){
		for(int i = a; i < b; i++)
			getCell(trail.get(i)).convertToRefCell(trail.get(i));
	}
	
	/** When performing a cut, determine which variables are now bound permanently. */
	public void tidyTrail(){
		int i = frameStack.peekTopChoicePoint()==null?0:frameStack.peekTopChoicePoint().getTr();
		while(i < tr){
			if(trail.get(i).lowerThan(hb) || (h.lowerThan(trail.get(i)) && trail.get(i).lowerThan(getB()))){
				i++;
			} else {
				trail.set(i, trail.get(tr));
				tr--;
			}
		}
	}
	
	CellAddress a1 = new CellAddress();
	CellAddress a2 = new CellAddress();
	/** Unify two addresses. */
	public boolean unify(int d1, int f1, int i1, int d2, int f2, int i2){ 
		a1.copyFrom(deref(d1, f1, i1));
		a2.copyFrom(deref(d2, f2, i2));
		MemoryCell c1 = getCell(a1.getDomain(),a1.getFrame(),a1.getIndex());
		MemoryCell c2 = getCell(a2.getDomain(),a2.getFrame(),a2.getIndex());
		if(c1.getType()==MemoryCell.REF||c2.getType()==MemoryCell.REF){
			bind(a1, a2);
			return true;
		} else if(c1.getType()==c2.getType()){
			switch(c1.getType()){
			case MemoryCell.CON: return c1.getFunctor().equals(c2.getFunctor());
			case MemoryCell.NUM: return c1.getNumber()==c2.getNumber();
			case MemoryCell.LIS: 
				return unify(c1.getPointerDomain(),c1.getPointerFrame(),c1.getPointerIndex(), c2.getPointerDomain(), c2.getPointerFrame(), c2.getPointerIndex()) && 
					   unify(c1.getPointerDomain(),c1.getPointerFrame(),c1.getPointerIndex()+1, c2.getPointerDomain(), c2.getPointerFrame(), c2.getPointerIndex()+1);
			case MemoryCell.STR: 
				CellAddress fn1 = new CellAddress(c1.getPointerDomain(), c1.getPointerFrame(), c1.getPointerIndex()); // Go to FN cell.
				c1 = getCell(fn1.getDomain(),fn1.getFrame(),fn1.getIndex());
				CellAddress fn2 = new CellAddress(c2.getPointerDomain(), c2.getPointerFrame(), c2.getPointerIndex()); // Go to FN cell.
				c2 = getCell(fn2.getDomain(),fn2.getFrame(),fn2.getIndex());
				if(c1.getFunctor().equals(c2.getFunctor()) && c1.getArgCount()==c2.getArgCount()){
					for(int i = 1; i <= c1.getArgCount(); i++)
						if(!unify(fn1.getDomain(), fn1.getFrame(), fn1.getIndex()+i, fn2.getDomain(), fn2.getFrame(), fn2.getIndex()+i))
							return false;
					return true;
				} else return false; 
			default: return false;
			}
		} else return false;
	}
	
	/** Scales a memory space to a target size. */
	private void scale(List<MemoryCell> list, int targetSize){
		for(int i = list.size(); i <= targetSize; i++){
			MemoryCell m = new MemoryCell();
			m.reset();
			list.add(m);
		}
	}
	
	/**
	 * Create a new environment on the stack. 
	 * @param permVarCount
	 */
	public void newEnvironment(int permVarCount){
		frameStack.newEnvironment().convertToEnvironment(cp_index, cp_clause, permVarCount, b0);
	}
	
	public void newChoicePoint(int arity){ 
		frameStack.newChoicePoint().convertToChoicePoint(cp_index, cp_clause, p_clause, getE(), tr, h.getIndex(), b0, arity, this);
	}
	
	public void setB0AsCurrentChoicePoint(){
		//if(frameStack.getEnvironmentTop()>frameStack.getChoicePointTop())
		frameStack.setChoicePointTop(frameStack.peekTopEnvironment().getB0()); // TODO: I still have to thoroughly test this
		//else frameStack.setChoicePointTop(b0);
	}

	/**
	 * Pop the top environment on the stack and restore the values that are stored in it.
	 */
	public void popEnvironment(){
		Frame f = frameStack.popTopEnvironment();
		p_index = f.getCPIndex();
		p_clause = f.getCPClause();
	}
	
	/**
	 * Pop the top choice point on the stack and restore the values that are stored in it.
	 */
	public void retryChoicePoint(){
		Frame f = frameStack.peekTopChoicePoint();
		for(int i = 0; i < f.getVariableCount(); i++)
			setRegister(i, f.getVar(i));
		frameStack.setEnvironmentTop(f.getE());
		cp_clause = f.getCPClause();
		cp_index = f.getCPIndex();
		f.setCurrentClause(p_clause); // replaces STACK[B+n+4] <- L
		unwindTrail(f.getTr(), tr);
		tr = f.getTr();
		h.setIndex(f.getH()); 
		b0 = f.getB0();
	}	
	
	/**
	 * Pop the top frame on the stack and restore the values that are stored in it.
	 */
	public void trustChoicePoint(){
		Frame f = frameStack.popTopChoicePoint();
		for(int i = 0; i < f.getVariableCount(); i++)
			setRegister(i, f.getVar(i));
		frameStack.setEnvironmentTop(f.getE());
		cp_clause = f.getCPClause();
		cp_index = f.getCPIndex();
		unwindTrail(f.getTr(),tr);
		tr = f.getTr();
		h.setIndex(f.getH()); 
		b0 = f.getB0();
	}
	
	/**
	 * Go to the latest choicepoint or fail.
	 */
	public void backtrack(){
		if(debugMode) trace.add("Backtracking...");
		Frame choicepoint = frameStack.peekTopChoicePoint();
		if(choicepoint == null){
			fail(); 
			return;
		}
		p_index = 0;
		p_clause = choicepoint.getCurrentClause().getNext();
		cp_clause = choicepoint.getCPClause();
		cp_index = choicepoint.getCPIndex(); 
		if(p_clause == null){ 
			frameStack.popTopChoicePoint(); // This can only happen after backtracking on something you just retracted.
			backtrack();
		}
	}
	
	// Getters and setters and related methods
	public CodeBase getCodeBase(){ return codebase; }
	public List<MemoryCell> getHeap(){ return heap; } 
	public List<MemoryCell> getRegisters(){ return registers; } 
	public CellAddress getH(){ return h; }
	public boolean isInWriteMode(){ return isInWriteMode; }
	public boolean setWriteMode(boolean b){ return isInWriteMode = b; }
	public void fail(){ failed = true;}
	public boolean hasFailed(){ return failed; }
	public int getS(){ return s; }
	public void setS(int s){ this.s = s; }
	public void increaseS(){ s++; }
	public void increaseP(){ p_index++; }
	public void moveToContinuationInstruction(){ p_index = cp_index; p_clause = cp_clause; }
	public MemoryCell getHeapCell(int index){ return heap.get(index); }
	public boolean isFinished(){ return failed || finished; }
	public int getE(){ return frameStack.getEnvironmentTop(); }
	private static CellAddress b_address = new CellAddress();
	public CellAddress getB(){ b_address.set(STACK,frameStack.getChoicePointTop(),0); return b_address; } 
	public void setHBtoH(){ hb.setIndex(h.getIndex()); } 
	public void setB0(){ b0 = frameStack.getChoicePointTop(); }
	public String getStackString(){ return frameStack.toString(); }
	public void activateDebugMode(){ debugMode = true; }
	public List<String> getTrace(){ return trace; }
	public void setCPToNextInstruction(){
		cp_index = p_index + 1;
		cp_clause = p_clause; 
	}
	
	/** Move execution to the first clause of a certain functor. */
	public void goToClause(String functor){
		p_clause = codebase.getClause(functor);
		b0 = frameStack.getChoicePointTop();
		if (p_clause == null)
			backtrack();
		else
			p_index = 0;
	}
	public void setFinished(boolean b){ finished = b; }

	/** Obtain a memory cell. */
	public MemoryCell getCell(CellAddress address){ 
		switch(address.getDomain()){
		case HEAP: return heap.get(address.getIndex());
		case REGISTERS: return registers.get(address.getIndex());
		case STACK: return frameStack.getFrame(address.getFrame()).getVariables().get(address.getIndex());
		}
		return null;
	}

	/** Obtain a memory cell. */
	public MemoryCell getCell(int domain, int frame, int index){ 
		switch(domain){
		case HEAP: return heap.get(index);
		case REGISTERS: return registers.get(index);
		case STACK: return frameStack.getFrame(frame).getVariables().get(index);
		}
		return null;
	}
	
	/** Obtain a memory area. */
	public List<MemoryCell> getTarget(int target){
		switch(target){
		case HEAP: return heap;
		case REGISTERS: return registers;
		case STACK: return frameStack.getVariables(); 
		}
		return null;
	}
	
	// From here on toStrings
	
	public static String targetToString(int target){
		switch(target){
		case HEAP: return "heap";
		case REGISTERS: return "registers";
		case STACK: return "stack";
		case TRAIL: return "trail";
		}
		return "null";
	}
	
	public String toString(){
		String r = "";
		r += "Code base:\r\n"+codebase;
		r += "Heap: \r\n";
		int c = 0; 
		for(MemoryCell m : heap)
			if(m.getType()!=-1){
				r += "\t"+c+": "+m+"\r\n";
				c++;
			}
		r += "Registers:\r\n";
		c = 0;
		for(MemoryCell m : registers)
			if(m.getType()!=-1){
				r += "\t"+c+": "+m+"\r\n";
				c++;
			}
		return r;
	}
	
	public String getHeapString(){
		StringBuffer r = new StringBuffer();
		r.append("Heap: \r\n");
		int c = 0; 
		for(MemoryCell m : heap)
			if(m.getType()!=-1){
				r.append("\t"+c+": "+m+"\r\n");
				c++;
			}
		return r.toString();
	}
	
	public String getRegistersString(){
		StringBuffer r = new StringBuffer();
		r.append("Registers: "+registers.size()+"\r\n");
		int c = 1; 
		for(MemoryCell m : registers){
				r.append("\t"+c+": "+m+"\r\n");
				c++; 
		}
		return r.toString();
	}
	
	public String getTraceString(){
		StringBuffer r = new StringBuffer();
		r.append("Trace:\r\n"); 
		r.append("Next instruction: "+(p_clause==null?"none":p_clause.getInstruction(p_index))+"\r\n");
		for(int i = trace.size()-1; i >= 0; i--){
			r.append(trace.get(i)+"\r\n");
		}
		return r.toString();
	}
	
	public String getRuntimeVariablesString(){
		StringBuffer r = new StringBuffer();
		r.append("Runtime variables:\r\n");
		r.append("h:\t\t"+h.getIndex()+"\r\n");
		r.append("hb:\t\t"+hb.getIndex()+"\r\n");
		r.append("tr:\t\t"+tr+"\r\n");
		r.append("b0:\t\t"+b0+"\r\n");
		r.append("write:\t"+isInWriteMode+"\r\n");
		r.append("failed:\t"+failed+"\r\n");
		r.append("s:\t\t"+s+"\r\n");
		String c = p_clause==null?"null":(p_clause.belongsTo==null?"query":p_clause.belongsTo);
		r.append("p:\t\t"+p_index+" (clause from: "+c+")\r\n");
		c = cp_clause.belongsTo==null?"query":cp_clause.belongsTo;
		r.append("cp:\t\t"+cp_index+" (clause from: "+c+")\r\n"); 
		return r.toString(); 
	}
	
	public StringBuffer cellToPrologString(CellAddress a, StringBuffer r, Map<CellAddress, Integer> vars){
		CellAddress d = deref(a.getDomain(),a.getFrame(),a.getIndex());
		CellAddress addr = new CellAddress(d.getDomain(), d.getFrame(), d.getIndex());
		MemoryCell m = getCell(addr);
		switch(m.getType()){
		case MemoryCell.STR:
			addr = new CellAddress(m.getPointerDomain(),m.getPointerFrame(), m.getPointerIndex());
			m = getCell(addr);
			r.append(m.getFunctor()+"(");
			for(int i = 1; i <= m.getArgCount(); i++){
				addr.setIndex(addr.getIndex()+1);
				cellToPrologString(addr, r, vars);
				if(i<m.getArgCount()) r.append(",");
			}
			r.append(")");
			break;
		case MemoryCell.LIS:
			CellAddress child1 = new CellAddress(m.getPointerDomain(),m.getPointerFrame(),m.getPointerIndex());
			CellAddress child2 = new CellAddress(m.getPointerDomain(),m.getPointerFrame(),m.getPointerIndex()+1);
			r.append("[");
			cellToPrologString(child1, r, vars);
			r.append("|");
			cellToPrologString(child2, r, vars);
			r.append("]");
			break; 
		case MemoryCell.REF: 
			int nr = vars.size(); 
			for(CellAddress a2 : vars.keySet()){ 
				if(a2.equals(addr)){
					nr = vars.get(a2);
					break;
				}
			}
			if(nr == vars.size())
				vars.put(new CellAddress(addr.getDomain(),addr.getFrame(),addr.getIndex()), nr);
			r.append("_V"+nr); 
			break; 
		case MemoryCell.CON: r.append(m.getFunctor()); break;
		case MemoryCell.NUM: r.append(m.getNumber()); break;
		} 
		return r;
	} 
	
	public Map<String,String> outputQueryResult(Map<String, Integer> qvars){
		Map<String, String> bindings = new HashMap<String, String>();
		StringBuffer r = new StringBuffer();
		Map<CellAddress, Integer> vars = new HashMap<CellAddress, Integer>();
		for(String s : qvars.keySet()){ 
			bindings.put(s, cellToPrologString(new CellAddress(STACK,0,qvars.get(s)), r, vars).toString());
			r = new StringBuffer();
		}
		//for(String s : bindings.keySet()) System.out.println(s+" = "+bindings.get(s));
		return bindings;
	}
}