package calculator.ui;

import calculator.CalcConstants;
import calculator.algorithm.CalcExpression;
import calculator.model.Calculator;

public class CalcControl {
	
	private Calculator calc = null;
	// 현재 입력되고 있는 숫자에 소숫점이 있는지 여부
	private boolean isPoint = false;
	
	public CalcControl(Calculator calc) {
		this.calc = calc;
	}
	
	public void changeSign() {
		StringBuffer exp = new StringBuffer(calc.getExpression());
		if( exp.length() > 0 ) {
			// 수식의 마지막 문자가 연산자라면 부호를 변경하지 않는다.
			if( isOperator(String.valueOf(exp.charAt(exp.length()-1)))) return;
			
			// 마지막 연산자의 위치를 찾는다.
			int lastIndex = Math.max(exp.lastIndexOf(String.valueOf(CalcConstants.OPERATOR_MINUS)), 
								Math.max( exp.lastIndexOf(String.valueOf(CalcConstants.OPERATOR_PLUS)), 
										Math.max(exp.lastIndexOf(String.valueOf(CalcConstants.OPERATOR_DIV)), 
												exp.lastIndexOf(String.valueOf(CalcConstants.OPERATOR_MUL)))));
						
			// 연산자가 없다면 0, 있다면 연산자 다음 위치를 lastIndex 로 지정
			lastIndex = lastIndex == -1 ? 0 : lastIndex;
			
			// 수식의 마지막 연산자가 -이면, 부호인지 연산자인지 구분하여 부호일 경우 제거.
			if( exp.charAt(lastIndex) == CalcConstants.OPERATOR_MINUS.charAt(0) ) {
				// -연산자 다음에 -부호가 오면 -연산자를 +연산자로 바꿔준다.			
				// 부호를 제거한다.
				exp.deleteCharAt(lastIndex);
				// 마지막 연산자가 수식의 맨 앞의 문자라면 부호 앞의 문자를 비교하지 않는다.
				if( lastIndex != 0 ) {
					char ch = exp.charAt(lastIndex-1);
					// -부호 앞에 숫자가 있다면 +부호를 삽입한다.
					if( CalcConstants.NUMBER_ZERO.charAt(0) <= ch && CalcConstants.NUMBER_NINE.charAt(0) >= ch ) 
						exp.insert(lastIndex, CalcConstants.OPERATOR_PLUS );
				}
			}
			// 양수 이므로 -부호를 삽입한다.
			else {
				if( lastIndex != 0 ) lastIndex++;
				
				if( lastIndex != 0 && exp.charAt(lastIndex-1) == CalcConstants.OPERATOR_PLUS.charAt(0) ) 
					exp.replace(lastIndex-1, lastIndex, String.valueOf(CalcConstants.OPERATOR_MINUS));
				// 음수 부호를 붙여야 하는 숫자 앞의 연산자가 +라면 +연산자를 -로 바꿔준다.
				else exp.insert(lastIndex, CalcConstants.OPERATOR_MINUS );
			}
			
			// 부호를 변경한 수식을 모델에 설정
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
		
		// 사칙연산을 입력 받았을 때
		if( isOperator(String.valueOf(exp.charAt(0))) ) {
			// 수식이 있다면
			if( calcExp.length() > 0 ) {
				// 수식의 마지막 문자가 "." 이라면 "."을 제거한다.
				if( CalcConstants.NUMBER_POINT.charAt(0) == lastExpStr) backspace();
				// 수식의 마지막 문자가 연산자가 아니라면
				if( !isOperator(String.valueOf(lastExpStr)) ) {
					// 연산자를 수식에 추가한다.
					calc.appendExpression(String.valueOf(exp));
					// "." 을 입력 할 수 있도록 isDot 를 false 로 한다.
					isPoint = false;
				}
			}
		}
		else if( CalcConstants.NUMBER_POINT.charAt(0) == exp.charAt(0) ) {
			// "." 이 입력되면 연산자가 입력되기 전까지 "."을 입력할 수 없다.
			if( !isPoint ) {
				isPoint = true;
				// 수식의 마지막 문자가 연산자라면 이거나 처음 입력하는 것이라면
				if( isOperator(String.valueOf(lastExpStr)) || calcExp.length() == 0 )
					calc.appendExpression(String.valueOf(CalcConstants.NUMBER_ZERO+exp));
				else 
					calc.appendExpression(String.valueOf(exp));
			}
		}
		// 숫자
		else { 
			// 수식이 "0"이면 뒤에 "." 나 연산자만 올 수 있다.
			if( String.valueOf(CalcConstants.NUMBER_ZERO).equals(calcExp) ) return; 
			// 연산자 뒤의 0 뒤에는 숫자가 올 수 없다.
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
			// 마지막 문자가 '.' 이면 다시 '.' 을 입력 받을 수 있도록 isDot 를 false 로 한다.
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
		// 수식이 ""이 아니고 수식의 마지막 값이 연산자 일때 까지
		while( buf.length()>0 && isOperator(String.valueOf(buf.charAt(buf.length()-1))) ) {
			// 수식에서 마지막 문자를 제거(연산자)
			buf.deleteCharAt(buf.length()-1);
		}
		
		return buf;
	}

	public void result() {
		// 수식의 마지막에 연산자가 있다면 제거.
		StringBuffer exp = removeLastOperator(); 
		
		// 연산할 수식이 없으면 그냥 리턴한다.
		if( exp.length() == 0 ) return;
		// 연산자가 없으면 수식을 계산하지 않는다.
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