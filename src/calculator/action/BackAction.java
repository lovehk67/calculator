package calculator.action;

import java.awt.event.ActionEvent;

import calculator.CalcConstants;
import calculator.ui.CalcControl;

public class BackAction extends AbstractCalcAction {

	public BackAction(CalcControl control) {
		super(control, CalcConstants.BACKSPACE);
	}
	public void actionPerformed(ActionEvent e) {
		control.backspace();		
	}
}