/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.sales.business.weixin;

import com.myapp.sales.ai.DefaultMessageSearch;
import com.myapp.sales.ai.IMessageSearch;
import com.myapp.sales.business.weixin.botchat.MessageType;
import com.myapp.sales.business.weixin.botchat.MessageUtil;
import com.myapp.sales.business.weixin.req.EventMessage;
import com.myapp.sales.business.weixin.req.ReqMessage;
import com.myapp.sales.business.weixin.req.ReqTextMessage;
import com.myapp.sales.business.weixin.resp.RespTextMessage;
import com.myapp.sales.mybatis.entity.MessageEntity;
import com.myapp.sales.mybatis.entity.TxtMsgEntity;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author citysky
 */
public class WeixinSvr {

	private Log logger = LogFactory.getLog(this.getClass());
	private static final String TOKEN = "sitech";
	private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
		'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

	public boolean validateSign(String timestamp, String nonce, String signature) throws NoSuchAlgorithmException {
		String[] str = {TOKEN, timestamp, nonce};
		Arrays.sort(str); // �ֵ�������
		String bigStr = str[0] + str[1] + str[2];
		// SHA1����
		MessageDigest digest = MessageDigest.getInstance("SHA1");
		digest.update(bigStr.getBytes());
		return getFormattedText(digest.digest()).equals(signature);
	}

	private String getFormattedText(byte[] bytes) {
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		// ������ת����ʮ�����Ƶ��ַ�����ʽ
		for (int j = 0; j < len; j++) {
			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buf.toString();

	}

	public String getMessage() {
		return "���Է������Ϣ�������Ŷ��";
	}

	public String processRequestMap(Map<String, String> map) {
		String respMessage = null;
		try {
			// Ĭ�Ϸ��ص��ı���Ϣ����
			String respContent = "�������쳣�����Ժ��ԣ�";
			logger.info("Begin to process Request!");
			//xml�������
			ReqMessage reqMessage = MessageUtil.parseXml2Message(map);

			// ���ͷ��ʺţ�open_id��
			String fromUserName = reqMessage.getFromUserName();
			// �����ʺ�
			String toUserName = reqMessage.getToUserName();
			// ��Ϣ����
			String msgType = reqMessage.getMsgType();
			// ���͵���Ϣ

			// �ظ��ı���Ϣ
			RespTextMessage textMessage = new RespTextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime() / 1000);
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			IMessageSearch msgSearch = new DefaultMessageSearch();

			// �ı���Ϣ
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				String content = ((ReqTextMessage) reqMessage).getContent();
				respContent = msgSearch.messageSearch(content);
			} // ͼƬ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "�����͵���ͼƬ��Ϣ��";
			} // ����λ����Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "�����ڵ����ǲ�ƽ����̫ƽׯ��, ����464/441 �ɵ�����ͨԷ�ƶ�Ӫҵ���������»��\n"
						+ "1���ֻ���ֵ���ѣ���100��50\n"
						+ "2���������ֻ���iPhone5|Sumsung Notes4���ɻ��\n"
						+ "3����Լ�Żݣ�Լ����68Ԫ��98Ԫ�ײ�\n";
			} // ������Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "�����͵���������Ϣ��";
			} // ��Ƶ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "�����͵�����Ƶ��Ϣ��";
			} // �¼�����
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// �¼�����
				String eventType = ((EventMessage) reqMessage).getEvent();
				// ����
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "������Ӫ������ƽ̨��лл���Ĺ�ע�����Ǻ�������Ϊ���ṩ����ķ�����Ҫ������������:?";
				} // ȡ������
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO ȡ�����ĺ��û����ղ������ںŷ��͵���Ϣ����˲���Ҫ�ظ���Ϣ
				} // �Զ���˵�����¼�
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// TODO �Զ���˵�Ȩû�п��ţ��ݲ����������Ϣ
				}
			}
			MessageUtil.saveReqMessage(reqMessage);

			textMessage.setContent(respContent);
			MessageUtil.saveResponseMessage(textMessage);
			respMessage = MessageUtil.textMessageToXml(textMessage);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return respMessage;
	}

	/**
	 * ����΢�ŷ���������
	 *
	 * @param request
	 * @return
	 */
	public String processRequest(HttpServletRequest request) throws Exception {
		return processRequestMap(MessageUtil.parseXml(request));
	}

	public List<MessageEntity> getAllMessages() {
		return MessageUtil.getAllMesages();
	}

	public List<TxtMsgEntity> getAllTxtMessages() {
		return MessageUtil.getAllTxtMesages();
	}
	
	public List<MessageType> getAllMessageType(){
		return MessageUtil.getAllMessageType();
	}
}
