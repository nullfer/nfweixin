/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.sales.business.weixin.resp;

/**
 * ��Ϣ���ࣨ��ͨ�û� -> �����ʺţ�
 *
 * @author citysky
 */
public class RespMessage {

	// ���շ��ʺţ��յ���OpenID��
	private String ToUserName;
	// ������΢�ź�
	private String FromUserName;
	// ��Ϣ����ʱ�� �����ͣ�
	private long CreateTime;
	// ��Ϣ���ͣ�text/music/news��
	private String MsgType;
	// λ0x0001����־ʱ���Ǳ���յ�����Ϣ
	private int FuncFlag;

	@Override
	public String toString() {
		return "createTime:" + getCreateTime() + "\ntoUserName:" + getToUserName() + "\n FromUserName:" + getFromUserName() + "\nmessageType:" + getMsgType();
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
	 * @return the FuncFlag
	 */
	public int getFuncFlag() {
		return FuncFlag;
	}

	/**
	 * @param FuncFlag the FuncFlag to set
	 */
	public void setFuncFlag(int FuncFlag) {
		this.FuncFlag = FuncFlag;
	}
}
