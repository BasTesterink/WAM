package runtime; 
import instructions.Fail;
import instructions.Instruction;

import java.util.ArrayList;
import java.util.List;

public class CodeClause {
	private CodeClause next = null, previous = null;
	private boolean retracted = false;
	private List<Instruction> instructions = new ArrayList<Instruction>();
	private static final Instruction fail = new Fail();		// Special fail instruction when a predicate cannot be found
	private String prologString = "";
	
	public void reset(){ next = null; retracted = false; instructions.clear(); previous = null; }
	public void setNext(CodeClause next){ this.next = next; }
	public void setPrevious(CodeClause previous){ this.previous = previous; }
	public CodeClause getNext(){
		if(next==null) return null;
		if(next.isRetracted()) return next.getNext();
		return next;
	}
	public CodeClause getPrevious(){
		if(previous==null) return null;
		if(previous.isRetracted()) return previous.getPrevious();
		return previous;
	}
	public void setRetracted(boolean b){ this.retracted = b; }
	public boolean isRetracted(){ return retracted; }
	public void setInstructions(List<Instruction> instructions){ this.instructions = instructions; }
	public List<Instruction> getInstructions(){ return instructions; }
	public Instruction getInstruction(int index){ return index>=instructions.size()? fail :  instructions.get(index); }
	public void setPrologString(String s){ prologString = s; }
	public String getPrologString(){ return prologString; }
	public int size(){ return instructions.size(); }
	public void remove(){
		if(previous != null) previous.setNext(next); 
		if(next != null) next.setPrevious(previous);  
		reset(); 
	}
	
	public String belongsTo; // for debugging
	public String toString(){
		int c = 0;
		String s = "\t "+prologString+"\r\n";
		for(Instruction i : instructions){
			s += "\t " + c + ": " + i +"\r\n";
			c++;
		}
		return s;
	}
}
