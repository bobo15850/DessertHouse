<%@page import="edu.nju.desserthouse.util.FinalValue"%>
<%@page import="edu.nju.desserthouse.util.UserBase"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	UserBase userbase = null;//session中的用户信息
	String basePath = null;//项目上下文地址
%>
<nav class="navbar navbar-inverse navbar-static-top" role="navigation">
	<div class="navbar-header">
		<a class="navbar-brand" href="#">DessertHouse</a>
	</div>
	<div>
		<ul class="nav navbar-nav">
			<%
				Object obj = session.getAttribute("userBase");//从session中取得用户登录后的信息
				basePath = application.getContextPath();
				if (obj == null) {
			%>
			<li class="active"><a href="#">欢饮来到DessertHouse</a></li>
			<%
				}
				else {
					userbase = (UserBase) obj;
					switch (userbase.getCategory()) {
					case FinalValue.UserCategory.COMMON_MENBER:
			%>
			<li class="active"><a href="<%=basePath%>/user/myAccount.action">我的账户</a></li>
			<li><a href="<%=basePath%>/user/consumptionRecord.action">消费记录</a></li>
			<li><a href="<%=basePath%>/user/bookGoods.action">预定商品</a></li>
			<li><a href="<%=basePath%>/user/rechargeRecord.action">充值信息</a></li>
			<%
				break;
					case FinalValue.UserCategory.BRANCH_WAITER:
			%>
			<li class="active"><a href="#">销售</a></li>
			<li><a href="#">查看会员信息</a></li>
			<li><a href="#">我的账户</a></li>
			<%
				break;
					case FinalValue.UserCategory.HEAD_WAITER:
			%>
			<li class="active"><a href="#">产品计划</a></li>
			<li><a href="#">产品管理</a></li>
			<%
				break;
					case FinalValue.UserCategory.MANAGER:
			%>
			<li class="active"><a href="#">产品计划审批</a></li>
			<li><a href="#">逐月统计</a></li>
			<%
				break;
					case FinalValue.UserCategory.ADMINISTRATOR:
			%>
			<li class="active"><a href="#">店面管理</a></li>
			<li><a href="#">店员管理</a></li>
			<%
				break;
					}
				}
			%>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<%
				if (userbase == null) {
			%>
			<li><a href="<%=basePath%>/page/user/login.jsp">登录/注册</a></li>
			<%
				}
				else {
			%>
			<li><a id="username-show"><%=userbase.getUsername()%></a></li>
			<li><a href="<%=basePath%>/user/logout.action">退出</a></li>
			<%
				}
			%>
		</ul>
	</div>
</nav>
