package calculator.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import calculator.CalcConstants;
import calculator.ui.CalcControl;



// ǥ�õ� ���ڸ� �����.
public class ClearAction extends AbstractCalcAction {
	
	public ClearAction(CalcControl control) {
		super(control, CalcConstants.CLEAR);
	}
	public void actionPerformed(ActionEvent e) {
		control.clear();
	}
}