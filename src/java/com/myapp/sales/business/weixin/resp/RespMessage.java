/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.sales.business.weixin.resp;

/**
 * 消息基类（普通用户 -> 公众帐号）
 *
 * @author citysky
 */
public class RespMessage {

	// 接收方帐号（收到的OpenID）
	private String ToUserName;
	// 开发者微信号
	private String FromUserName;
	// 消息创建时间 （整型）
	private long CreateTime;
	// 消息类型（text/music/news）
	private String MsgType;
	// 位0x0001被标志时，星标刚收到的消息
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
