/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.sales.mybatis.mapper;

import com.myapp.sales.mybatis.entity.EventMsgEntity;
import com.myapp.sales.mybatis.entity.ImgMsgEntity;
import com.myapp.sales.mybatis.entity.LinkMsgEntity;
import com.myapp.sales.mybatis.entity.LocMsgEntity;
import com.myapp.sales.mybatis.entity.MessageEntity;
import com.myapp.sales.mybatis.entity.TxtMsgEntity;
import com.myapp.sales.mybatis.entity.VoiceMsgEntity;
import java.util.List;

/**
 *
 * @author citysky
 */
public interface MessageMapper {

	public void saveMessage(MessageEntity message);
	
	public void saveTxtMessage(TxtMsgEntity message);
	
	public void saveImgMessage(ImgMsgEntity message);
	
	public void saveLinkMessage(LinkMsgEntity message);
	
	public void saveLocationMessage(LocMsgEntity message);
	
	public void saveVoiceMessage(VoiceMsgEntity message);
	
	public void saveEventMessage(EventMsgEntity message);

	public MessageEntity getMessage(String msgId);
	
	public List<MessageEntity> getAllMessage();
	
	public List<TxtMsgEntity> getAllTxtMessage();
	
	public TxtMsgEntity getTxtMessage(String msgId);
	
	public void getImgMessage(String msgId);
	
	public void getLinkMessage(String msgId);
	
	public void getLocationMessage(String msgId);
	
	public void getVoiceMessage(String msgId);
	
	public void getEventMessage(String msgId);
}
