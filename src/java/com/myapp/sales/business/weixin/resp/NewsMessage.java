/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.sales.business.weixin.resp;

import java.util.List;

/**
 *
 * @author citysky
 */
public class NewsMessage extends RespMessage {
	// 图文消息个数，限制为10条以内

	private int ArticleCount;
// 多条图文消息信息，默认第一个item为大图
	private List<Article> Articles;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		this.ArticleCount = articleCount;
	}

	public List<Article> getArticles() {
		return Articles;
	}

	public void setArticles(List<Article> articles) {
		this.Articles = articles;
	}
}
