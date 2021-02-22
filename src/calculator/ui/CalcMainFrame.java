package calculator.ui;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import calculator.CalcConstants;
import calculator.action.CalcActions;
import calculator.action.CalcInputs;
import calculator.layout.CalcLayout;
import calculator.model.Calculator;

public class CalcMainFrame extends JFrame{
	
	private Calculator calc;
	private ButtonPanel1 panel1;
	private TextFieldView textField;
	
	private CalcMainFrame() {
		super(CalcConstants.PROGRAM_TITLE);

		calc = new Calculator();
		textField = new TextFieldView();
		CalcControl control = new CalcControl(calc);

		panel1 = new ButtonPanel1();
		ButtonPanel2 panel2 = new ButtonPanel2();
		ButtonPanel3 panel3 = new ButtonPanel3();
		CalcActions actions = new CalcActions(control);
		
		panel1.setActionMap(actions.getActionMap());
		panel2.setActionMap(actions.getActionMap());
		panel3.setActionMap(actions.getActionMap());

		JPanel panel = new JPanel();
		panel.setActionMap(actions.getActionMap());
		panel.setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, new CalcInputs(panel).getInputMap());
		
		calc.addCalcListener(textField);
		calc.addCalcListener(panel1);
		
		panel1.initComponents();
		panel2.initComponents();
		panel3.initComponents();
		
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new CalcLayout(5,5));
		panel.add(textField, CalcLayout.COMP1);
		panel.add(panel1, CalcLayout.COMP2);
		panel.add(panel2, CalcLayout.COMP3);
		panel.add(panel3, CalcLayout.COMP4);	
				
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		
		calc.removeCalcListener(textField);
		calc.removeCalcListener(panel1);
		
		panel1 = null;
		textField = null;
		calc = null;
	}

	public static void main(String args[]) {
		new CalcMainFrame().setVisible(true);
	}
}
