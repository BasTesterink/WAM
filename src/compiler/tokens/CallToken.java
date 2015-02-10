package compiler.tokens;

public class CallToken implements CompileToken { 
	private String functor;
	private int arity;
	
	public CallToken(String functor, int arity){
		this.functor = functor;
		this.arity = arity;
	}
	
	public int getArity(){ return arity; }
	public String getFunctor(){ return functor; }
	
	public String toString(){ return "<call " + functor + "/" + arity + ">"; }
}
