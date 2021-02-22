package calculator.model;

import javax.swing.event.EventListenerList;

import calculator.event.CalcListener;
import calculator.event.CalcEvent;
import calculator.event.CalcEvent.EventType;

public class Calculator {

	private EventListenerList listenerList = new EventListenerList();
	// ����
	private String expression = "";
	// �޸�(����)
	private String memory = "";
	// �����
	private double result = 0.0;
	// ���� �Է��� ��ġ�� ������� ��� �Ǿ����� ����
	private boolean isFinished = false;
	
	
	// ���Ŀ� �μ��� ���� string �� ���Ѵ�.
	public void appendExpression(String exp) {
		expression += exp;
		// ������ ����ǰ� �ִ� ���̶�� ������� ������ �ʾҴ�.
		isFinished = false;
		fire(EventType.EXPRESSION);
	}

	// ������ �μ��� ���� string ���� ��ü
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