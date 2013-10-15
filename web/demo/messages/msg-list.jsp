<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.myapp.sales.mybatis.entity.TxtMsgEntity"%>
<%@page import="java.util.List"%>
<%@page import="com.myapp.sales.mybatis.entity.MessageEntity"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : record-list
    Created on : 2013-10-12, 7:24:45
    Author     : citysky
--%>

<%@page contentType="text/html" pageEncoding="GBK"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=GBK">
        <title>JSP Page</title>
    </head>
    <body>
		<table border="1" cellspacing="0" cellpadding="1" style="width: 100%">
			<thead>
				<tr>
					<th>消息类型</th>
					<th>描述</th>
					<th>回复可用</th>
					<th>编码</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="record" items="${requestScope.recordList}">
					<tr>
						<td>${record.name}</td>
						<td>${record.description}</td>
						<td>${record.replySupport}</td>
						<td>${record.code}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

    </body>
</html>
