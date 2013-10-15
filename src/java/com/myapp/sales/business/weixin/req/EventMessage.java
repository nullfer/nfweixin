/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.sales.business.weixin.req;

/**
 *
 * @author citysky
 */
public class EventMessage extends ReqMessage {
	//事件类型，subscribe(订阅)、unsubscribe(取消订阅)、CLICK(自定义菜单点击事件)

	private String Event;
	//事件KEY值，与自定义菜单接口中KEY值对应
	private String EventKey;

	/**
	 * @return the Event
	 */
	public String getEvent() {
		return Event;
	}

	/**
	 * @param Event the Event to set
	 */
	public void setEvent(String Event) {
		this.Event = Event;
	}

	/**
	 * @return the EventKey
	 */
	public String getEventKey() {
		return EventKey;
	}

	/**
	 * @param EventKey the EventKey to set
	 */
	public void setEventKey(String EventKey) {
		this.EventKey = EventKey;
	}
}
