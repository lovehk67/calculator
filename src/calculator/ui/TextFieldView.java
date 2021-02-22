package calculator.ui;

import java.awt.Color;

import javax.swing.JTextField;

import calculator.CalcConstants;
import calculator.event.CalcEvent;
import calculator.event.CalcListener;
import calculator.model.Calculator;

public class TextFieldView extends JTextField implements CalcListener {
	
	public TextFieldView() {
		this.setHorizontalAlignment(JTextField.RIGHT);
		this.setFocusable(false);
		init();
	}
	
	private void init() {
		this.setForeground(Color.LIGHT_GRAY);
		this.setText("수식을 입력해 주세요");
	}
	
	@Override
	public void stateChanged(CalcEvent event) {

		Calculator calc = (Calculator)event.getSource();
		String exp = calc.getExpression();
		
		switch( event.getEventType() ) {
		case RESULT:
			// 연산 결과를 포맷해서 보여준다. "수식=결과" 형태로 보여준다.
			StringBuffer result = new StringBuffer();
			result.append(exp + CalcConstants.RESULT + calc.getResult());
			int length = result.length();
			if( result.lastIndexOf(".0") == length-2 )
				result.delete(length-2, length);
			
			this.setText( result.toString() );
			break;
		// 수식을 string 형식으로 보여준다.
		case EXPRESSION:

			if( "".equals(exp) ) {
				init();
			}
			else {
				this.setForeground(Color.black);
				this.setText(exp);
			}
			break;
		}
	}
}
