package gui;


import java.util.ArrayList;

import main.Prolog; 
import org.eclipse.swt.custom.StyledTextContent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Display; 
/**
 * I really hate this GUI but haven't found the time yet to create a better one. 
 * The main issue is that SWT concurrency exceptions sometimes occur without me 
 * being able to reproduce them. 
 *  
 * The idea is that after each instruction a snapshot (in the form of Strings) is 
 * made of the WAM. These snapshots can be stepped through in the GUI
 * . 
 * @author Bas Testerink
 */
public class GUI implements Runnable, MouseListener{
	public static final int CODE=0, HEAP=1, TRAIL=2, RUNTIMEVARS=3, TRACE=4, STACK=5, REGISTERS=6;
	private GUIWindow gui = new GUIWindow();				// The actual window
	private Prolog prolog;									// The Prolog engine 
	private ArrayList<String>[] data = new ArrayList[7];	// The stored data after each instruction
	private int cursor = -1;								// The current cursor in the data

	public GUI(Prolog prolog){ 
		this.prolog = prolog;
		for(int i = 0; i < 7; i++) data[i] = new ArrayList<String>();
	}

	public void run(){
		me = this;
		gui.open();
	}

	public GUIWindow getGUI(){ return gui; }

	/**
	 * Update a text field.
	 * @param textfield Either GUI.CODE, HEAP, TRAIL, RUNTIMEVARS, TRACE, STACK or REGISTERS.
	 * @param inputtext The text to put in the text field. 
	 */
	public void updateText(int textfield, String inputtext){  
		if(textfield < data.length){
			data[textfield].add(inputtext);
			cursor = data[textfield].size()-1; 
		}
	} 

	/**
	 * Shows the texts in the GUI from the current cursor position. 
	 */
	public void showTexts(){
		for(int i = 0; i < 7; i++){
			StyledTextContent field = null;
			switch(i){
			case CODE: field = gui.getCodeText(); break;
			case HEAP: field = gui.getHeapText(); break;
			case TRAIL: field = gui.getTrailText(); break;
			case RUNTIMEVARS: field = gui.getRuntimeVariablesText(); break;
			case TRACE: field = gui.getTraceText(); break;
			case STACK: field = gui.getStackText(); break;
			case REGISTERS: field = gui.getRegistersText(); break;
			} 
			field.setText(data[i].get(cursor)); 
		}
	}
 
	private GUI me; // Needed for registering the mouse.
	/** Make sure the GUI is listening to the mouse. */
	public void registerMouse(){
		try{Thread.sleep(50);}catch(Exception e){ e.printStackTrace(); }
		new Thread(new Runnable() {
			public void run() {
				Display.getDefault().syncExec(new Runnable() {
					public void run() {
						gui.getBtnNext().addMouseListener(me);
						gui.getBtnExecuteAll().addMouseListener(me);
						gui.getBtnPrevious().addMouseListener(me);
						prolog.updateGUI();
						showTexts();
					}
				});
			}
		}).start();
	}

	public void mouseDoubleClick(MouseEvent e) {}
	public void mouseDown(MouseEvent e) {}
	public void mouseUp(MouseEvent e) { 
		String name = e.getSource().toString();
		if(name.contains("Next")){ 
			next();									// Execute next instruction
		} else if(name.contains("Previous")){
			cursor = cursor==1?1:(cursor-1);
			showTexts();
		} else if(name.contains("Execute All")){
			while(!next());							// Keep executing next instruction until the prolog engine is finished
		} 
	}
	
	private boolean next(){
		if(cursor == data[0].size()-1 && !prolog.isFinished()){ // If the engine isn't finished yet
			prolog.executeSingleStep(); 						// Then execute a single instruction
			prolog.updateGUI();									
		} else if(cursor < data[0].size()-1) {					// Otherwise just step forward in the data
			cursor++;
		} 
		showTexts(); 
		return prolog.isFinished();
	}
}
