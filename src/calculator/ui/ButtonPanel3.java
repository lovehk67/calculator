package calculator.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import calculator.CalcConstants;


public class ButtonPanel3 extends JPanel {

	public void initComponents() {
				
		JButton[] buttonNum = new JButton[10];
		
		setLayout(new GridLayout(4, 4, 5, 5));
		
		Font f = new Font(Font.SANS_SERIF, Font.PLAIN, getFont().getSize());

		for( int i=0; i<buttonNum.length; i++ ) {
			buttonNum[i] = new JButton(getActionMap().get(String.valueOf(i)));
			buttonNum[i].setFont(f);
		}
	
		JButton buttonDiv = new JButton( getActionMap().get(CalcConstants.OPERATOR_DIV) );
		JButton buttonMul = new JButton( getActionMap().get(CalcConstants.OPERATOR_MUL) );
		JButton buttonMinus = new JButton( getActionMap().get(CalcConstants.OPERATOR_MINUS) );
		JButton buttonPoint = new JButton( getActionMap().get(CalcConstants.NUMBER_POINT) );
		JButton buttonPlus = new JButton( getActionMap().get(CalcConstants.OPERATOR_PLUS) );

		JButton buttonSign = new JButton( getActionMap().get(CalcConstants.SIGN) );

		buttonDiv.setForeground(Color.red);
		buttonMul.setForeground(Color.red);
		buttonMinus.setForeground(Color.red);
		buttonPlus.setForeground(Color.red);
		
		buttonDiv.setFont(f);
		buttonMul.setFont(f);
		buttonMinus.setFont(f);
		buttonSign.setFont(f);
		buttonPoint.setFont(f);
		buttonPlus.setFont(f);

		add(buttonNum[7]);
		add(buttonNum[8]);
		add(buttonNum[9]);
		add(buttonDiv);
		add(buttonNum[4]);
		add(buttonNum[5]);
		add(buttonNum[6]);
		add(buttonMul);
		add(buttonNum[1]);
		add(buttonNum[2]);
		add(buttonNum[3]);
		add(buttonMinus);
		add(buttonNum[0]);
		add(buttonSign);
		add(buttonPoint);
		add(buttonPlus);		
	}
}
