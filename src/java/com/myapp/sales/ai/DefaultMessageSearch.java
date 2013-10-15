/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.sales.ai;

import java.util.Map;

/**
 *
 * @author citysky
 */
public class DefaultMessageSearch implements IMessageSearch {

	@Override
	public String messageSearch(String message) {
		String[] keywords = getKeyWords(message); //此处需要对输入的关键字进行分词
		String result = getResultWithKeywords(keywords); //此处需要连接逻辑进行物理构建
		return result;
	}

	@Override
	public String messageSearch(Map<String, String> msgMap) {
		return "";
	}

	private String[] getKeyWords(String message) {
		return message.split(" ");
	}

	private String getResultWithKeywords(String[] keywords) {
		String result = "";
		if (keywords.length > 0) {
			String firstKey = keywords[0];
			if ("?".equals(firstKey.trim())) {
				result = getResult(getHelpCommands());
			} else if ("1".equals(firstKey.trim())) {
				result = getResult(getTeminalInfos());
			} else if ("2".equals(firstKey.trim())) {
				result = getResult(getActionInfos());
			} else if ("3".equals(firstKey.trim())) {
				result = getResult(getCallCredit());
			} else if ( "hi".equalsIgnoreCase(firstKey.trim())){
				result = getResult(getHelpCommands());
			}
		}
		return result;
	}

	private String getResult(String[] array) {
		StringBuilder sb = new StringBuilder();
		for (String string : array) {
			sb.append(string).append("\n");
		}
		return sb.toString();
	}

	private String[] getHelpCommands() {
		return new String[]{
			"您好，我是营销小秘，请回复数字选择服务：",
			"1、终端促销",
			"2、活动办理查询",
			"3、话费查询"
		};
	}

	private String[] getTeminalInfos() {
		return new String[]{
			"终端促销品信息如下：",
			"1、促销品1",
			"2、促销品2",
			"3、促销品3"
		};
	}

	private String[] getActionInfos() {
		return new String[]{
			"活动办理信息如下：",
			"1、活动信息1",
			"2、活动信息2",
			"3、活动信息3"
		};
	}

	private String[] getCallCredit() {
		return new String[]{
			"话费信息如下：",
			"本月拨打国内电话市场78分钟，港澳台电话时长32分钟，国际电话时长20分钟。",
			"总计电话费用103.34元."
		};
	}
}
