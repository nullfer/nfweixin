/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.sales.business.weixin.botchat;

/**
 *
 * @author citysky
 */
public class WxReply {

	private String id;
	private int msgTypeId;
	private String content;
	private String description;
	private String msgTypeName;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the msgTypeId
	 */
	public int getMsgTypeId() {
		return msgTypeId;
	}

	/**
	 * @param msgTypeId the msgTypeId to set
	 */
	public void setMsgTypeId(int msgTypeId) {
		this.msgTypeId = msgTypeId;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the msgTypeName
	 */
	public String getMsgTypeName() {
		return msgTypeName;
	}

	/**
	 * @param msgTypeName the msgTypeName to set
	 */
	public void setMsgTypeName(String msgTypeName) {
		this.msgTypeName = msgTypeName;
	}
}
