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
		this.setText("������ �Է��� �ּ���");
	}
	
	@Override
	public void stateChanged(CalcEvent event) {

		Calculator calc = (Calculator)event.getSource();
		String exp = calc.getExpression();
		
		switch( event.getEventType() ) {
		case RESULT:
			// ���� ����� �����ؼ� �����ش�. "����=���" ���·� �����ش�.
			StringBuffer result = new StringBuffer();
			result.append(exp + CalcConstants.RESULT + calc.getResult());
			int length = result.length();
			if( result.lastIndexOf(".0") == length-2 )
				result.delete(length-2, length);
			
			this.setText( result.toString() );
			break;
		// ������ string �������� �����ش�.
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
