<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=basePath%>/css/user/register.css">
<title>注册</title>
</head>
<body>
	<s:include value="../common/header.jsp"></s:include>
	<div class="container">
		<form class="form-register" action="<%=basePath%>/user/register.action" method="post" onSubmit="return check_form();">
			<h2 class="form-register-heading">请注册</h2>
			<input type="text" name="user.username" class="form-control" placeholder="请输入昵称，不超过10个字符" onBlur="check_username(this)" autofocus required>
			<input type="password" name="user.password" class="form-control" placeholder="请输入密码8-16位" onBlur="check_password(this)" required>
			<input type="password" name="password_repeat" class="form-control" placeholder="确认密码" required>
			<h5 class="register-error">${FieldErrors.registerMessage[0]}</h5>
			<button class="btn btn-lg btn-primary btn-block" type="submit">注册</button>
		</form>
		<div class="has-account">
			<label>已有账号<a href="<%=basePath%>/page/user/login.jsp">==>直接登陆</a></label>
		</div>
	</div>
	<script type="text/javascript" src="<%=basePath%>/js/user/register.js"></script>
</body>
</html>