package compiler.tokens;

public class StringRepresentationToken implements CompileToken {
	private String value;
	
	public String getValue(){ return value; }
	public StringRepresentationToken(String value){ this.value = value; }	
	
	public String toString(){
		return "<String representation: " + value + ">";
	}
}