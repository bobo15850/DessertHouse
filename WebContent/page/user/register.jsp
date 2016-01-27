<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
</head>
<body>
	<h1>注册</h1>
	<s:form action="/user/register.action">
		<s:textfield name="user.username">用户名</s:textfield>
		<s:textfield name="phonenumber">手机号</s:textfield>
		<s:password name="user.password">密码</s:password>
		<s:password name="confirm">确认密码</s:password>
		<s:submit>注冊</s:submit>
	</s:form>
</body>
</html>