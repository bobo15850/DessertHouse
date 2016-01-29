<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>header</title>
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>
	<nav class="navbar navbar-inverse navbar-static-top" role="navigation">
	<div class="navbar-header">
		<a class="navbar-brand" href="#">DessertHouse</a>
	</div>
	<div>
		<ul class="nav navbar-nav">
			<s:if test="false">
				<li class="active"><a href="#">recharge</a></li>
				<li><a href="#">consumption</a></li>
				<li><a href="#">book</a></li>
				<li><a href="#">account</a></li>
			</s:if>
			<s:elseif test="true">
				<li class="active"><a href="#">sale</a></li>
				<li><a href="#">account</a></li>
			</s:elseif>
		</ul>
	</div>
	</nav>
</body>
</html>