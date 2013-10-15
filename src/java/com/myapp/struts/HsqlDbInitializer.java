/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import com.alibaba.fastjson.JSON;
import com.myapp.sales.mybatis.MybatisUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author citysky
 */
public class HsqlDbInitializer {

	private static boolean checkDBInited() throws SQLException {
		SqlSession session = MybatisUtil.getSqlSessionFactory().openSession();
		String sql = "select 1 from INFORMATION_SCHEMA.tables t where t.table_schema='PUBLIC' and table_name='MESSAGE'";
		Connection conn = session.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		boolean isInited = false;
		if (rs.next()) {
			isInited = true;
		}
		rs.close();
		st.close();
		session.close();
		return isInited;
	}

	public static String initDB() throws SQLException {
		if (checkDBInited()) {
			return "数据库已经初始化完毕,不需要再进行初始化！";
		}
//		SqlSession session = MybatisUtil.getSqlSessionFactory().openSession();
		String sqls = "CREATE TABLE eventmessage ( msgId bigint NOT NULL, event varchar(20)  NOT NULL, eventKey varchar(20) ) ;"
				+ "CREATE TABLE imagemessage ( msgId bigint NOT NULL, picUrl varchar(500)  NOT NULL );"
				+ "CREATE TABLE linkmessage ( msgId bigint NOT NULL, title varchar(200)  NOT NULL, description varchar(500)  NOT NULL, url varchar(300)  NOT NULL) ;"
				+ "CREATE TABLE locationmessage ( msgId bigint NOT NULL, Location_X float NOT NULL, Location_Y float NOT NULL, Scale int NOT NULL, Label varchar(1024)  NOT NULL) ;"
				+ "CREATE TABLE message ( toUserName varchar(50)  NOT NULL, fromUserName varchar(50)  NOT NULL, createTime bigint NOT NULL, msgType varchar(10)  NOT NULL, msgId bigint NOT NULL, postSign bit, funcFlag int, PRIMARY KEY (msgId) );"
				+ "CREATE TABLE textmessage ( msgId bigint NOT NULL, content varchar(2048) );"
				+ "CREATE TABLE voicemessage ( msgId bigint NOT NULL, mediaId varchar(200)  NOT NULL, format varchar(50)  NOT NULL );";
		String ss ="insert into message(tousername,fromuserName,createtime,msgtype,msgid,postsign,funcflag) values('a','b',12984823,'text',11110001,false,0);"
				+ "insert into message(tousername,fromuserName,createtime,msgtype,msgid,postsign,funcflag) values('c','d',12983423,'text',11110002,false,0);";
//		Connection conn = session.getConnection();
//		Statement st = conn.createStatement();
		for (String sql : ss.split(";")) {
			if (sql.trim().length() > 0) {
				System.out.println(execute(sql)+":"+sql);
			}
		}
//		int result[] = st.executeBatch();
//		session.commit();
//		System.out.println(JSON.toJSONString(result));
//		st.close();
//		session.close();
		return "数据库初始化完毕";
	}

	public static boolean execute(String sql) throws SQLException {
		SqlSession session = MybatisUtil.getSqlSessionFactory().openSession();
		Connection conn = session.getConnection();
		Statement st = conn.createStatement();
		boolean ok = st.execute(sql);
		session.close();
		return ok;
	}

	public static void main(String[] args) throws SQLException {
		System.out.println(initDB());
	}
}
