/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.sales.business.weixin.botchat;

/**
 *
 * @author citysky
 */
public class WxKeyword {

	private int msgTypeId;
	private String keyword;
	private String description;
	private String id;
	private transient String msgTypeName;

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
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * @param keyword the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
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
