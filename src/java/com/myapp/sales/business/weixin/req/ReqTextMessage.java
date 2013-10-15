/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.sales.business.weixin.req;

/**
 * 文本消息
 *
 * @author citysky
 */
public class ReqTextMessage extends ReqMessage {
	
	// 消息内容：最大长度2047字节，微信采用utf-8编码，每个汉字占据6个字节，即最多341个汉字
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		this.Content = content;
	}
}
