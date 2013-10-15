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
					<th>发送方</th>
					<th>接收方</th>
					<th>发送时间</th>
					<th>类型</th>
					<th>内容</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="record" items="${requestScope.recordList}">
					<tr>
						<td>${record.toUserName}</td>
						<td>${record.toUserName}</td>
						<td><fmt:formatDate value="${record.createDate}" pattern="yyyy-MM-dd hh:mm:ss"></fmt:formatDate></td>
						<td>${record.msgType}</td>
						<td>${record.content}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

    </body>
</html>
