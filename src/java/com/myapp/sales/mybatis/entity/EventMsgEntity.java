/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.sales.mybatis.entity;

/**
 *
 * @author citysky
 */
public class EventMsgEntity extends MessageEntity {

	private String event;
	private String eventKey;

	/**
	 * @return the event
	 */
	public String getEvent() {
		return event;
	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(String event) {
		this.event = event;
	}

	/**
	 * @return the eventKey
	 */
	public String getEventKey() {
		return eventKey;
	}

	/**
	 * @param eventKey the eventKey to set
	 */
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
}
