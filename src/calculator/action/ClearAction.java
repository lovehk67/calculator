package calculator.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import calculator.CalcConstants;
import calculator.ui.CalcControl;



// 표시된 숫자를 지운다.
public class ClearAction extends AbstractCalcAction {
	
	public ClearAction(CalcControl control) {
		super(control, CalcConstants.CLEAR);
	}
	public void actionPerformed(ActionEvent e) {
		control.clear();
	}
}