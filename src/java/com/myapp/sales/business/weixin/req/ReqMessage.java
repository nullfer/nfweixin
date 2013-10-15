/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.sales.business.weixin.req;

import java.io.Serializable;

/**
 *
 * @author citysky
 */
public class ReqMessage implements Serializable {

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

	@Override
	public String toString() {
		return "msgId:" + getMsgId() + "\ncreateTime:" + getCreateTime() + "\ntoUserName:" + getToUserName() + "\n FromUserName:" + getFromUserName() + "\nmessageType:" + getMsgType();
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
}
