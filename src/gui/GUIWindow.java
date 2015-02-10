package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.StyledTextContent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
/** Auto generated. */
public class GUIWindow {
	private StyledText styledText_5;
	private StyledText styledText_3;
	private StyledText styledText_6;
	private StyledText styledText_4;
	private StyledText styledText;
	private StyledText styledText_1;
	private StyledText styledText_2;
	private Button btnNext;
	private Button btnPrevious;
	private Button btnExecuteAll;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GUIWindow window = new GUIWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(1023, 1000);
		shell.setText("SWT Application");
		
		styledText = new StyledText(shell, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		styledText.setLeftMargin(5);
		styledText.setFont(SWTResourceManager.getFont("Consolas", 10, SWT.NORMAL));
		styledText.setText("Runtime Variables:\r\n");
		styledText.setBounds(0, 0, 306, 215);
		
		styledText_1 = new StyledText(shell, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		styledText_1.setLeftMargin(5);
		styledText_1.setFont(SWTResourceManager.getFont("Consolas", 10, SWT.NORMAL));
		styledText_1.setText("Registers:");
		styledText_1.setBounds(0, 221, 306, 215);
		
		styledText_2 = new StyledText(shell, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		styledText_2.setLeftMargin(5);
		styledText_2.setFont(SWTResourceManager.getFont("Consolas", 10, SWT.NORMAL));
		styledText_2.setText("Heap:");
		styledText_2.setBounds(0, 442, 306, 531);
		
		styledText_3 = new StyledText(shell, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		styledText_3.setLeftMargin(5);
		styledText_3.setFont(SWTResourceManager.getFont("Consolas", 10, SWT.NORMAL));
		styledText_3.setText("Stack:");
		styledText_3.setBounds(312, 0, 306, 791);
		
		styledText_4 = new StyledText(shell, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		styledText_4.setLeftMargin(5);
		styledText_4.setFont(SWTResourceManager.getFont("Consolas", 10, SWT.NORMAL));
		styledText_4.setText("Trail:");
		styledText_4.setBounds(312, 797, 306, 176);
		
		styledText_5 = new StyledText(shell, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		styledText_5.setLeftMargin(5);
		styledText_5.setFont(SWTResourceManager.getFont("Consolas", 10, SWT.NORMAL));
		styledText_5.setText("Code:\r\n");
		styledText_5.setBounds(624, 0, 391, 791);
		
		styledText_6 = new StyledText(shell, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		styledText_6.setLeftMargin(5);
		styledText_6.setFont(SWTResourceManager.getFont("Consolas", 10, SWT.NORMAL));
		styledText_6.setText("Trace:");
		styledText_6.setBounds(624, 797, 391, 148); 
		
		btnPrevious = new Button(shell, SWT.NONE);
		btnPrevious.setBounds(799, 950, 68, 23);
		btnPrevious.setText("Previous");
		
		btnExecuteAll = new Button(shell, SWT.NONE);
		btnExecuteAll.setBounds(947, 950, 68, 23);
		btnExecuteAll.setText("Execute All");
		
		btnNext = new Button(shell, SWT.NONE);
		btnNext.setBounds(873, 950, 68, 23);
		btnNext.setText("Next");

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	 
	
	public StyledTextContent getCodeText() {
		return styledText_5.getContent();
	}
	public StyledTextContent getStackText() {
		return styledText_3.getContent();
	}
	public StyledTextContent getTraceText() {
		return styledText_6.getContent();
	}
	public StyledTextContent getTrailText() {
		return styledText_4.getContent();
	}
	public StyledTextContent getRuntimeVariablesText() {
		return styledText.getContent();
	}
	public StyledTextContent getRegistersText() {
		return styledText_1.getContent();
	}
	public StyledTextContent getHeapText() {
		return styledText_2.getContent();
	}
	public Button getBtnNext() {
		return btnNext;
	}
	public Button getBtnPrevious() {
		return btnPrevious;
	}
	public Button getBtnExecuteAll() {
		return btnExecuteAll;
	}
}
