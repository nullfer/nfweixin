/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.sales.business.weixin.req;

/**
 * 链接消息
 *
 * @author citysky
 */
public class LinkMessage extends ReqMessage {

// 消息标题
	private String Title;
// 消息描述
	private String Description;
// 消息链接
	private String Url;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		this.Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		this.Description = description;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		this.Url = url;
	}
}
