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

public class ButtonPanel2 extends JPanel {

	public void initComponents() {				
		setLayout(new GridLayout(1,3,5,5));
		
		JButton buttonBack = new JButton( getActionMap().get(CalcConstants.BACKSPACE) );
		JButton buttonC = new JButton( getActionMap().get(CalcConstants.CLEAR) );
		JButton buttonResult = new JButton( getActionMap().get(CalcConstants.RESULT) );
		
		buttonBack.setForeground(Color.red);
		buttonC.setForeground(Color.red);
		buttonResult.setForeground(Color.red);
		
		Font f = new Font(Font.SANS_SERIF, Font.PLAIN, getFont().getSize());
		buttonBack.setFont(f);
		buttonC.setFont(f);
		buttonResult.setFont(f);
		
		add(buttonBack);
		add(buttonC);
		add(buttonResult);
	}
}
