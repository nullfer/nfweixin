/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.sales.business.weixin.req;

/**
 * �ı���Ϣ
 *
 * @author citysky
 */
public class ReqTextMessage extends ReqMessage {
	
	// ��Ϣ���ݣ���󳤶�2047�ֽڣ�΢�Ų���utf-8���룬ÿ������ռ��6���ֽڣ������341������
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		this.Content = content;
	}
}
