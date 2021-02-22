package calculator.event;

import java.util.EventObject;

public class CalcEvent extends EventObject{

	public static enum EventType {
		EXPRESSION,
		RESULT,
		MEMORY,
	}
	
	private EventType eventType;

    public CalcEvent(Object source, EventType eventType) {
        super(source);
        this.eventType = eventType;
    }
    
    public EventType getEventType() {
    	return eventType;
    }
}
