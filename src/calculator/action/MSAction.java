package calculator.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import calculator.CalcConstants;
import calculator.ui.CalcControl;

public class MSAction extends AbstractCalcAction {
	public MSAction(CalcControl control) {
		super(control, CalcConstants.MEMORY_SAVE);
	}
	public void actionPerformed(ActionEvent e) {
		control.saveMemory();	
	}
}