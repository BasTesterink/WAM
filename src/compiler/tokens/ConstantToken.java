package compiler.tokens;

public class ConstantToken implements CompileToken {
	private int argument;
	private String name;
	
	public ConstantToken(String name, int argument){
		this.name = name;
		this.argument = argument;
	}
	
	public String getName(){ return name; }
	public int getArgument(){ return argument; }
	
	public String toString(){ return "<constant " + name + ">"; }
}

