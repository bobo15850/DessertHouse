<%@page import="edu.nju.desserthouse.model.BookRecord"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String basePath = request.getContextPath();
	BookRecord bookRecord = (BookRecord) request.getAttribute("bookRecord");//订单
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=basePath%>/lib/bootstrap/css/bootstrap.min.css">
<script src="<%=basePath%>/lib/jquery/jquery-1.12.1.min.js"></script>
<script src="<%=basePath%>/lib/bootstrap/js/bootstrap.min.js"></script>
<title>在线支付</title>
</head>
<body>
	<s:include value="../common/header.jsp"></s:include>
	<h1>预定订单支付</h1>
	<s:include value="../common/footer.jsp"></s:include>
</body>
</html>