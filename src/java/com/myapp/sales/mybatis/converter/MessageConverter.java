/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.sales.mybatis.converter;

import com.myapp.sales.business.weixin.req.ReqMessage;
import com.myapp.sales.business.weixin.resp.RespMessage;
import com.myapp.sales.mybatis.entity.ImgMsgEntity;
import com.myapp.sales.mybatis.entity.LinkMsgEntity;
import com.myapp.sales.mybatis.entity.LocMsgEntity;
import com.myapp.sales.mybatis.entity.MessageEntity;
import com.myapp.sales.mybatis.entity.TxtMsgEntity;
import com.myapp.sales.mybatis.entity.VoiceMsgEntity;
import com.myapp.sales.util.SequenceUtil;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author citysky
 */
public class MessageConverter {

	public static <T extends MessageEntity> T makeMessageEntity(ReqMessage mess, Class<T> clazz) throws Exception {
		T entity = clazz.newInstance();
		//mess.getMsgId(), mess.getToUserName(), mess.getFromUserName(), mess.getCreateTime(), mess.getMsgType(), false);
		entity.setMsgId(mess.getMsgId());
		entity.setToUserName(mess.getToUserName());
		entity.setFromUserName(mess.getFromUserName());
		entity.setCreateTime(mess.getCreateTime());
		entity.setMsgType(mess.getMsgType());
		entity.setPostSign(false);
		initParams(clazz, entity, mess);
		return entity;
	}

	public static <T extends MessageEntity> T makeMessageEntity(RespMessage mess, Class<T> clazz) throws Exception {
		T entity = clazz.newInstance();
		entity.setMsgId(Long.parseLong(SequenceUtil.getSequence()));
		entity.setToUserName(mess.getToUserName());
		entity.setFromUserName(mess.getFromUserName());
		entity.setCreateTime(mess.getCreateTime());
		entity.setMsgType(mess.getMsgType());
		entity.setPostSign(true);
		entity.setFuncFlag(0);
		initParams(clazz, entity, mess);
		return entity;
	}

	private static <T extends MessageEntity> void initParams(Class<T> clazz, T entity, Object mess) throws Exception {
		String clazzName = clazz.getName();
		if (TxtMsgEntity.class.getName().equals(clazzName)) {
			invokeClassSetMethod(clazz, entity, "content", String.class, invokeClassGetMethod(mess, "content"));
		} else if (ImgMsgEntity.class.getName().equals(clazzName)) {
			invokeClassSetMethod(clazz, entity, "picUrl", String.class, invokeClassGetMethod(mess, "picUrl"));
		} else if (LocMsgEntity.class.getName().equals(clazzName)) {
			invokeClassSetMethod(clazz, entity, "location_X", String.class, invokeClassGetMethod(mess, "location_X"));
			invokeClassSetMethod(clazz, entity, "location_Y", String.class, invokeClassGetMethod(mess, "location_Y"));
			invokeClassSetMethod(clazz, entity, "scale", String.class, invokeClassGetMethod(mess, "scale"));
			invokeClassSetMethod(clazz, entity, "label", String.class, invokeClassGetMethod(mess, "label"));
		} else if (LinkMsgEntity.class.getName().equals(clazzName)) {
			invokeClassSetMethod(clazz, entity, "description", String.class, invokeClassGetMethod(mess, "description"));
			invokeClassSetMethod(clazz, entity, "title", String.class, invokeClassGetMethod(mess, "title"));
			invokeClassSetMethod(clazz, entity, "url", String.class, invokeClassGetMethod(mess, "url"));
		} else if (VoiceMsgEntity.class.getName().equals(clazzName)) {
			invokeClassSetMethod(clazz, entity, "mediaId", String.class, invokeClassGetMethod(mess, "mediaId"));
			invokeClassSetMethod(clazz, entity, "format", String.class, invokeClassGetMethod(mess, "format"));
		}
	}

	public static Object invokeClassGetMethod(Object obj, String propName) throws Exception {
		return invokeClassGetMethod(obj.getClass(), obj, propName);
	}

	public static Object invokeClassGetMethod(Class<?> clazz, Object obj, String propName) throws Exception {
		return clazz.getMethod("get" + StringUtils.capitalise(propName)).invoke(obj);
	}

	public static void invokeClassSetMethod(Object obj, String propName, Class<?> paramType, Object paramValue) throws Exception {
		invokeClassSetMethod(obj.getClass(), obj, propName, paramType, paramValue);
	}

	public static void invokeClassSetMethod(Class<?> clazz, Object obj, String propName, Class<?> paramType, Object paramValue) throws Exception {
		clazz.getMethod("set" + StringUtils.capitalise(propName), paramType).invoke(obj, paramValue);

	}
}
