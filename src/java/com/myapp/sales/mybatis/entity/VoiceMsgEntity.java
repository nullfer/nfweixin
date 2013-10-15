/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.sales.mybatis.entity;

/**
 *
 * @author citysky
 */
public class VoiceMsgEntity extends MessageEntity {
	// √ΩÃÂID

	private String mediaId;
// ”Ô“Ù∏Ò Ω
	private String format;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
}
