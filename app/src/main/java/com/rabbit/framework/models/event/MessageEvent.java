package com.rabbit.framework.models.event;

/**
 * @author miaohd
 */
public abstract class MessageEvent<T> {

	public enum Action{
		CREATE, UPDATE, DELETE, GET, NONE
	}

	T t;
	Action action;

	public MessageEvent(T t, Action action){
		this.t = t;
		this.action = action;
	}

	public MessageEvent(T t){
		this(t, Action.NONE);
	}

	public T getData(){
		return t;
	}

}
