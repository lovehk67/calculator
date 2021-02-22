package calculator.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import calculator.CalcConstants;
import calculator.ui.CalcControl;

public class MCAction extends AbstractCalcAction {

	public MCAction(CalcControl control) {
		super(control, CalcConstants.MEMORY_CLEAR);
	}
	public void actionPerformed(ActionEvent e) {
		control.clearMemory();
	}
}