package main;


import gui.GUI;

import java.util.Map;

import compiler.Compiler;
import runtime.CodeBase;
import runtime.PrologRuntime;

/**
 * Main class to use when utilizing this WAM implementation for Prolog. Using this class
 * one can load files, ask queries and look inside the WAM using a GUI. 
 * @author Bas Testerink
 *
 */
public class Prolog {
	private CodeBase code = new CodeBase();						// Contains all the compiled Prolog code and the loaded query
	private PrologRuntime runtime = new PrologRuntime(code);	// Contains the data for answering a query
	private GUI gui;
	private boolean debugMode = false;
	private Map<String, Integer> lastQueryVariables; 			// The variables of the last query, used to obtain bindings after a query
	
	public Prolog(){ 
		gui = new GUI(this);
		loadFile("./resources/builtinlib");
	}
	
	/**
	 * Adds a file to the code base of the engine. 
	 * @param filePath
	 */
	public void loadFile(String filePath){
		Compiler.compileFile(filePath, code);
	}
	
	/**
	 * Ask a query to the Prolog engine. 
	 * @param query The query should be formatted as: "?- goal1, goal2, ..., goalK.".
	 * @return True if the query could be answered successfully, false otherwise.
	 */
	public boolean query(String query){
		runtime.reset();
		lastQueryVariables = Compiler.compileQuery(query, code);
		return runtime.execute(false);
	}
	 
	/**
	 * Obtain the bindings of the last query.
	 * @return Null if the query failed, otherwise a map with (variable name, prolog string) pairs. 
	 */
	public Map<String, String> obtainVariableBindings(){
		if(runtime.hasFailed()) return null;
		else return runtime.outputQueryResult(lastQueryVariables);
	}
	
	public boolean redoQuery(){
		runtime.reset();
		return runtime.execute(false);
	}
	
	public void setQuery(String query){
		Compiler.compileQuery(query, code);
		if(debugMode) updateGUI();
	}
	
	public void executeSingleStep(){
		runtime.execute(true);
	}
	
	/**
	 * Launches a GUI for the WAM, can be used for debugging etc.
	 */
	public void launchGUI(){
		Thread t = new Thread(gui);
		t.start();
		gui.registerMouse();
	}
	  
	public void activateDebugMode(){
		debugMode = true;
		runtime.activateDebugMode();
		launchGUI();
	}
	
	public void updateGUI(){  
		gui.updateText(GUI.CODE, code.toString());
		gui.updateText(GUI.HEAP, runtime.getHeapString());
		gui.updateText(GUI.REGISTERS, runtime.getRegistersString());
		gui.updateText(GUI.TRACE, runtime.getTraceString());
		gui.updateText(GUI.RUNTIMEVARS, runtime.getRuntimeVariablesString());
		gui.updateText(GUI.STACK, runtime.getStackString());
		gui.updateText(GUI.TRAIL, "Trail:\r\n");
	}
	
	public boolean isFinished(){return runtime.isFinished(); }
}