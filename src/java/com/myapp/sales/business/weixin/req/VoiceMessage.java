/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.sales.business.weixin.req;

/**
 * ��Ƶ��Ϣ
 *
 * @author citysky
 */
public class VoiceMessage extends ReqMessage {

// ý��ID
	private String MediaId;
// ������ʽ
	private String Format;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		this.MediaId = mediaId;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		this.Format = format;
	}
}
