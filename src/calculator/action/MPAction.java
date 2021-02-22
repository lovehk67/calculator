package calculator.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import calculator.CalcConstants;
import calculator.ui.CalcControl;

public class MPAction extends AbstractCalcAction {

	public MPAction(CalcControl control) {
		super(control, CalcConstants.MEMORY_PLUS);
	}
	public void actionPerformed(ActionEvent e) {
		control.plusMemory();		
	}
}