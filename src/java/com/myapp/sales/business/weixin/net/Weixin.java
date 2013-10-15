package com.myapp.sales.business.weixin.net;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Weixin {

	private final static Log log = LogFactory.getLog(Weixin.class);
	public final static String HOST = "http://mp.weixin.qq.com";
	public final static String LOGIN_URL = "http://mp.weixin.qq.com/cgi-bin/login?lang=zh_CN";
	public final static String INDEX_URL = "http://mp.weixin.qq.com/cgi-bin/indexpage?t=wxm-index&lang=zh_CN";
	public final static String FANS_URL = "http://mp.weixin.qq.com/cgi-bin/contactmanagepage?t=wxm-friend&lang=zh_CN&pagesize=10&pageidx=0&type=0&groupid=0";
	public final static String LOGOUT_URL = "http://mp.weixin.qq.com/cgi-bin/logout?t=wxm-logout&lang=zh_CN";
	public final static String DOWNLOAD_URL = "http://mp.weixin.qq.com/cgi-bin/downloadfile?";
	public final static String VERIFY_CODE = "http://mp.weixin.qq.com/cgi-bin/verifycode?";
	public final static String POST_MSG = "https://mp.weixin.qq.com/cgi-bin/masssend?t=ajax-response";
	public final static String VIEW_HEAD_IMG = "http://mp.weixin.qq.com/cgi-bin/viewheadimg";
	public final static String GET_IMG_DATA = "http://mp.weixin.qq.com/cgi-bin/getimgdata";
	public final static String GET_REGIONS = "http://mp.weixin.qq.com/cgi-bin/getregions";
	public final static String GET_MESSAGE = "http://mp.weixin.qq.com/cgi-bin/getmessage";
	public final static String OPER_ADVANCED_FUNC = "http://mp.weixin.qq.com/cgi-bin/operadvancedfunc";
	public final static String MASSSEND_PAGE = "http://mp.weixin.qq.com/cgi-bin/masssendpage";
	public final static String FILE_MANAGE_PAGE = "http://mp.weixin.qq.com/cgi-bin/filemanagepage";
	public final static String OPERATE_APPMSG = "https://mp.weixin.qq.com/cgi-bin/operate_appmsg";
	public final static String FMS_TRANSPORT = "http://mp.weixin.qq.com/cgi-bin/fmstransport";
	public final static String CONTACT_MANAGE_PAGE = "http://mp.weixin.qq.com/cgi-bin/contactmanagepage";
	public final static String OPER_SELF_MENU = "http://mp.weixin.qq.com/cgi-bin/operselfmenu";
	public final static String REPLY_RULE_PAGE = "http://mp.weixin.qq.com/cgi-bin/replyrulepage";
	public final static String SINGLE_MSG_PAGE = "http://mp.weixin.qq.com/cgi-bin/singlemsgpage";
	public final static String USER_INFO_PAGE = "http://mp.weixin.qq.com/cgi-bin/userinfopage";
	public final static String DEV_APPLY = "http://mp.weixin.qq.com/cgi-bin/devapply";
	public final static String UPLOAD_MATERIAL = "https://mp.weixin.qq.com/cgi-bin/uploadmaterial?cgi=uploadmaterial&type=2&token=416919388&t=iframe-uploadfile&lang=zh_CN&formId=1";
	public final static String USER_AGENT_H = "User-Agent";
	public final static String REFERER_H = "Referer";
	public final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.172 Safari/537.22";
	public final static String UTF_8 = "UTF-8";
	private HttpClient client = new HttpClient();
	private Cookie[] cookies;
	private String cookiestr;
	private String token;
	private int loginErrCode;
	private String loginErrMsg;
	private int msgSendCode;
	private String msgSendMsg;
	private String loginUser;
	private String loginPwd;
	public boolean isLogin = false;

	public Weixin(String user, String pwd) {
		this.loginUser = user;
		this.loginPwd = pwd;
	}

	public Cookie[] getCookies() {
		return cookies;
	}

	public void setCookies(Cookie[] cookies) {
		this.cookies = cookies;
	}

	public String getCookiestr() {
		return cookiestr;
	}

	public void setCookiestr(String cookiestr) {
		this.cookiestr = cookiestr;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getLoginErrCode() {
		return loginErrCode;
	}

	public void setLoginErrCode(int loginErrCode) {
		this.loginErrCode = loginErrCode;
	}

	public String getLoginErrMsg() {
		return loginErrMsg;
	}

	public void setLoginErrMsg(String loginErrMsg) {
		this.loginErrMsg = loginErrMsg;
	}

	public int getMsgSendCode() {
		return msgSendCode;
	}

	public void setMsgSendCode(int msgSendCode) {
		this.msgSendCode = msgSendCode;
	}

	public String getMsgSendMsg() {
		return msgSendMsg;
	}

	public void setMsgSendMsg(String msgSendMsg) {
		this.msgSendMsg = msgSendMsg;
	}

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	/**
	 * ��¼,��¼ʧ�ܻ��ظ������¼
	 */
	public void login() {
		boolean bool = _login();
		while (!bool) {
			String info = "����¼ʧ�ܡ���������룺" + this.loginErrMsg + "�����˺ţ�"
					+ this.loginUser + "�����ڳ������µ�¼....";
			log.debug(info);
			System.out.println(info);
			bool = _login();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				bool = _login();
			}

		}
		System.out.println("��½�ɹ���");
	}

	/**
	 * ���͵�¼��Ϣ,��¼cookie����¼״̬��token����Ϣ
	 *
	 * @return
	 */
	private boolean _login() {
		try {
			PostMethod post = new PostMethod(LOGIN_URL);
			post.setRequestHeader(USER_AGENT_H, USER_AGENT);
			NameValuePair[] params = new NameValuePair[]{
				new NameValuePair("username", this.loginUser),
				new NameValuePair("pwd", DigestUtils.md5Hex(this.loginPwd.getBytes())), new NameValuePair("f", "json"),
				new NameValuePair("imagecode", "")};
			post.setQueryString(params);
			int status = client.executeMethod(post);
			if (status == HttpStatus.SC_OK) {
				String ret = post.getResponseBodyAsString();
				LoginJson retcode = JSON.parseObject(ret, LoginJson.class);
				if (retcode.getRet() == 302 && retcode.getErrCode() == 0) {
					this.cookies = client.getState().getCookies();
					StringBuilder cookie = new StringBuilder();
					for (Cookie c : client.getState().getCookies()) {
						cookie.append(c.getName()).append("=")
								.append(c.getValue()).append(";");
					}
					this.cookiestr = cookie.toString();
					this.isLogin = true;
					this.token = getToken(retcode.getErrMsg());
					return true;
				}
				int errCode = retcode.getErrCode();
				this.loginErrCode = errCode;
				switch (errCode) {

					case -1:
						this.loginErrMsg = "ϵͳ����";
						return false;
					case -2:
						this.loginErrMsg = "�ʺŻ��������";
						return false;
					case -3:
						this.loginErrMsg = "�������";
						return false;
					case -4:
						this.loginErrMsg = "�����ڸ��ʻ�";
						return false;
					case -5:
						this.loginErrMsg = "��������";
						return false;
					case -6:
						this.loginErrMsg = "��Ҫ������֤��";
						return false;
					case -7:
						this.loginErrMsg = "���ʺ��Ѱ�˽��΢�źţ��������ڹ���ƽ̨��¼";
						return false;
					case -8:
						this.loginErrMsg = "�����Ѵ���";
						return false;
					case -32:
						this.loginErrMsg = "��֤���������";
						return false;
					case -200:
						this.loginErrMsg = "��Ƶ���ύ������ϣ����ʺű��ܾ���¼";
						return false;
					case -94:
						this.loginErrMsg = "��ʹ�������½";
						return false;
					case 10:
						this.loginErrMsg = "�ù��ڻ�����Ѿ����ڣ��޷��ٵ�¼ʹ��";
						return false;
					case 65201:
					case 65202:
						this.loginErrMsg = "�ɹ���½��������ת...";
						return true;
					case 0:
						this.loginErrMsg = "�ɹ���½��������ת...";
						return true;
					default:
						this.loginErrMsg = "δ֪�ķ���";
						return false;
				}
			}
		} catch (Exception e) {
			String info = "����¼ʧ�ܡ��������쳣��" + e.getMessage() + "��";
			System.err.println(info);
			log.debug(info);
			log.info(info);
			return false;
		}
		return false;
	}

	/**
	 * �ӵ�¼�ɹ�����Ϣ�з����token��Ϣ
	 *
	 * @param s
	 * @return
	 */
	private String getToken(String s) {
		try {
			if (StringUtils.isBlank(s)) {
				return null;
			}
			String[] ss = StringUtils.split(s, "?");
			String[] params = null;
			if (ss.length == 2) {
				if (!StringUtils.isBlank(ss[1])) {
					params = StringUtils.split(ss[1], "&");
				}
			} else if (ss.length == 1) {
				if (!StringUtils.isBlank(ss[0]) && ss[0].indexOf("&") != -1) {
					params = StringUtils.split(ss[0], "&");
				}
			} else {
				return null;
			}
			for (String param : params) {
				if (StringUtils.isBlank(param)) {
					continue;
				}
				String[] p = StringUtils.split(param, "=");
				if (null != p && p.length == 2
						&& StringUtils.equalsIgnoreCase(p[0], "token")) {
					return p[1];
				}

			}
		} catch (Exception e) {
			String info = "������Tokenʧ�ܡ��������쳣��" + e.getMessage() + "��";
			System.err.println(info);
			log.debug(info);
			log.info(info);
			return null;
		}
		return null;
	}

	/**
	 * ��ȡ��ҳ
	 *
	 * @throws org.apache.commons.httpclient.HttpException
	 *
	 * @throws java.io.IOException
	 */
	public void index() throws HttpException, IOException {
		GetMethod get = new GetMethod(INDEX_URL);
		get.setRequestHeader(USER_AGENT_H, USER_AGENT);
		get.setRequestHeader("Cookie", this.cookiestr);
		int status = client.executeMethod(get);
		if (status == HttpStatus.SC_OK) {
			System.out.println(get.getResponseBodyAsString());
		}
	}

	/**
	 * �ǳ�����
	 *
	 * @throws org.apache.commons.httpclient.HttpException
	 *
	 * @throws java.io.IOException
	 */
	public void logout() throws HttpException, IOException {
		GetMethod get = new GetMethod(LOGOUT_URL);
		get.setRequestHeader(USER_AGENT_H, USER_AGENT);
		get.setRequestHeader("Cookie", this.cookiestr);
		int status = client.executeMethod(get);
		if (status == HttpStatus.SC_OK) {
			System.err.println("-----------ע����¼�ɹ�-----------");
		}
	}

	/**
	 * ��ȡ��֤��
	 *
	 * @throws org.apache.commons.httpclient.HttpException
	 *
	 * @throws java.io.IOException
	 */
	public InputStream code() throws HttpException, IOException {
		GetMethod get = new GetMethod(VERIFY_CODE);
		get.setRequestHeader(USER_AGENT_H, USER_AGENT);
		get.setRequestHeader("Cookie", this.cookiestr);
		NameValuePair[] params = new NameValuePair[]{
			new NameValuePair("username", this.loginUser),
			new NameValuePair("r", "1365318662649")};
		get.setQueryString(params);
		int status = client.executeMethod(get);
		if (status == HttpStatus.SC_OK) {
			return get.getResponseBodyAsStream();
		}
		return null;
	}

	/**
	 * ��÷�˿����,��������ɹ��᷵�ط�˿�����������ʧ���򷵻�-1
	 *
	 * @return
	 */
	public int getFansCount() {
		try {
			String paramStr = "?t=wxm-friend&token=" + this.token
					+ "&lang=zh_CN&pagesize=10&pageidx=0&type=0&groupid=0";
			if (!this.isLogin) {
				this._login();
			}
			if (this.isLogin) {
				GetMethod get = new GetMethod(CONTACT_MANAGE_PAGE + paramStr);
				get.setRequestHeader(REFERER_H, INDEX_URL);
				get.setRequestHeader("Cookie", this.cookiestr);
				int status = client.executeMethod(get);
				if (status == HttpStatus.SC_OK) {
					return parseFansCount(get.getResponseBodyAsString());
				}
				return -1;
			}
		} catch (Exception e) {
			String info = "����ȡ��˿��ʧ�ܡ������ܵ�¼���ڡ�";
			System.err.println(info);
			log.debug(info);
			log.info(info);
			return -1;
		}
		return -1;
	}

	/**
	 * �ӷ����ı�����ȡ��˿����
	 *
	 * @param text
	 * @return
	 */
	private int parseFansCount(String text) {
		try {
			StringBuilder json = new StringBuilder();
			final String start = "DATA.groupList =";
			for (int i = text.indexOf(start) + start.length(), len = text
					.length(); i < len; i++) {
				char ci = text.charAt(i);
				if (ci == ';') {
					break;
				}
				json.append(text.charAt(i));
			}
			String txt = json.toString().replaceAll("[*]1", "")
					.replaceAll("defaultGroupName\\[0\\] \\|\\|", "")
					.replaceAll("defaultGroupName\\[1\\] \\|\\|", "")
					.replaceAll("defaultGroupName\\[2\\] \\|\\|", "")
					.replaceAll("defaultGroupName\\[100\\] \\|\\|", "");
			List<FansCount> fans = JSON.parseArray(txt, FansCount.class);
			if (null != fans && !fans.isEmpty()) {
				for (FansCount fan : fans) {
					if (fan.getId() == 0) {
						return fan.getNum();
					}
				}
			}
		} catch (Exception e) {
			String info = "��������˿��ʧ�ܡ� " + "\t\n���ı�����\t\n" + text + "\t\n"
					+ "�������쳣��" + e.getMessage() + "��";
			System.err.println(info);
			log.debug(info);
			log.info(info);
			return -1;
		}
		return -1;
	}

	/**
	 *
	 * <strong>Ⱥ����Ϣ</strong>
	 * <p>
	 * ������˵��<br>
	 * 0�����ͳɹ�<br>
	 * 64004:�����Ⱥ�������ѵ����޷�Ⱥ��<br>
	 * -20000:���󱻽�ֹ������ϸ���token�Ƿ�Ϸ�<br>
	 * </p>
	 * <p>
	 * ��ͨ��msgSendCodeȡ�÷���״̬��
	 * </p>
	 *
	 * @by liaokai
	 *
	 */
	/**
	 * @param form
	 * @param type
	 * @return
	 */
	public boolean msgSend(MsgForm form, MsgType type) {
		try {
			if (!this.isLogin) {
				this._login();
			}
			if (this.isLogin) {
				form.setToken(this.token);
				PostMethod post = new PostMethod(POST_MSG);
				post.setRequestHeader(USER_AGENT_H, USER_AGENT);
				post.setRequestHeader(REFERER_H, INDEX_URL);
				post.setRequestHeader("Cookie", this.cookiestr);
				NameValuePair[] params = null;
				Part[] parts;
				switch (type) {
					case TEXT:
						parts = new Part[]{
							new StringPart("content", form.getContent(), "UTF-8"),
							new StringPart("type", MsgType.TEXT.getType() + ""),
							new StringPart("error", form.getError()),
							new StringPart("needcomment", form.getNeedcomment()),
							new StringPart("groupid", form.getGroupid()),
							new StringPart("sex", form.getSex()),
							new StringPart("country", form.getCountry()),
							new StringPart("province", form.getProvince()),
							new StringPart("city", form.getCity()),
							new StringPart("token", form.getToken()),
							new StringPart("ajax", form.getAjax()),
							new StringPart("t", "ajax-response")};
						break;
					case IMAGE_TEXT:
						ImgTextMsgList list = this.getImgTextMsgList();
						List<ImgTextMsgList.ImgTextMsg> imgTextMsgs = list.getList();
						if (null != imgTextMsgs && !imgTextMsgs.isEmpty()) {
							ImgTextMsgList.ImgTextMsg imgTextMsg = imgTextMsgs.get(0);
							if (null != imgTextMsg) {
								form.setAppmsgid(imgTextMsg.getAppId());
								form.setFid(imgTextMsg.getAppId());
							}
						}
						if (StringUtils.isBlank(form.getAppmsgid()) || StringUtils.isBlank(form.getFid())) {
							this.msgSendMsg = "��������:appmsgidΪ��";
							return false;
						}
						parts = new Part[]{
							new StringPart("fid", form.getFid(),
							"UTF-8"),
							new StringPart("appmsgid", form.getAppmsgid(),
							"UTF-8"),
							new StringPart("type", MsgType.IMAGE_TEXT.getType() + ""),
							new StringPart("error", form.getError()),
							new StringPart("needcomment", form.getNeedcomment()),
							new StringPart("groupid", form.getGroupid()),
							new StringPart("sex", form.getSex()),
							new StringPart("country", form.getCountry()),
							new StringPart("province", form.getProvince()),
							new StringPart("city", form.getCity()),
							new StringPart("token", form.getToken()),
							new StringPart("ajax", form.getAjax()),
							new StringPart("t", "ajax-response")};
						break;
					default:
						parts = new Part[]{
							new StringPart("content", form.getContent(),
							"UTF-8"),
							new StringPart("type", form.getType()),
							new StringPart("error", form.getError()),
							new StringPart("needcomment", form.getNeedcomment()),
							new StringPart("groupid", form.getGroupid()),
							new StringPart("sex", form.getSex()),
							new StringPart("country", form.getCountry()),
							new StringPart("province", form.getProvince()),
							new StringPart("city", form.getCity()),
							new StringPart("token", form.getToken()),
							new StringPart("ajax", form.getAjax()),
							new StringPart("t", "ajax-response")};

						break;
				}
				RequestEntity entity = new MultipartRequestEntity(parts,
						post.getParams());
				post.setRequestEntity(entity);
				int status;
				status = client.executeMethod(post);
				if (status == HttpStatus.SC_OK) {
					String text = post.getResponseBodyAsString();
					try {
						MsgJson ret = JSON.parseObject(text, MsgJson.class);
						this.msgSendCode = ret.getRet();
						switch (this.msgSendCode) {
							case 0:
								this.msgSendMsg = "���ͳɹ�";
								return true;
							case -2:
								this.msgSendMsg = "������������ϸ���";
								return false;
							case 64004:
								this.msgSendMsg = "�����Ⱥ�������ѵ����޷�Ⱥ��";
								return false;
							case -20000:
								this.msgSendMsg = "���󱻽�ֹ������ϸ���token�Ƿ�Ϸ�";
								return false;
							default:
								this.msgSendMsg = "δ֪����!";
								return false;
						}
					} catch (Exception e) {
						String info = "��Ⱥ����Ϣʧ�ܡ�������json����" + e.getMessage()
								+ "\n\t���ı�:��\n\t" + text;
						System.err.println(info);
						log.debug(info);
						log.info(info);
						return false;
					}
				}
			}
		} catch (Exception e) {
			String info = "��Ⱥ����Ϣʧ�ܡ�" + e.getMessage();
			System.err.println(info);
			log.debug(info);
			log.info(info);
			return false;
		}
		return false;
	}
	private String updateImgErr;

	public String getUpdateImgErr() {
		return updateImgErr;
	}

	public void setUpdateImgErr(String updateImgErr) {
		this.updateImgErr = updateImgErr;
	}

	//    public final static Pattern IMG_SUCCESS_REG = Pattern.compile("\.top\.W\.upload\.suc(\"")
	public String updateImg(ImgFileForm form) {
		try {
			if (!this.isLogin) {
				this.isLogin();
			}
			if (this.isLogin) {
				form.setToken(this.getToken());
				PostMethod post = new PostMethod(UPLOAD_MATERIAL);
				post.setRequestHeader(USER_AGENT_H, USER_AGENT);
				post.setRequestHeader(REFERER_H, INDEX_URL);
				post.setRequestHeader("Connection", "Keep-Alive");
				post.setRequestHeader("Cookie", this.cookiestr);
				post.setRequestHeader("Cache-Control", "no-cache");

				/**
				 * private String cgi = "uploadmaterial"; private String type = "2"; private String token = ""; private
				 * String t = "iframe-uploadfile"; private String lang = "zh_CN"; private String formId = "1";
				 */
				String fileName = form.getUploadfile().getName();
				String ext = StringUtils.substring(fileName, fileName.indexOf("."), fileName.length());
				FilePart file = new FilePart("uploadfile", form.getUploadfile(), "image/" + ext, "UTF-8");
				System.out.println(form.getToken());
				Part[] parts = new Part[]{
					new StringPart("cgi", form.getCgi()),
					new StringPart("type", form.getType()),
					new StringPart("token", form.getToken()),
					new StringPart("t", form.getT()),
					new StringPart("lang", form.getLang()),
					new StringPart("formId", form.getFormId()),
					file};
				MultipartRequestEntity entity = new MultipartRequestEntity(parts, post.getParams());
				post.setRequestEntity(entity);
				int status = client.executeMethod(post);
				if (status == HttpStatus.SC_OK) {
					String text = post.getResponseBodyAsString();
					return parseUploadImgText(text);
				}
			}
			return null;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

	private String parseUploadImgText(String text) {
		try {
			if (StringUtils.isBlank(text)) {
				return null;
			}
			String sub = "";
			int type = 0;
			if (text.indexOf("top.W.upload.suc(") != -1) {
				sub = "top.W.upload.suc(";
				type = 1;
			} else if (text.indexOf("top.W.upload.err(") != -1) {
				sub = "top.W.upload.err(";
				type = 2;
			}
			StringBuilder ret = new StringBuilder();
			for (int i = text.indexOf(sub) + sub.length(), len = text.length(); i < len; i++) {
				char c = text.charAt(i);
				if (c == ')') {
					break;
				}
				ret.append(c);
			}
			String result = ret.toString().replaceAll("['|\"]", "");
			String[] s;
			switch (type) {
				case 1:
					s = StringUtils.split(result, ",");
					if (null != s && s.length == 4) {
						this.updateImgErr = StringUtils.trim(s[0]);
						return StringUtils.trim(s[3]);
					}
					this.updateImgErr = "δ֪����";
					return null;
				case 2:
					s = StringUtils.split(result, ",");
					if (null != s && s.length == 3) {
						this.updateImgErr = StringUtils.trim(s[0]);
						return null;
					}
					this.updateImgErr = "δ֪����";
					return null;
				default:
					this.updateImgErr = "δ֪����";
					return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
	private String imgTextSendErr = "";
	private int imgTextSendCode;

	public String getImgTextSendErr() {
		return imgTextSendErr;
	}

	public void setImgTextSendErr(String imgTextSendErr) {
		this.imgTextSendErr = imgTextSendErr;
	}

	public int getImgTextSendCode() {
		return imgTextSendCode;
	}

	public void setImgTextSendCode(int imgTextSendCode) {
		this.imgTextSendCode = imgTextSendCode;
	}

	public boolean saveImgText(ImgTextForm form) {
		try {
			if (!this.isLogin) {
				this.isLogin();
			}
			if (this.isLogin) {
				form.setToken(this.getToken());
				PostMethod post = new PostMethod(OPERATE_APPMSG);
				post.setRequestHeader(USER_AGENT_H, USER_AGENT);
				post.setRequestHeader(REFERER_H, INDEX_URL);

				post.setRequestHeader("Cookie", this.cookiestr);

				/**
				 * private String error = "false"; private String count; private String AppMsgId = ""; private String
				 * token; private String ajax = "1"; private String lang = "zh_CN"; private String t = "ajax-response";
				 * private String sub = "create"; private List<Piece> pieces;
				 */
				System.out.println(form.getToken());
				List<Part> params = new ArrayList<Part>();
				params.add(new StringPart("error", form.getError()));
				params.add(new StringPart("count", form.getCount()));
				params.add(new StringPart("AppMsgId", form.getAppMsgId()));
				params.add(new StringPart("token", form.getToken()));
				params.add(new StringPart("ajax", form.getAjax()));
				params.add(new StringPart("lang", form.getLang()));
				params.add(new StringPart("t", form.getT()));
				params.add(new StringPart("sub", form.getSub()));

				int i = 0;
				for (ImgTextForm.Piece piece : form.getPieces()) {
					if (null != piece.getImg()) {
						String fileid = this.updateImg(piece.getImg());
						if (StringUtils.isBlank(fileid)) {
							continue;
						}
						piece.setFileid(fileid);
						params.add(new StringPart("title" + i, piece.getTitle(), UTF_8));
						params.add(new StringPart("digest" + i, piece.getDigest(), UTF_8));
						params.add(new StringPart("content" + i, piece.getContent(), UTF_8));
						params.add(new StringPart("fileid" + i, piece.getFileid(), UTF_8));
						i++;
					}
				}
				Part[] parts = new Part[params.size()];
				MultipartRequestEntity entity = new MultipartRequestEntity(params.toArray(parts), post.getParams());
				post.setRequestEntity(entity);
				int status = client.executeMethod(post);
				if (status == HttpStatus.SC_OK) {
					String text = post.getResponseBodyAsString();
					try {
						MsgJson ret = JSON.parseObject(text, MsgJson.class);
						this.imgTextSendCode = ret.getRet();
						System.out.println(text);
						switch (this.msgSendCode) {
							case 0:
								this.imgTextSendErr = "���ͳɹ�";
								return true;
							case -2:
								this.imgTextSendErr = "������������ϸ���";
								return false;
							case 64004:
								this.imgTextSendErr = "�����Ⱥ�������ѵ����޷�Ⱥ��";
								return false;
							case -20000:
								this.imgTextSendErr = "���󱻽�ֹ������ϸ���token�Ƿ�Ϸ�";
								return false;
							default:
								this.imgTextSendErr = "δ֪����!";
								return false;
						}
					} catch (Exception e) {
						String info = "��Ⱥ����Ϣʧ�ܡ�������json����" + e.getMessage()
								+ "\n\t���ı�:��\n\t" + text;
						System.err.println(info);
						log.debug(info);
						log.info(info);
						return false;
					}

				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return false;
	}

	public ImgTextMsgList getImgTextMsgList() {
		try {
			if (!this.isLogin) {
				this.isLogin();
			}
			if (this.isLogin) {

				GetMethod post = new GetMethod(OPERATE_APPMSG);
				post.setRequestHeader(USER_AGENT_H, USER_AGENT);
				post.setRequestHeader(REFERER_H, INDEX_URL);
				post.setRequestHeader("Connection", "Keep-Alive");
				post.setRequestHeader("Cookie", this.cookiestr);
				post.setRequestHeader("Cache-Control", "no-cache");
				/**
				 * sub=list&type=10&subtype=3&t=wxm-appmsgs-list-new&pagesize=10&pageidx=0&token=1004476860&lang=zh_CN
				 */
				NameValuePair[] params = new NameValuePair[]{
					new NameValuePair("sub", "list"),
					new NameValuePair("type", "10"),
					new NameValuePair("subtype", "3"),
					new NameValuePair("t", "wxm-appmsgs-list-new"),
					new NameValuePair("pagesize", "10"),
					new NameValuePair("pageidx", "0"),
					new NameValuePair("token", this.getToken()),
					new NameValuePair("lang", "zh_CN")
				};
				post.setQueryString(params);

				int status = client.executeMethod(post);
				if (status == HttpStatus.SC_OK) {
					String text = post.getResponseBodyAsString();
					Document doc = Jsoup.parse(text);
					Elements eles = doc.select("script[id=json-msglist]");
					for (Element e : eles) {
						String html = e.html();
						ImgTextMsgList ret = JSON.parseObject(html, ImgTextMsgList.class);
						return ret;
					}

				}
			}
			return null;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * ҳ����ת
	 *
	 * @param url
	 */
	public void redirect(String url) {
		if (url.indexOf("https://") == -1) {
			url = HOST + url;
		}
		try {
			if (this.isLogin) {
				GetMethod get = new GetMethod(url);
				get.setRequestHeader(USER_AGENT_H, USER_AGENT);
				get.setRequestHeader(REFERER_H, INDEX_URL);
				get.setRequestHeader("Cookie", this.cookiestr);
				int status = client.executeMethod(get);
				if (status == HttpStatus.SC_OK) {
					System.err.println("������ת.....");
					System.out.println(get.getResponseBodyAsString());
				}
			}
		} catch (Exception e) {
		}
	}

	/**
	 * ʹ�÷���:<br>
	 * new Weixin()�����ȵ�¼��ȡ��˿������Ⱥ����<br>
	 * Ⱥ����Ҫ����һ��MsgForm������ Ĭ�Ϸ����ı���Ϣ�����͸��й���<br>
	 * ���Բ���Ҫ��������������������setContent����Ҫ���͵���������OK<br>
	 * �����еĳ����ӿ���ֱ�ӷ��Ͳ���ʹ��<a>��ǩ
	 *
	 * @param args
	 */
	public static void main(String[] args) {


		String LOGIN_USER = "caolei_t@yeah.net";
		String LOGIN_PWD = "#citysky#";
		Weixin wx = new Weixin(LOGIN_USER, LOGIN_PWD);
		wx.login();
//        wx.getMsgTextList();
		wx.getCookiestr();
		File file = new File("e:\\Data\\calendar.gif");
		ImgFileForm img = new ImgFileForm();
		img.setUploadfile(file);
		ImgTextForm form = new ImgTextForm();
		List<ImgTextForm.Piece> pieces = new ArrayList<ImgTextForm.Piece>();

		ImgTextForm.Piece piece = new ImgTextForm.Piece();
		piece.setContent("��һ����һ����һ����һ����һ����һ��");
		piece.setDigest("��һ����һ��");
		piece.setImg(img);
		piece.setTitle("��һ��");
		pieces.add(piece);

		ImgTextForm.Piece piece1 = new ImgTextForm.Piece();
		piece1.setContent("�ڶ����ڶ����ڶ����ڶ����ڶ����ڶ���");
		piece1.setDigest("�ڶ����ڶ���");
		piece1.setImg(img);
		piece1.setTitle("�ڶ���");
		pieces.add(piece1);

		ImgTextForm.Piece piece2 = new ImgTextForm.Piece();
		piece2.setContent("������������������������������������");
		piece2.setDigest("������������");
		piece2.setImg(img);
		piece2.setTitle("������");
		pieces.add(piece2);

		form.setPieces(pieces);
		wx.saveImgText(form);
		MsgForm msg = new MsgForm();
		wx.msgSend(msg, MsgType.IMAGE_TEXT);

		System.out.println(wx.getMsgSendMsg());
		System.out.println(wx.getFansCount());
		System.out.println(wx.getImgTextSendErr());


	}
}
