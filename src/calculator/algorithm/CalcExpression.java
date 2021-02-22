package calculator.algorithm;

import java.util.Stack;

import calculator.CalcConstants;
 
public class CalcExpression {

	// 모델의 수식을 StringBuffer 로 받는다.
	private StringBuffer exp = null;
	
	public CalcExpression(String exp) {
		this.exp = new StringBuffer(exp);
	}
	
	public static int getIndexOfFirstOperator(String exp) {
		// 가장 앞에 있는 연산자의 위치를 찾는다.
		int index = exp.indexOf(CalcConstants.OPERATOR_DIV) == -1 ? Integer.MAX_VALUE : exp.indexOf(CalcConstants.OPERATOR_DIV);
			index = Math.min(index, exp.indexOf(CalcConstants.OPERATOR_MUL) == -1 ? Integer.MAX_VALUE : exp.indexOf(CalcConstants.OPERATOR_MUL));
			index = Math.min(index, exp.indexOf(CalcConstants.OPERATOR_PLUS) == -1 ? Integer.MAX_VALUE : exp.indexOf(CalcConstants.OPERATOR_PLUS));
		// -부호가 제일 앞에 있다면 
		if( exp.charAt(0) == CalcConstants.OPERATOR_MINUS.charAt(0) ) { 
			// 수식의 두번째 문자부터 가장 앞에 있는 "-" 연산자의 위치를 찾는다.
			index = Math.min(index, exp.indexOf(CalcConstants.OPERATOR_MINUS, 1) == -1 ? Integer.MAX_VALUE : exp.indexOf(CalcConstants.OPERATOR_MINUS, 1));
		}
		else 
			index = Math.min(index, exp.indexOf(CalcConstants.OPERATOR_MINUS) == -1 ? Integer.MAX_VALUE : exp.indexOf(CalcConstants.OPERATOR_MINUS, 1));
		
		return index;
	}
	
	// 수식이 숫자로 시작한다고 가정하고,
	// 수식의 맨 앞의 숫자를 double 형으로 변환하여 돌려주고 수식에서 숫자를 제거한다.
	private double getNum() {
		double d = 0.0;
		
		int index = getIndexOfFirstOperator(exp.toString());

		// 수식에서 연산자의 index 를 찾지 못했다면 수식에 연산자가 없고 한개의 숫자만 있는 것이다.
		if( index == Integer.MAX_VALUE ) index = exp.length();

		// 수식에서 숫자를 가져와서 double 형으로 변환.
		d = Double.parseDouble(exp.substring(0, index));
		// 가져온 숫자를 수식에서 삭제한다.
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

		// 연산에 필요한 스택. double 형 숫자와 String 형 연산자가 차례로 들어간다.
		Stack<Object> stack = new Stack<Object>();
		
		while( exp.length() > 0 ) {
			
			// 숫자를 넣고
			stack.push(getNum());

			// 수식의 마지막 숫자를 스택에 넣고 더이상 수식이 없으면 반복문을 끝낸다.
			if( exp.length() == 0 ) break;
			
			// 연산자를 받고
			operator = String.valueOf(exp.charAt(0));
			exp.deleteCharAt(0);
			
			// 우선순위가 높다면  스택의 숫자와 수식에서 가져온 숫자를 연산 후  수식의 맨 앞에 삽입한다.
			if( isPriorityHigh(operator) ) {
				// 스택에서 숫자를 꺼내고
				op1 = (Double)stack.pop();
				// 수식에서 숫자를 가져온다.
				op2 = getNum();

				// 연산 후 수식의 맨 앞에 삽입한다.
				if( String.valueOf(CalcConstants.OPERATOR_MUL).equals(operator)) exp.insert(0, op1*op2);
				else if( String.valueOf(CalcConstants.OPERATOR_DIV).equals( operator))	exp.insert(0, op1/op2);
			}
			// 우선순위가 낮다면 연산자를 스택에 넣는다.
			else {
				stack.push(operator);
			}
		}
		
		// stack 에 최종 연산결과인 숫자 하나만 남을 때 까지 반복한다.
		while( stack.size() > 1) {
			// 피연산자2, 연산자, 피연산자1을 스택에서 차례로 꺼낸다.
			op2 = (Double)stack.pop();
			operator = (String)stack.pop();
			op1 = (Double)stack.pop();
			
			// 연산 후 결과를 스택에 넣는다.
			if( String.valueOf(CalcConstants.OPERATOR_PLUS).equals(operator) ) stack.push(op1+op2);
			if( String.valueOf(CalcConstants.OPERATOR_MINUS).equals(operator) ) stack.push(op1-op2);
		}
		
		// 최종 연산된 결과를 리턴.
		return (Double)stack.pop();
	}
}