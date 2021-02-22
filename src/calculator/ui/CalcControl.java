package calculator.ui;

import calculator.CalcConstants;
import calculator.algorithm.CalcExpression;
import calculator.model.Calculator;

public class CalcControl {
	
	private Calculator calc = null;
	// ���� �Էµǰ� �ִ� ���ڿ� �Ҽ����� �ִ��� ����
	private boolean isPoint = false;
	
	public CalcControl(Calculator calc) {
		this.calc = calc;
	}
	
	public void changeSign() {
		StringBuffer exp = new StringBuffer(calc.getExpression());
		if( exp.length() > 0 ) {
			// ������ ������ ���ڰ� �����ڶ�� ��ȣ�� �������� �ʴ´�.
			if( isOperator(String.valueOf(exp.charAt(exp.length()-1)))) return;
			
			// ������ �������� ��ġ�� ã�´�.
			int lastIndex = Math.max(exp.lastIndexOf(String.valueOf(CalcConstants.OPERATOR_MINUS)), 
								Math.max( exp.lastIndexOf(String.valueOf(CalcConstants.OPERATOR_PLUS)), 
										Math.max(exp.lastIndexOf(String.valueOf(CalcConstants.OPERATOR_DIV)), 
												exp.lastIndexOf(String.valueOf(CalcConstants.OPERATOR_MUL)))));
						
			// �����ڰ� ���ٸ� 0, �ִٸ� ������ ���� ��ġ�� lastIndex �� ����
			lastIndex = lastIndex == -1 ? 0 : lastIndex;
			
			// ������ ������ �����ڰ� -�̸�, ��ȣ���� ���������� �����Ͽ� ��ȣ�� ��� ����.
			if( exp.charAt(lastIndex) == CalcConstants.OPERATOR_MINUS.charAt(0) ) {
				// -������ ������ -��ȣ�� ���� -�����ڸ� +�����ڷ� �ٲ��ش�.			
				// ��ȣ�� �����Ѵ�.
				exp.deleteCharAt(lastIndex);
				// ������ �����ڰ� ������ �� ���� ���ڶ�� ��ȣ ���� ���ڸ� ������ �ʴ´�.
				if( lastIndex != 0 ) {
					char ch = exp.charAt(lastIndex-1);
					// -��ȣ �տ� ���ڰ� �ִٸ� +��ȣ�� �����Ѵ�.
					if( CalcConstants.NUMBER_ZERO.charAt(0) <= ch && CalcConstants.NUMBER_NINE.charAt(0) >= ch ) 
						exp.insert(lastIndex, CalcConstants.OPERATOR_PLUS );
				}
			}
			// ��� �̹Ƿ� -��ȣ�� �����Ѵ�.
			else {
				if( lastIndex != 0 ) lastIndex++;
				
				if( lastIndex != 0 && exp.charAt(lastIndex-1) == CalcConstants.OPERATOR_PLUS.charAt(0) ) 
					exp.replace(lastIndex-1, lastIndex, String.valueOf(CalcConstants.OPERATOR_MINUS));
				// ���� ��ȣ�� �ٿ��� �ϴ� ���� ���� �����ڰ� +��� +�����ڸ� -�� �ٲ��ش�.
				else exp.insert(lastIndex, CalcConstants.OPERATOR_MINUS );
			}
			
			// ��ȣ�� ������ ������ �𵨿� ����
			calc.setExpression(exp.toString());
		}
	}
	
	private boolean isOperator(String oper) {
		if( CalcConstants.OPERATOR_PLUS.equals(oper) || CalcConstants.OPERATOR_MINUS.equals(oper)
			|| CalcConstants.OPERATOR_MUL.equals(oper) || CalcConstants.OPERATOR_DIV.equals(oper) ) return true;
		else return false;
	}
	
	public void addExpression(String exp) {
		
		String calcExp = calc.getExpression();
		char lastExpStr = '\u0000';
		
		if( calcExp.length() > 0) 
			lastExpStr = calcExp.charAt(calcExp.length()-1);
		
		// ��Ģ������ �Է� �޾��� ��
		if( isOperator(String.valueOf(exp.charAt(0))) ) {
			// ������ �ִٸ�
			if( calcExp.length() > 0 ) {
				// ������ ������ ���ڰ� "." �̶�� "."�� �����Ѵ�.
				if( CalcConstants.NUMBER_POINT.charAt(0) == lastExpStr) backspace();
				// ������ ������ ���ڰ� �����ڰ� �ƴ϶��
				if( !isOperator(String.valueOf(lastExpStr)) ) {
					// �����ڸ� ���Ŀ� �߰��Ѵ�.
					calc.appendExpression(String.valueOf(exp));
					// "." �� �Է� �� �� �ֵ��� isDot �� false �� �Ѵ�.
					isPoint = false;
				}
			}
		}
		else if( CalcConstants.NUMBER_POINT.charAt(0) == exp.charAt(0) ) {
			// "." �� �ԷµǸ� �����ڰ� �ԷµǱ� ������ "."�� �Է��� �� ����.
			if( !isPoint ) {
				isPoint = true;
				// ������ ������ ���ڰ� �����ڶ�� �̰ų� ó�� �Է��ϴ� ���̶��
				if( isOperator(String.valueOf(lastExpStr)) || calcExp.length() == 0 )
					calc.appendExpression(String.valueOf(CalcConstants.NUMBER_ZERO+exp));
				else 
					calc.appendExpression(String.valueOf(exp));
			}
		}
		// ����
		else { 
			// ������ "0"�̸� �ڿ� "." �� �����ڸ� �� �� �ִ�.
			if( String.valueOf(CalcConstants.NUMBER_ZERO).equals(calcExp) ) return; 
			// ������ ���� 0 �ڿ��� ���ڰ� �� �� ����.
			if( calcExp.length() > 2 && calcExp.charAt(calcExp.length()-1) == CalcConstants.NUMBER_ZERO.charAt(0)
				&& isOperator(String.valueOf(calcExp.charAt(calcExp.length()-2))))	return;
			calc.appendExpression(String.valueOf(exp));
		}
	}
	
	public void backspace() {
		StringBuffer exp = new StringBuffer(calc.getExpression());
		int length = exp.length();
		if( length > 0 ) {
			char lastChar = exp.charAt(length-1);
			// ������ ���ڰ� '.' �̸� �ٽ� '.' �� �Է� ���� �� �ֵ��� isDot �� false �� �Ѵ�.
			if( CalcConstants.NUMBER_POINT.charAt(0) == lastChar ) isPoint = false;
			exp.deleteCharAt(length-1);
			calc.setExpression(exp.toString());
		}
	}
	
	public void clear() {
		isPoint = false;
		calc.setExpression("");
	}
	
	private StringBuffer removeLastOperator() {
		StringBuffer buf = new StringBuffer(calc.getExpression());
		// ������ ""�� �ƴϰ� ������ ������ ���� ������ �϶� ����
		while( buf.length()>0 && isOperator(String.valueOf(buf.charAt(buf.length()-1))) ) {
			// ���Ŀ��� ������ ���ڸ� ����(������)
			buf.deleteCharAt(buf.length()-1);
		}
		
		return buf;
	}

	public void result() {
		// ������ �������� �����ڰ� �ִٸ� ����.
		StringBuffer exp = removeLastOperator(); 
		
		// ������ ������ ������ �׳� �����Ѵ�.
		if( exp.length() == 0 ) return;
		// �����ڰ� ������ ������ ������� �ʴ´�.
		if( CalcExpression.getIndexOfFirstOperator(exp.toString()) == Integer.MAX_VALUE ) return;
		
		calc.setExpression(exp.toString());
		calc.setResult(new CalcExpression(exp.toString()).calc());
		isPoint = false;
	}
	
	public void clearMemory() {
		calc.setMemory("");
	}
	
	public void readMemory() {
		if( calc.isFinished() ) calc.setExpression("");
		calc.appendExpression(calc.getMemory());
	}
	
	public void saveMemory() {
		if( calc.isFinished() ) return;
		calc.setMemory(removeLastOperator().toString());
	}
	
	public void plusMemory() {
		if( calc.isFinished() ) return;
		if( !"".equals(calc.getMemory()) ) 
			calc.setMemory(calc.getMemory() + CalcConstants.OPERATOR_PLUS + removeLastOperator().toString());
		else calc.setMemory(removeLastOperator().toString());
	}	
}