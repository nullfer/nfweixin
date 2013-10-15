/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.sales.business.weixin;

import com.myapp.sales.business.weixin.botchat.MessageType;
import com.myapp.sales.mybatis.entity.MessageEntity;
import com.myapp.sales.mybatis.entity.TxtMsgEntity;
import com.myapp.struts.AppAction;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

/**
 *
 * @author citysky
 */
public class WeixinAction extends AppAction {

	private WeixinSvr weixinSvr;

	public WeixinSvr getWeixinSvr() {
		return weixinSvr;
	}

	public void setWeixinSvr(WeixinSvr weixinSvr) {
		this.weixinSvr = weixinSvr;
	}

	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setAttribute("user", "value");
		String sign = request.getParameter("test");
		if (sign == null) {
			if ("GET".equalsIgnoreCase(request.getMethod())) {
				return validSign(mapping, form, request, response);
			} else {
				String signature = request.getParameter("signature");
				// 随机字符串
				String echostr = request.getParameter("echostr");
				// 时间戳
				String timestamp = request.getParameter("timestamp");
				// 随机数
				String nonce = request.getParameter("nonce");
				logger.info("[Info]: GetMessage -" + signature);
				boolean isValid = getWeixinSvr().validateSign(timestamp, nonce, signature);
				logger.info("[Info]: CheckValid -" + isValid);
				if (isValid) {
					String result = getWeixinSvr().processRequest(request);
					logger.info("[Result]:" + result);
					response.getWriter().write(result);
				} else {
					logger.info("[Error]: 输入不合法！");
				}
				return mapping.findForward(null);
			}
		} else {
			String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
					+ "<xml><ToUserName><![CDATA[gh_014f373a5b6b]]></ToUserName>\n"
					+ "<FromUserName><![CDATA[oB-ELt0AsDNDN2sKrexvA7uW9pBQ]]></FromUserName>\n"
					+ "<CreateTime>1381456288</CreateTime>\n"
					+ "<MsgType><![CDATA[text]]></MsgType>\n"
					+ "<Content><![CDATA[hi]]></Content>\n"
					+ "<MsgId>5933309577813557283</MsgId>\n"
					+ "</xml>";
			Map<String, String> map = new HashMap<String, String>();
			SAXReader reader = new SAXReader();
			Document document = reader.read(new InputSource(new StringReader(xml)));
			Element root = document.getRootElement();
			// 得到根元素的所有子节点
			List<Element> elementList = root.elements();
			// 遍历所有子节点
			for (Element e : elementList) {
				map.put(e.getName(), e.getText());
			}
			String result = getWeixinSvr().processRequestMap(map);
			logger.info("[Result]:" + result);
			response.getWriter().write(result);
			return mapping.findForward(null);
		}
	}

	private ActionForward validSign(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 微信加密签名
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String signature = request.getParameter("signature");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");

		if (StringUtils.isEmpty(signature) || StringUtils.isEmpty(echostr) || StringUtils.isEmpty(timestamp) || StringUtils.isEmpty(nonce)) {
			String errmsg = getWeixinSvr().getMessage();
			PrintWriter writer = response.getWriter();
			logger.info("[Error]: 输入不合法！" + errmsg);
			writer.print(errmsg);
			return mapping.findForward(null);
		}
		boolean isValid = getWeixinSvr().validateSign(timestamp, nonce, signature);

		// 确认请求来至微信
		if (isValid) {
			response.getWriter().print(echostr);
		}
		return mapping.findForward(null);
	}

	public ActionForward initDB(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String service = System.getenv("VCAP_SERVICES");
//		service="{\"uhurufs-0.9\":[{\"name\":\"weixinfs\",\"label\":\"uhurufs-0.9\",\"plan\":\"free\",\"tags\":[\"Persistent filesystem service\",\"uhurufs-0.9\",\"uhurufs\"],\"credentials\":{\"name\":\"D4Ta4db89839148a48579959ea0a4ae90fae\",\"username\":\"US3Ri9jdp3d4ETib\",\"user\":\"US3Ri9jdp3d4ETib\",\"password\":\"P4SS5kcGHiefald6\",\"hostname\":\"192.168.6.4\",\"port\":49711,\"bind_opts\":{}}}],\"mysql-5.1\":[{\"name\":\"mysql\",\"label\":\"mysql-5.1\",\"plan\":\"free\",\"tags\":[\"relational\",\"mysql-5.1\",\"mysql\"],\"credentials\":{\"name\":\"dd86c2c45de0e44ffb5fe09d1cb28176e\",\"hostname\":\"192.168.4.110\",\"host\":\"192.168.4.110\",\"port\":3306,\"user\":\"urB6NFgaoFcd4\",\"username\":\"urB6NFgaoFcd4\",\"password\":\"pYc4fs0mMT1Rj\"}}]}";
//		JSON.parseArray(service)
		PrintWriter writer = response.getWriter();
		logger.info("[Info]:" + service);
		writer.print(service);
		return mapping.findForward(null);
	}

	public ActionForward msgRecord(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<TxtMsgEntity> messages = getWeixinSvr().getAllTxtMessages();
		request.setAttribute("recordList", messages);
		return mapping.findForward("record_list");
	}
	
	public ActionForward msgType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<MessageType> messages = getWeixinSvr().getAllMessageType();
		request.setAttribute("recordList", messages);
		return mapping.findForward("msg_list");
	}
}
