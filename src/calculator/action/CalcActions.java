package calculator.action;

import java.awt.event.KeyEvent;

import javax.swing.ActionMap;
import javax.swing.ComponentInputMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;

import calculator.CalcConstants;
import calculator.ui.CalcControl;

public class CalcActions{

	private ActionMap actionMap = new ActionMap();
	
	public CalcActions(CalcControl control) {
		
		// panel1
		actionMap.put(CalcConstants.MEMORY_CLEAR, new MCAction(control));
		actionMap.put(CalcConstants.MEMORY_READ, new MRAction(control));
		actionMap.put(CalcConstants.MEMORY_SAVE, new MSAction(control));
		actionMap.put(CalcConstants.MEMORY_PLUS, new MPAction(control));

		// panel2
		actionMap.put(CalcConstants.BACKSPACE, new BackAction(control));
		actionMap.put(CalcConstants.CLEAR, new ClearAction(control));
		actionMap.put(CalcConstants.RESULT, new ResultAction(control));
	
		// panel3
		actionMap.put(CalcConstants.NUMBER_ZERO, new NumAndOperAction(CalcConstants.NUMBER_ZERO, control));
		actionMap.put(CalcConstants.NUMBER_ONE, new NumAndOperAction(CalcConstants.NUMBER_ONE, control));
		actionMap.put(CalcConstants.NUMBER_TWO, new NumAndOperAction(CalcConstants.NUMBER_TWO, control));
		actionMap.put(CalcConstants.NUMBER_THREE, new NumAndOperAction(CalcConstants.NUMBER_THREE, control));
		actionMap.put(CalcConstants.NUMBER_FOUR, new NumAndOperAction(CalcConstants.NUMBER_FOUR, control));
		actionMap.put(CalcConstants.NUMBER_FIVE, new NumAndOperAction(CalcConstants.NUMBER_FIVE, control));
		actionMap.put(CalcConstants.NUMBER_SIX, new NumAndOperAction(CalcConstants.NUMBER_SIX, control));
		actionMap.put(CalcConstants.NUMBER_SEVEN, new NumAndOperAction(CalcConstants.NUMBER_SEVEN, control));
		actionMap.put(CalcConstants.NUMBER_EIGHT, new NumAndOperAction(CalcConstants.NUMBER_EIGHT, control));
		actionMap.put(CalcConstants.NUMBER_NINE, new NumAndOperAction(CalcConstants.NUMBER_NINE, control));
		actionMap.put(CalcConstants.NUMBER_POINT, new NumAndOperAction(CalcConstants.NUMBER_POINT, control));
		actionMap.put(CalcConstants.OPERATOR_PLUS, new NumAndOperAction(CalcConstants.OPERATOR_PLUS, control));
		actionMap.put(CalcConstants.OPERATOR_MINUS, new NumAndOperAction(CalcConstants.OPERATOR_MINUS, control));
		actionMap.put(CalcConstants.OPERATOR_MUL, new NumAndOperAction(CalcConstants.OPERATOR_MUL, control));
		actionMap.put(CalcConstants.OPERATOR_DIV, new NumAndOperAction(CalcConstants.OPERATOR_DIV, control));
		actionMap.put(CalcConstants.SIGN, new SignAction(control));
	}
	
	public ActionMap getActionMap() {
		return actionMap;
	}
}