package calculator.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import calculator.CalcConstants;
import calculator.ui.CalcControl;


public class MRAction extends AbstractCalcAction {
	public MRAction(CalcControl control) {
		super(control, CalcConstants.MEMORY_READ);
	}
	public void actionPerformed(ActionEvent e) {
		control.readMemory();			
	}
}