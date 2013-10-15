/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.sales.business.weixin.resp;

/**
 * 文本消息
 *
 * @author citysky
 */
public class RespTextMessage extends RespMessage {

	private String Content;

	/**
	 * @return the Content
	 */
	public String getContent() {
		return Content;
	}

	/**
	 * @param Content the Content to set
	 */
	public void setContent(String Content) {
		this.Content = Content;
	}
}
