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
	// ��������

	private String Title;
// ��������
	private String Description;
// ��������
	private String MusicUrl;
// �������������ӣ�WIFI��������ʹ�ø����Ӳ�������
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
