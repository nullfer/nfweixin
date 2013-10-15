/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.sales.business.weixin.resp;

/**
 *
 * @author citysky
 */
public class Music {
	// 音乐名称

	private String Title;
// 音乐描述
	private String Description;
// 音乐链接
	private String MusicUrl;
// 高质量音乐链接，WIFI环境优先使用该链接播放音乐
	private String HQMusicUrl;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return Title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.Title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return Description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.Description = description;
	}

	/**
	 * @return the musicUrl
	 */
	public String getMusicUrl() {
		return MusicUrl;
	}

	/**
	 * @param musicUrl the musicUrl to set
	 */
	public void setMusicUrl(String musicUrl) {
		this.MusicUrl = musicUrl;
	}

	/**
	 * @return the hqMusicUrl
	 */
	public String getHqMusicUrl() {
		return HQMusicUrl;
	}

	/**
	 * @param hqMusicUrl the hqMusicUrl to set
	 */
	public void setHqMusicUrl(String hqMusicUrl) {
		this.HQMusicUrl = hqMusicUrl;
	}
}
