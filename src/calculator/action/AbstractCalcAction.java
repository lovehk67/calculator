package calculator.action;

import javax.swing.AbstractAction;

import calculator.ui.CalcControl;

public abstract class AbstractCalcAction extends AbstractAction {
	protected CalcControl control;
	
	protected AbstractCalcAction(CalcControl control, String actionName){
		super(actionName);
		this.control = control;
	}
}
