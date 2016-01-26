<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test page</title>
</head>
<body>
	<%!List<String> list = Arrays.asList("abc", "asd", "fgh", "jkl");%>

	<h1>测试页面</h1>
	<s:if test="%{false}">
		<h2>1+1</h2>
	</s:if>
	<s:elseif test="%{true}">
		<h2>1+1==2</h2>
	</s:elseif>
	<s:else>
		<h2>else</h2>
	</s:else>

	<s:generator separator="" val="%{'asd,dfg,hjhj,erwe'}">
		<s:iterator>
			<h3>
				<s:property />
			</h3>
		</s:iterator>
	</s:generator>

	<s:include value="user/myPage.jsp"></s:include>

	<s:date name="" />

</body>
</html>