package com.rabbit.framework.models.event;

/**
 * @author miaohd
 */
public class IncrementMessageEvent extends MessageEvent<Integer> {

	public IncrementMessageEvent(Integer integer) {
		super(integer);
	}
}
