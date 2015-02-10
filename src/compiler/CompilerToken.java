package compiler;
/**
 * Compiler token streams are used by the compiler to convert Prolog into WAM instructions.
 * This class is currently not used but in the future all token classes will be replaced by this one.
 * The purpose is that then compiler tokens can be reused for better assert/retract operations at runtime. 
 * @author Bas Testerink
 */

public class CompilerToken {
	// All the token types:
	public static final int 
		ALLOCATE=0, ARGUMENTVARIABLE=1, CALL=2, CALLVARIABLE=3, CONSTANT=4, CUT=5, DEALLOCATE=6,
		ENDOFHEAD=7, LIST=8, NUMBER=9, PROCEED=10, STRINGREPRESENTATION=11, STRUCTURE=12, SUBTERMREGISTER=13;
	
	// Attributes
	private int type, i1, i2;
	private String str;
	private double num;
	
	public CompilerToken(int type, int i1, int i2, String str, double num){
		set(type, i1, i2, str, num);
	}
	
	/**
	 * Setter for the whole token. 
	 */
	public void set(int type, int i1, int i2, String str, double num){
		this.type = type;
		this.i1 = i1;
		this.i2 = i2;
		this.str = str;
		this.num = num;
	}
	
	// Getters/setters
	public int getType(){ return type;} 	public void setType(int type){ this.type = type; }
	public int getI1(){ return i1; }		public void setI1(int i){ i1 = i; }
	public int getI2(){ return i2; }		public void setI2(int i){ i2 = i; }
	public String getStr(){ return str; }   public void setStr(String str){ this.str = str; }
	public double getNum(){ return num; }	public void setNum(double num){ this.num = num; }
	
	public String toString(){
		StringBuffer r = new StringBuffer();
		r.append("<");
		switch(type){
		case ALLOCATE: r.append("allocate "+i1); break;
		case ARGUMENTVARIABLE: r.append("A"+(i2+1)+" = "+WAMTokenizer.varRegisterToString(i1)+" = "+str); break;
		case CALL: r.append("call "+str+"/"+i1); break;
		case CALLVARIABLE: r.append("call "+WAMTokenizer.varRegisterToString(i1)); break;
		case CONSTANT: r.append("constant "+str); break;
		case CUT: r.append("cut"); break;
		case DEALLOCATE: r.append("deallocate"); break;
		case ENDOFHEAD: r.append("End of Head"); break;
		case LIST: r.append("list X"+(i1+1)); break;
		case NUMBER: r.append("num "+num); break;
		case PROCEED: r.append("proceed"); break;
		case STRINGREPRESENTATION: r.append("String representation = "+str); break;
		case STRUCTURE: r.append("X"+(i1+1)+" = "+str+"/"+i2); break;
		case SUBTERMREGISTER: r.append(""+WAMTokenizer.varRegisterToString(i1)); break;
		}
		r.append(">");
		return r.toString();
	}
}
