package calculator.action;

import java.awt.event.KeyEvent;

import javax.swing.ComponentInputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import calculator.CalcConstants;

public class CalcInputs {

	private ComponentInputMap inputMap;
	
	public CalcInputs(JComponent comp){
		inputMap = new ComponentInputMap(comp);
		
		// panel1
		inputMap.put(KeyStroke.getKeyStroke('c'), CalcConstants.MEMORY_CLEAR);
		inputMap.put(KeyStroke.getKeyStroke('r'), CalcConstants.MEMORY_READ);
		inputMap.put(KeyStroke.getKeyStroke('s'), CalcConstants.MEMORY_SAVE);
		inputMap.put(KeyStroke.getKeyStroke('p'), CalcConstants.MEMORY_PLUS);
	
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.SHIFT_MASK), CalcConstants.MEMORY_CLEAR);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.SHIFT_MASK), CalcConstants.MEMORY_READ);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.SHIFT_MASK), CalcConstants.MEMORY_SAVE);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.SHIFT_MASK), CalcConstants.MEMORY_PLUS);
	
		// panel2
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), CalcConstants.BACKSPACE );
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), CalcConstants.CLEAR );
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), CalcConstants.RESULT );
		
		// panel3
		inputMap.put(KeyStroke.getKeyStroke('0'), CalcConstants.NUMBER_ZERO);
		inputMap.put(KeyStroke.getKeyStroke('1'), CalcConstants.NUMBER_ONE);
		inputMap.put(KeyStroke.getKeyStroke('2'), CalcConstants.NUMBER_TWO);
		inputMap.put(KeyStroke.getKeyStroke('3'), CalcConstants.NUMBER_THREE);
		inputMap.put(KeyStroke.getKeyStroke('4'), CalcConstants.NUMBER_FOUR);
		inputMap.put(KeyStroke.getKeyStroke('5'), CalcConstants.NUMBER_FIVE);
		inputMap.put(KeyStroke.getKeyStroke('6'), CalcConstants.NUMBER_SIX);
		inputMap.put(KeyStroke.getKeyStroke('7'), CalcConstants.NUMBER_SEVEN);
		inputMap.put(KeyStroke.getKeyStroke('8'), CalcConstants.NUMBER_EIGHT);
		inputMap.put(KeyStroke.getKeyStroke('9'), CalcConstants.NUMBER_NINE);
		inputMap.put(KeyStroke.getKeyStroke('.'), CalcConstants.NUMBER_POINT);
		inputMap.put(KeyStroke.getKeyStroke('+'), CalcConstants.OPERATOR_PLUS);
		inputMap.put(KeyStroke.getKeyStroke('-'), CalcConstants.OPERATOR_MINUS);
		inputMap.put(KeyStroke.getKeyStroke('*'), CalcConstants.OPERATOR_MUL);
		inputMap.put(KeyStroke.getKeyStroke('/'), CalcConstants.OPERATOR_DIV);

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0), CalcConstants.SIGN);		
	}
	
	public ComponentInputMap getInputMap() {
		return inputMap;
	}
}
