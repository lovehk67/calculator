package calculator.algorithm;

import java.util.Stack;

import calculator.CalcConstants;
 
public class CalcExpression {

	// ���� ������ StringBuffer �� �޴´�.
	private StringBuffer exp = null;
	
	public CalcExpression(String exp) {
		this.exp = new StringBuffer(exp);
	}
	
	public static int getIndexOfFirstOperator(String exp) {
		// ���� �տ� �ִ� �������� ��ġ�� ã�´�.
		int index = exp.indexOf(CalcConstants.OPERATOR_DIV) == -1 ? Integer.MAX_VALUE : exp.indexOf(CalcConstants.OPERATOR_DIV);
			index = Math.min(index, exp.indexOf(CalcConstants.OPERATOR_MUL) == -1 ? Integer.MAX_VALUE : exp.indexOf(CalcConstants.OPERATOR_MUL));
			index = Math.min(index, exp.indexOf(CalcConstants.OPERATOR_PLUS) == -1 ? Integer.MAX_VALUE : exp.indexOf(CalcConstants.OPERATOR_PLUS));
		// -��ȣ�� ���� �տ� �ִٸ� 
		if( exp.charAt(0) == CalcConstants.OPERATOR_MINUS.charAt(0) ) { 
			// ������ �ι�° ���ں��� ���� �տ� �ִ� "-" �������� ��ġ�� ã�´�.
			index = Math.min(index, exp.indexOf(CalcConstants.OPERATOR_MINUS, 1) == -1 ? Integer.MAX_VALUE : exp.indexOf(CalcConstants.OPERATOR_MINUS, 1));
		}
		else 
			index = Math.min(index, exp.indexOf(CalcConstants.OPERATOR_MINUS) == -1 ? Integer.MAX_VALUE : exp.indexOf(CalcConstants.OPERATOR_MINUS, 1));
		
		return index;
	}
	
	// ������ ���ڷ� �����Ѵٰ� �����ϰ�,
	// ������ �� ���� ���ڸ� double ������ ��ȯ�Ͽ� �����ְ� ���Ŀ��� ���ڸ� �����Ѵ�.
	private double getNum() {
		double d = 0.0;
		
		int index = getIndexOfFirstOperator(exp.toString());

		// ���Ŀ��� �������� index �� ã�� ���ߴٸ� ���Ŀ� �����ڰ� ���� �Ѱ��� ���ڸ� �ִ� ���̴�.
		if( index == Integer.MAX_VALUE ) index = exp.length();

		// ���Ŀ��� ���ڸ� �����ͼ� double ������ ��ȯ.
		d = Double.parseDouble(exp.substring(0, index));
		// ������ ���ڸ� ���Ŀ��� �����Ѵ�.
		exp.delete(0,index);
		
		return d;
	}
	
	public boolean isPriorityHigh(String operator) {
		if( String.valueOf(CalcConstants.OPERATOR_MUL).equals(operator) || 
			String.valueOf(CalcConstants.OPERATOR_DIV).equals(operator) ) return true;
		return false;
	}
	
	public double calc() {
		double op1 = 0.0;
		double op2 = 0.0;
		String operator = "";

		// ���꿡 �ʿ��� ����. double �� ���ڿ� String �� �����ڰ� ���ʷ� ����.
		Stack<Object> stack = new Stack<Object>();
		
		while( exp.length() > 0 ) {
			
			// ���ڸ� �ְ�
			stack.push(getNum());

			// ������ ������ ���ڸ� ���ÿ� �ְ� ���̻� ������ ������ �ݺ����� ������.
			if( exp.length() == 0 ) break;
			
			// �����ڸ� �ް�
			operator = String.valueOf(exp.charAt(0));
			exp.deleteCharAt(0);
			
			// �켱������ ���ٸ�  ������ ���ڿ� ���Ŀ��� ������ ���ڸ� ���� ��  ������ �� �տ� �����Ѵ�.
			if( isPriorityHigh(operator) ) {
				// ���ÿ��� ���ڸ� ������
				op1 = (Double)stack.pop();
				// ���Ŀ��� ���ڸ� �����´�.
				op2 = getNum();

				// ���� �� ������ �� �տ� �����Ѵ�.
				if( String.valueOf(CalcConstants.OPERATOR_MUL).equals(operator)) exp.insert(0, op1*op2);
				else if( String.valueOf(CalcConstants.OPERATOR_DIV).equals( operator))	exp.insert(0, op1/op2);
			}
			// �켱������ ���ٸ� �����ڸ� ���ÿ� �ִ´�.
			else {
				stack.push(operator);
			}
		}
		
		// stack �� ���� �������� ���� �ϳ��� ���� �� ���� �ݺ��Ѵ�.
		while( stack.size() > 1) {
			// �ǿ�����2, ������, �ǿ�����1�� ���ÿ��� ���ʷ� ������.
			op2 = (Double)stack.pop();
			operator = (String)stack.pop();
			op1 = (Double)stack.pop();
			
			// ���� �� ����� ���ÿ� �ִ´�.
			if( String.valueOf(CalcConstants.OPERATOR_PLUS).equals(operator) ) stack.push(op1+op2);
			if( String.valueOf(CalcConstants.OPERATOR_MINUS).equals(operator) ) stack.push(op1-op2);
		}
		
		// ���� ����� ����� ����.
		return (Double)stack.pop();
	}
}