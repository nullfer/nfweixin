/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.sales.business.weixin.req;

/**
 * ������Ϣ
 *
 * @author citysky
 */
public class LinkMessage extends ReqMessage {

// ��Ϣ����
	private String Title;
// ��Ϣ����
	private String Description;
// ��Ϣ����
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
