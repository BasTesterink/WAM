package compiler.tokens;

public class AllocateToken implements CompileToken {
	private int nrOfVariables;
	
	public AllocateToken(int nrOfVariables){
		this.nrOfVariables = nrOfVariables;
	}
	
	public int getNrOfVariables(){ return nrOfVariables; }
		
	public String toString(){ return "<allocate>"; }
}
