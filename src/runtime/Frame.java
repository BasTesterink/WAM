package runtime;

import java.util.ArrayList;
import java.util.List;

/**
 * Frames can  be environments and choice points. As they can be reused, we utilize conversion methods to convert a 
 * frame into an environment or choice point. 
 * @author Bas Testerink
 *
 */

public class Frame {
	public static final int ENVIRONMENT=0, CHOICEPOINT=1; 						// Possible frame types
	private int type;															// Type: environment/choicepoint
	private int cp_index, tr, h, e, b0; 										// Note: e is only used for choicepoints, in environments this is the previous field
	private CodeClause cp_clause, currentClause;								// The continuation clause and the clause at the moment of the frame creation
	private List<MemoryCell> variables = new ArrayList<MemoryCell>();			// Container for variables (note: is recycled so not each memory slot is an actual variable)
	private int previous;														// The previous frame of its kind
	private int variableCount;													// Amount of stored variables
	
	/** Converts the frame to an environment */
	public void convertToEnvironment(int cp_index, CodeClause cp_clause, int variableCount, int b0){
		type = ENVIRONMENT;
		this.cp_index = cp_index;
		this.cp_clause = cp_clause;
		this.b0 = b0;
		while(variableCount > variables.size()){
			variables.add(new MemoryCell());
		}
	}
	
	/** Converts the frame to a choice point */
	public void convertToChoicePoint(int cp_index, CodeClause cp_clause, CodeClause currentClause, int e, int tr, int h, int b0, int variableCount, PrologRuntime runtime){
		type = CHOICEPOINT;
		this.e = e;
		this.cp_index = cp_index;
		this.cp_clause = cp_clause;
		this.variableCount = variableCount;
		while(variableCount > variables.size()) variables.add(new MemoryCell());
		for(int i = 0; i < variableCount; i++)
			variables.get(i).copyFrom(runtime.getRegisterCell(i));
		this.tr = tr;
		this.h = h;
		this.b0 = b0;
		this.currentClause = currentClause; // Functions as L
	}
	
	// Getters/setters
	public int getCPIndex(){ return cp_index; }
	public CodeClause getCPClause(){ return cp_clause; }
	public MemoryCell getVar(int nr){ return variables.get(nr); }
	public List<MemoryCell> getVariables(){ return variables; }
	public CodeClause getCurrentClause(){ return currentClause; }
	public void setPrevious(int previous){ this.previous = previous; }
	public int getPrevious(){ return previous; }
	public int getH(){ return h; }
	public int getE(){ return e; }
	public int getTr(){ return tr; } 
	public int getB0(){ return b0; } 
	public int getVariableCount(){ return variableCount; }
	public int getType(){ return type; }
	public void setCurrentClause(CodeClause c){ this.currentClause = c; }
 
	public String toString(){
		StringBuffer b = new StringBuffer();
		b.append("\t"+"Type:\t"+(type==ENVIRONMENT?"environment":"choice point")+"\r\n");
		b.append("\t"+"cp:\t\t"+cp_index+" (from clause "+(cp_clause.belongsTo==null?"query":cp_clause.belongsTo)+")\r\n");
		b.append("\t"+"b0:\t\t"+b0+"\r\n");
		if(type==CHOICEPOINT){
			b.append("\t"+"e:\t\t"+e+"\r\n");
			b.append("\t"+"h:\t\t"+h+"\r\n");
			b.append("\t"+"tr:\t\t"+tr+"\r\n");
			CodeClause c = currentClause.getNext();
			String s;
			if(c==null) s = "none";
			else s = c.belongsTo+" instruction 0 = "+c.getInstruction(0);
			b.append("\t"+"Next clause: "+s+"\r\n");
		}
		b.append("\t"+"Variables("+variableCount+"):\r\n");
		for(int i = 0; i < variables.size(); i++){
			b.append("\t\t"+i+":"+variables.get(i)+"\r\n");
		}
		return b.toString();
	}
}
