/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.sales.business.weixin.botchat;

import com.alibaba.fastjson.JSON;
import com.myapp.sales.business.weixin.req.ImageMessage;
import com.myapp.sales.business.weixin.req.LinkMessage;
import com.myapp.sales.business.weixin.req.LocationMessage;
import com.myapp.sales.business.weixin.req.ReqMessage;
import com.myapp.sales.business.weixin.req.ReqTextMessage;
import com.myapp.sales.business.weixin.req.VoiceMessage;
import com.myapp.sales.business.weixin.resp.NewsMessage;
import com.myapp.sales.business.weixin.resp.MusicMessage;
import com.myapp.sales.business.weixin.resp.Article;
import com.myapp.sales.business.weixin.resp.RespMessage;
import com.myapp.sales.business.weixin.resp.RespTextMessage;
import com.myapp.sales.mybatis.MybatisUtil;
import com.myapp.sales.mybatis.Test;
import com.myapp.sales.mybatis.converter.MessageConverter;
import com.myapp.sales.mybatis.entity.EventMsgEntity;
import com.myapp.sales.mybatis.entity.ImgMsgEntity;
import com.myapp.sales.mybatis.entity.LinkMsgEntity;
import com.myapp.sales.mybatis.entity.LocMsgEntity;
import com.myapp.sales.mybatis.entity.MessageEntity;
import com.myapp.sales.mybatis.entity.TxtMsgEntity;
import com.myapp.sales.mybatis.entity.VoiceMsgEntity;
import com.myapp.sales.mybatis.mapper.MessageBasicMapper;
import com.myapp.sales.mybatis.mapper.MessageMapper;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 *
 * @author citysky
 */
public class MessageUtil {

	private static Log logger = LogFactory.getLog(MessageUtil.class);
	/**
	 * 返回消息类型：文本
	 */
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";
	/**
	 * 返回消息类型：音乐
	 */
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
	/**
	 * 返回消息类型：图文
	 */
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";
	/**
	 * 请求消息类型：文本
	 */
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";
	/**
	 * 请求消息类型：图片
	 */
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
	/**
	 * 请求消息类型：链接
	 */
	public static final String REQ_MESSAGE_TYPE_LINK = "link";
	/**
	 * 请求消息类型：地理位置
	 */
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
	/**
	 * 请求消息类型：音频
	 */
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
	/**
	 * 请求消息类型：推送
	 */
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";
	/**
	 * 事件类型：subscribe(订阅)
	 */
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
	/**
	 * 事件类型：unsubscribe(取消订阅)
	 */
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
	/**
	 * 事件类型：CLICK(自定义菜单点击事件)
	 */
	public static final String EVENT_TYPE_CLICK = "CLICK";

	/**
	 * 解析微信发来的请求（XML）
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
		// 将解析结果存储在HashMap中
		logger.info("parseXml Begin");
		Map<String, String> map = new HashMap<String, String>();
		// 从request中取得输入流
		InputStream inputStream = request.getInputStream();
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		logger.info(document.asXML());
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();
		// 遍历所有子节点
		for (Element e : elementList) {
			map.put(e.getName(), e.getText());
		}
		// 释放资源
		inputStream.close();
		return map;
	}

	public static ReqMessage parseXml2Message(Map<String, String> map) throws Exception {
		logger.info("parseMessage Begin");
		ReqMessage message;
		String msgType = map.get("MsgType");
		if (REQ_MESSAGE_TYPE_TEXT.equals(msgType)) {
			ReqTextMessage msg = new ReqTextMessage();
			msg.setContent(map.get("Content"));
			message = msg;
		} else if (REQ_MESSAGE_TYPE_IMAGE.equals(msgType)) {
			ImageMessage msg = new ImageMessage();
			msg.setPicUrl(map.get("PicUrl"));
			message = msg;
		} else if (REQ_MESSAGE_TYPE_LINK.equals(msgType)) {
			LinkMessage msg = new LinkMessage();
			msg.setDescription(map.get("Description"));
			msg.setTitle(map.get("Title"));
			msg.setUrl(map.get("Url"));
			message = msg;
		} else if (REQ_MESSAGE_TYPE_LOCATION.equals(msgType)) {
			LocationMessage msg = new LocationMessage();
			msg.setLocation_X(map.get("Location_X"));
			msg.setLocation_Y(map.get("Location_Y"));
			msg.setScale(map.get("Scale"));
			msg.setLabel(map.get("Label"));
			message = msg;
		} else if (REQ_MESSAGE_TYPE_VOICE.equals(msgType)) {
			VoiceMessage msg = new VoiceMessage();
			msg.setMediaId(map.get("MediaId"));
			msg.setFormat(map.get("Format"));
			message = msg;
		} else {
			message = new ReqMessage();
		}
		message.setMsgId(Long.parseLong(map.get("MsgId")));
		message.setFromUserName(map.get("FromUserName"));
		message.setMsgType(msgType);
		message.setCreateTime(Long.parseLong(map.get("CreateTime")));
		message.setToUserName(map.get("ToUserName"));
		logger.info(JSON.toJSONString(message));
		return message;
	}

	public static ReqMessage parseXml2Message(HttpServletRequest request) throws Exception {
		return parseXml2Message(parseXml(request));

	}

	/**
	 * 文本消息对象转换成xml
	 *
	 * @param textMessage 文本消息对象
	 * @return xml
	 */
	public static String textMessageToXml(RespTextMessage textMessage) {
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}

	/**
	 * 音乐消息对象转换成xml
	 *
	 * @param musicMessage 音乐消息对象
	 * @return xml
	 */
	public static String musicMessageToXml(MusicMessage musicMessage) {
		xstream.alias("xml", musicMessage.getClass());
		return xstream.toXML(musicMessage);
	}

	/**
	 * 图文消息对象转换成xml
	 *
	 * @param newsMessage 图文消息对象
	 * @return xml
	 */
	public static String newsMessageToXml(NewsMessage newsMessage) {
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(newsMessage);
	}
	/**
	 * 扩展xstream，使其支持CDATA块
	 *
	 * @date 2013-05-19
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		@Override
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;

				@SuppressWarnings("unchecked")
				@Override
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				@Override
				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});

	public static void saveReqMessage(ReqMessage message) throws Exception {
		MessageEntity entity = null;
		if (message instanceof ReqTextMessage) {
			entity = MessageConverter.makeMessageEntity(message, TxtMsgEntity.class);
		} else if (message instanceof ImageMessage) {
			entity = MessageConverter.makeMessageEntity(message, ImgMsgEntity.class);
		} else if (message instanceof LinkMessage) {
			entity = MessageConverter.makeMessageEntity(message, LinkMsgEntity.class);
		} else if (message instanceof LocationMessage) {
			entity = MessageConverter.makeMessageEntity(message, LocMsgEntity.class);
		} else if (message instanceof VoiceMessage) {
			entity = MessageConverter.makeMessageEntity(message, VoiceMsgEntity.class);
		}
		saveMessage(entity);
	}

	public static void saveResponseMessage(RespMessage message) throws Exception {
		MessageEntity entity = null;
		if (message instanceof RespTextMessage) {
			entity = MessageConverter.makeMessageEntity(message, TxtMsgEntity.class);
		} else if (message instanceof MusicMessage) {
			entity = MessageConverter.makeMessageEntity(message, ImgMsgEntity.class);
		} else if (message instanceof NewsMessage) {
			entity = MessageConverter.makeMessageEntity(message, LinkMsgEntity.class);
		}
		saveMessage(entity);
	}

	private static void saveMessage(MessageEntity message) {
		if (message == null) {
			logger.info("No message record!");
			return;
		}
		SqlSession sqlSession = MybatisUtil.getSqlSessionFactory().openSession();
		try {
			MessageMapper msgMapper = sqlSession.getMapper(MessageMapper.class);
			System.out.println(JSON.toJSONString(message));
			msgMapper.saveMessage(message);
			if (message instanceof TxtMsgEntity) {
				msgMapper.saveTxtMessage((TxtMsgEntity) message);
			} else if (message instanceof ImgMsgEntity) {
				msgMapper.saveImgMessage((ImgMsgEntity) message);
			} else if (message instanceof LinkMsgEntity) {
				msgMapper.saveLinkMessage((LinkMsgEntity) message);
			} else if (message instanceof LocMsgEntity) {
				msgMapper.saveLocationMessage((LocMsgEntity) message);
			} else if (message instanceof VoiceMsgEntity) {
				msgMapper.saveVoiceMessage((VoiceMsgEntity) message);
			} else if (message instanceof EventMsgEntity) {
				msgMapper.saveEventMessage((EventMsgEntity) message);
			}

			sqlSession.commit();
		} catch (Exception ex) {
			Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			sqlSession.close();
		}
	}

	public static List<MessageEntity> getAllMesages() {
		SqlSession sqlSession = MybatisUtil.getSqlSessionFactory().openSession();
		List<MessageEntity> messages = null;
		try {
			MessageMapper msgMapper = sqlSession.getMapper(MessageMapper.class);
			messages = msgMapper.getAllMessage();
		} catch (Exception ex) {
			Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			sqlSession.close();
		}
		return messages;
	}
	
	public static List<TxtMsgEntity> getAllTxtMesages() {
		SqlSession sqlSession = MybatisUtil.getSqlSessionFactory().openSession();
		List<TxtMsgEntity> messages = null;
		try {
			MessageMapper msgMapper = sqlSession.getMapper(MessageMapper.class);
			messages = msgMapper.getAllTxtMessage();
		} catch (Exception ex) {
			Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			sqlSession.close();
		}
		return messages;
	}

	public static List<MessageType> getAllMessageType() {
		SqlSession sqlSession = MybatisUtil.getSqlSessionFactory().openSession();
		List<MessageType> messages = null;
		try {
			MessageBasicMapper msgMapper = sqlSession.getMapper(MessageBasicMapper.class);
			messages = msgMapper.getAllMessageType();
		} catch (Exception ex) {
			Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			sqlSession.close();
		}
		return messages;
	}
}
