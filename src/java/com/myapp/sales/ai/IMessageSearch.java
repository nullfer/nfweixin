/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.sales.ai;

import java.util.Map;

/**
 * ��Ϣ��ѯ�ӿ�
 *
 * @author citysky
 */
public interface IMessageSearch {

	public String messageSearch(String message);

	public String messageSearch(Map<String, String> msgMap);
}
