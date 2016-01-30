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
<link rel="stylesheet" href="<%=basePath%>/css/user/login.css">
<title>登陆</title>
</head>
<body>
	<s:include value="../common/header.jsp"></s:include>
	<div class="container">
		<form class="form-login" action="<%=basePath%>/user/login.action" method="post">
			<h2 class="form-login-heading">请登录</h2>
			<input type="text" name="key" class="form-control" placeholder="用户名或手机号" required autofocus>
			<input type="password" name="password" class="form-control" placeholder="密码8-16位数字或字母或下划线" required>
			<h5 class="login-error">${FieldErrors.loginMessage[0]}</h5>
			<div class="checkbox">
				<label><input type="checkbox" value="remember-me">记住密码</label>
				<a class="forget-password" href="#">忘记密码</a>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
		</form>
		<div class="no-account">
			<label>没有账号<a href="register.jsp">==>立即注册</a></label>
		</div>
	</div>
</body>
</html>