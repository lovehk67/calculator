package calculator.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import calculator.ui.CalcControl;


public class NumAndOperAction extends AbstractCalcAction {
	public NumAndOperAction(String cmd, CalcControl control) {
		super(control, cmd);
	}
	public void actionPerformed(ActionEvent e) {
		control.addExpression(e.getActionCommand());
	}
}