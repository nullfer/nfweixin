/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.sales.business.weixin.botchat;

/**
 *
 * @author citysky
 */
public class MessageType {

	private int id;
	private String name;
	private String description;
	private boolean replySupport;
	private String code;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the replySupoort
	 */
	public boolean isReplySupport() {
		return replySupport;
	}

	/**
	 * @param replySupoort the replySupoort to set
	 */
	public void setReplySupport(boolean replySupoort) {
		this.replySupport = replySupoort;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
}
