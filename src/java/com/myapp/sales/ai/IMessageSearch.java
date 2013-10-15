/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.sales.ai;

import java.util.Map;

/**
 * 消息查询接口
 *
 * @author citysky
 */
public interface IMessageSearch {

	public String messageSearch(String message);

	public String messageSearch(Map<String, String> msgMap);
}
