/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.sales.mybatis;

import com.alibaba.fastjson.JSON;
import com.myapp.sales.business.weixin.req.ReqTextMessage;
import com.myapp.sales.mybatis.converter.MessageConverter;
import com.myapp.sales.mybatis.entity.MessageEntity;
import com.myapp.sales.mybatis.entity.TxtMsgEntity;
import com.myapp.sales.mybatis.mapper.MessageMapper;
import com.myapp.sales.util.SequenceUtil;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 *
 * @author citysky
 */
public class Test {

	static SqlSessionFactory sqlSessionFactory = null;

	static {
		sqlSessionFactory = MybatisUtil.getSqlSessionFactory();
	}

	public static void main(String args[]) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			MessageMapper msgMapper = sqlSession.getMapper(MessageMapper.class);
			ReqTextMessage message = new ReqTextMessage();
			message.setContent("Wahahahah2! Wahahahah2!");
			message.setCreateTime(new Date().getTime());
			message.setFromUserName("fromUserNameTest1");
			message.setMsgId(SequenceUtil.getSequenceLong());
			message.setMsgType("text");
			message.setToUserName("toUserNameTest2");
			System.out.println(JSON.toJSONString(message));
			TxtMsgEntity txtMsg = MessageConverter.makeMessageEntity(message, TxtMsgEntity.class);
			msgMapper.saveMessage(txtMsg);
			msgMapper.saveTxtMessage(txtMsg);
			sqlSession.commit();
		} catch (Exception ex) {
			Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			sqlSession.close();
		}
	}
}
