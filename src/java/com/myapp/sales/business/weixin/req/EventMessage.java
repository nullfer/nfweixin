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
	//�¼����ͣ�subscribe(����)��unsubscribe(ȡ������)��CLICK(�Զ���˵�����¼�)

	private String Event;
	//�¼�KEYֵ�����Զ���˵��ӿ���KEYֵ��Ӧ
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
