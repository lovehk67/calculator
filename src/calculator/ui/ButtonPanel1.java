package calculator.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import calculator.CalcConstants;
import calculator.event.CalcEvent;
import calculator.event.CalcListener;
import calculator.event.CalcEvent.EventType;
import calculator.model.Calculator;

public class ButtonPanel1 extends JPanel implements CalcListener {

	private JLabel label = new JLabel();
	
	public void initComponents() {
		setLayout(new GridLayout(5,1, 5,5));
		
		label.setBorder(BorderFactory.createLoweredBevelBorder());
		label.setHorizontalAlignment(JLabel.CENTER);
		
		JButton buttonMC = new JButton(getActionMap().get(CalcConstants.MEMORY_CLEAR));
		JButton buttonMR = new JButton(getActionMap().get(CalcConstants.MEMORY_READ));
		JButton buttonMS = new JButton(getActionMap().get(CalcConstants.MEMORY_SAVE));
		JButton buttonMP = new JButton(getActionMap().get(CalcConstants.MEMORY_PLUS));
		
		buttonMC.setForeground(Color.red);
		buttonMR.setForeground(Color.red);
		buttonMS.setForeground(Color.red);
		buttonMP.setForeground(Color.red);

		Font f = new Font(Font.SANS_SERIF, Font.PLAIN, getFont().getSize());
		
		label.setFont(f);
		buttonMC.setFont(f);
		buttonMR.setFont(f);
		buttonMS.setFont(f);
		buttonMP.setFont(f);

		add( label );
		add( buttonMC );
		add( buttonMR );
		add( buttonMS );
		add( buttonMP );
	}
	
	// model listener
	public void stateChanged(CalcEvent event) {
		if( event.getEventType() == EventType.MEMORY ) {
			Calculator calc = (Calculator)event.getSource();
			if( calc.getMemory().length() == 0 ) label.setText("");
			else label.setText(CalcConstants.MEMORY);
		}
	}
}
