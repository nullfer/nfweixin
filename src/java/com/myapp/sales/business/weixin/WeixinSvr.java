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
		Arrays.sort(str); // 字典序排序
		String bigStr = str[0] + str[1] + str[2];
		// SHA1加密
		MessageDigest digest = MessageDigest.getInstance("SHA1");
		digest.update(bigStr.getBytes());
		return getFormattedText(digest.digest()).equals(signature);
	}

	private String getFormattedText(byte[] bytes) {
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		// 把密文转换成十六进制的字符串形式
		for (int j = 0; j < len; j++) {
			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buf.toString();

	}

	public String getMessage() {
		return "来自服务的消息：你错了哦，";
	}

	public String processRequestMap(Map<String, String> map) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";
			logger.info("Begin to process Request!");
			//xml请求解析
			ReqMessage reqMessage = MessageUtil.parseXml2Message(map);

			// 发送方帐号（open_id）
			String fromUserName = reqMessage.getFromUserName();
			// 公众帐号
			String toUserName = reqMessage.getToUserName();
			// 消息类型
			String msgType = reqMessage.getMsgType();
			// 发送的消息

			// 回复文本消息
			RespTextMessage textMessage = new RespTextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime() / 1000);
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			IMessageSearch msgSearch = new DefaultMessageSearch();

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				String content = ((ReqTextMessage) reqMessage).getContent();
				respContent = msgSearch.messageSearch(content);
			} // 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
			} // 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您所在地区是昌平区北太平庄街, 乘坐464/441 可到达天通苑移动营业厅参与以下活动：\n"
						+ "1、手机充值赠费：充100送50\n"
						+ "2、入网送手机：iPhone5|Sumsung Notes4轻松获得\n"
						+ "3、合约优惠：约消费68元送98元套餐\n";
			} // 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			} // 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！";
			} // 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = ((EventMessage) reqMessage).getEvent();
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "这里是营销服务平台，谢谢您的关注！我们后续将会为您提供更多的服务，需要帮助，请输入:?";
				} // 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				} // 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// TODO 自定义菜单权没有开放，暂不处理该类消息
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
	 * 处理微信发来的请求
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
