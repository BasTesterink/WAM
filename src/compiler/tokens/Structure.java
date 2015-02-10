package compiler.tokens;

public class Structure implements CompileToken {
	private int register;
	private String functor;
	private int arity;
	
	public Structure(int register, String functor, int arity){
		this.register = register;
		this.functor = functor;
		this.arity = arity;
	}
	
	public int getRegister(){ return register; }
	public int getArity(){ return arity; }
	public String getFunctor(){ return functor; }
	
	public String toString(){ return "<X" + (register+1) + " = " + functor + "/" + arity + ">"; }
}
