package calculator.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import calculator.CalcConstants;
import calculator.ui.CalcControl;


public class ResultAction extends AbstractCalcAction {
	public ResultAction(CalcControl control) {
		super(control, CalcConstants.RESULT);
	}
	public void actionPerformed(ActionEvent e) {
		control.result();
	}
}
