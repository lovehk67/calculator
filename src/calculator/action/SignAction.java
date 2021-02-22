package calculator.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import calculator.CalcConstants;
import calculator.ui.CalcControl;


public class SignAction extends AbstractCalcAction {
	public SignAction(CalcControl control) {
		super(control, CalcConstants.SIGN);
	}
	public void actionPerformed(ActionEvent e) {
		control.changeSign();
	}
}