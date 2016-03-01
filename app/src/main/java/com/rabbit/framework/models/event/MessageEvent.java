package com.rabbit.framework.models.event;

/**
 * @author miaohd
 */
public class MessageEvent<T> {

	public enum EventAction {
		CREATE, UPDATE, DELETE, GET, NONE
	}

	public enum EventType{
		CLIENTID, NONE
	}

	T t;
	EventAction eventAction;
	EventType eventType;

	public MessageEvent(T t, EventAction eventAction, EventType eventType){
		this.t = t;
		this.eventAction = eventAction;
		this.eventType = eventType;
	}

	public MessageEvent(T t){
		this(t, EventAction.NONE, EventType.NONE);
	}

	public T getData(){
		return t;
	}

	public EventAction getEventAction() {
		return eventAction;
	}

	public EventType getEventType() {
		return eventType;
	}
}
