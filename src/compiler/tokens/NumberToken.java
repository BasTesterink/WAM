package compiler.tokens;

public class NumberToken implements CompileToken {
	private double number; 
	private int argument;
	
	public NumberToken(double number, int argument){
		this.number = number;
		this.argument = argument;
	}
	
	public double getNumber(){ return number; }
	public int getArgument(){ return argument; }
	
	public String toString(){ return "<number " + number + ">"; }
}
