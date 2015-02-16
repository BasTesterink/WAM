package main; 

import java.util.Map;

 
public class OOPrologMain { 
	private Prolog prolog = new Prolog(); 
	
	public static void main(String[] args){
		OOPrologMain m = new OOPrologMain();
		m.test2();
	}

	public void test2(){
		prolog.activateDebugMode();
		prolog.loadFile("./resources/input2.txt"); 
		prolog.setQuery("?- q(A,B)."); 
	}
	
	public void test1(){ 
		prolog.loadFile("./resources/input2.txt"); 
		System.out.println(prolog.query("?- f(Y),not(c(Y)), findall((X,Z),f(X),L).")); 
		Map<String, String> result = prolog.obtainVariableBindings();
		for(String s : result.keySet()) System.out.println(s +" = "+result.get(s));
		prolog.loadFile("./resources/einstein.txt"); 
		System.out.println(prolog.query("?- einstein(X,Y)."));
		result = prolog.obtainVariableBindings();
		for(String s : result.keySet()) System.out.println(s +" = "+result.get(s));
	}
	
	public void test7(){
		prolog.loadFile("./resources/einstein.txt"); 
		System.out.println(prolog.query("?- einstein(X,Y)."));  
		int amount = 10;
		
		long c = System.currentTimeMillis();
		for(int i = 0; i < amount; i++){
			prolog.redoQuery();
			if(i%100==0) System.out.println((i/((double)amount)*100));
		}
		System.out.println((System.currentTimeMillis()-c)/((double)amount));
	}
	
	
}
