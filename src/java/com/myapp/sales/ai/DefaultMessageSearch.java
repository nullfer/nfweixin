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
		String[] keywords = getKeyWords(message); //�˴���Ҫ������Ĺؼ��ֽ��зִ�
		String result = getResultWithKeywords(keywords); //�˴���Ҫ�����߼�����������
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
			"���ã�����Ӫ��С�أ���ظ�����ѡ�����",
			"1���ն˴���",
			"2��������ѯ",
			"3�����Ѳ�ѯ"
		};
	}

	private String[] getTeminalInfos() {
		return new String[]{
			"�ն˴���Ʒ��Ϣ���£�",
			"1������Ʒ1",
			"2������Ʒ2",
			"3������Ʒ3"
		};
	}

	private String[] getActionInfos() {
		return new String[]{
			"�������Ϣ���£�",
			"1�����Ϣ1",
			"2�����Ϣ2",
			"3�����Ϣ3"
		};
	}

	private String[] getCallCredit() {
		return new String[]{
			"������Ϣ���£�",
			"���²�����ڵ绰�г�78���ӣ��۰�̨�绰ʱ��32���ӣ����ʵ绰ʱ��20���ӡ�",
			"�ܼƵ绰����103.34Ԫ."
		};
	}
}
