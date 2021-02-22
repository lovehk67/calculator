package calculator.model;

import javax.swing.event.EventListenerList;

import calculator.event.CalcListener;
import calculator.event.CalcEvent;
import calculator.event.CalcEvent.EventType;

public class Calculator {

	private EventListenerList listenerList = new EventListenerList();
	// 수식
	private String expression = "";
	// 메모리(수식)
	private String memory = "";
	// 결과값
	private double result = 0.0;
	// 수식 입력을 마치고 결과값이 계산 되었는지 여부
	private boolean isFinished = false;
	
	
	// 수식에 인수로 받은 string 을 더한다.
	public void appendExpression(String exp) {
		expression += exp;
		// 수식이 변경되고 있는 중이라면 결과값은 나오지 않았다.
		isFinished = false;
		fire(EventType.EXPRESSION);
	}

	// 수식을 인수로 받은 string 으로 대체
	public void setExpression(String exp) {
		expression = exp;
		isFinished = false;
		fire(EventType.EXPRESSION);
	}

	public String getExpression() {
		return expression;
	}
	
	public boolean isFinished() {
		return isFinished;
	}
	
	public void setResult(double r) {
		result = r;
		isFinished = true;
		fire(EventType.RESULT);
	}
	
	public double getResult() {
		return result;
	}
	
	public void setMemory(String mem) {
		memory = mem;
		fire(EventType.MEMORY);
	}
	
	public String getMemory() {
		return memory;
	}
	
	public void addCalcListener(CalcListener l) {
		listenerList.add(CalcListener.class, l);
	}
	
	public void removeCalcListener(CalcListener l) {
		listenerList.remove(CalcListener.class, l);
	}
		
	private void fire(EventType eventType) {
		Object[] o = listenerList.getListenerList();
		CalcEvent event = new CalcEvent(this, eventType);
		for (int i = o.length - 2; i >= 0; i -= 2) {
			if (o[i] == CalcListener.class) {
				((CalcListener) o[i + 1]).stateChanged(event);
			}
		}
	}
}