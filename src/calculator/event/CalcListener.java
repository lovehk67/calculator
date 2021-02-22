package calculator.event;

import java.util.EventListener;

public interface CalcListener extends EventListener {
	public void stateChanged(CalcEvent event);
}
