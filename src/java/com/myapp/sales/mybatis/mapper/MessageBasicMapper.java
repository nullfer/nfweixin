/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.sales.mybatis.mapper;

import com.myapp.sales.business.weixin.botchat.MessageType;
import com.myapp.sales.business.weixin.botchat.WxKeyword;
import com.myapp.sales.business.weixin.botchat.WxReply;
import java.util.List;

/**
 *
 * @author citysky
 */
public interface MessageBasicMapper {
	
	public void insertMessageType(MessageType message);
	
	public List<MessageType> getAllMessageType();
	
	public void insertKeyword(WxKeyword keyword);
	
	public List<WxKeyword> getAllKeywords();
	
	public void insertReply(WxReply replyDict);
}
