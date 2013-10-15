/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.sales.mybatis.entity;

import java.util.Date;

/**
 *
 * @author citysky
 */
public class MessageEntity {
	// ������΢�ź�

	private String ToUserName;
	// ���ͷ��ʺţ�һ��OpenID��
	private String FromUserName;
	// ��Ϣ����ʱ�� �����ͣ�
	private long CreateTime;
	// ��Ϣ���ͣ�text/image/location/link��
	private String MsgType;
	// ��Ϣid��64λ����
	private long MsgId;
	private boolean postSign;
	// λ0x0001����־ʱ���Ǳ���յ�����Ϣ
	private int funcFlag;

	public MessageEntity() {
	}

	public MessageEntity(long msgId, String toUserName, String fromUserName, long createTime, String msgType, boolean postSign) {
		this.MsgId = msgId;
		this.ToUserName = toUserName;
		this.FromUserName = fromUserName;
		this.CreateTime = createTime;
		this.MsgType = msgType;
		this.postSign = postSign;
	}

	/**
	 * @return the ToUserName
	 */
	public String getToUserName() {
		return ToUserName;
	}

	/**
	 * @param ToUserName the ToUserName to set
	 */
	public void setToUserName(String ToUserName) {
		this.ToUserName = ToUserName;
	}

	/**
	 * @return the FromUserName
	 */
	public String getFromUserName() {
		return FromUserName;
	}

	/**
	 * @param FromUserName the FromUserName to set
	 */
	public void setFromUserName(String FromUserName) {
		this.FromUserName = FromUserName;
	}

	/**
	 * @return the CreateTime
	 */
	public long getCreateTime() {
		return CreateTime;
	}

	/**
	 * @param CreateTime the CreateTime to set
	 */
	public void setCreateTime(long CreateTime) {
		this.CreateTime = CreateTime;
	}

	/**
	 * @return the MsgType
	 */
	public String getMsgType() {
		return MsgType;
	}

	/**
	 * @param MsgType the MsgType to set
	 */
	public void setMsgType(String MsgType) {
		this.MsgType = MsgType;
	}

	/**
	 * @return the MsgId
	 */
	public long getMsgId() {
		return MsgId;
	}

	/**
	 * @param MsgId the MsgId to set
	 */
	public void setMsgId(long MsgId) {
		this.MsgId = MsgId;
	}

	/**
	 * @return the postSign
	 */
	public boolean isPostSign() {
		return postSign;
	}

	/**
	 * @param postSign the postSign to set
	 */
	public void setPostSign(boolean postSign) {
		this.postSign = postSign;
	}

	/**
	 * @return the funcFlag
	 */
	public int getFuncFlag() {
		return funcFlag;
	}

	/**
	 * @param funcFlag the funcFlag to set
	 */
	public void setFuncFlag(int funcFlag) {
		this.funcFlag = funcFlag;
	}
	
	public Date getCreateDate(){
		return new Date(getCreateTime());
	}
}
