<%@page import="edu.nju.desserthouse.util.FinalValue"%>
<%@page import="edu.nju.desserthouse.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String basePath = request.getContextPath();
	Object inquireStr = request.getAttribute("inquireStr");//查询条件
	Object obj = request.getAttribute("user");//用户
	User user = null;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=basePath%>/lib/bootstrap/css/bootstrap.min.css">
<script src="<%=basePath%>/lib/jquery/jquery-1.12.1.min.js"></script>
<script src="<%=basePath%>/lib/bootstrap/js/bootstrap.min.js"></script>
<title>查看会员信息</title>
</head>
<body>
	<s:include value="../common/header.jsp"></s:include>
	<div class="container">
		<div class="row">
			<div class="col-sm-1"></div>
			<div class="col-sm-10">
				<div>
					<form action="<%=basePath%>/viewUserInfo.action" method="post">
						<div class="input-group">
							<span class="input-group-addon">查找用户</span>
							<input name="inquire-input" type="text" class="form-control" value="<%=inquireStr != null ? inquireStr : ""%>"
								placeholder="请输入用户名或注册手机号或会员卡号，请注意大小写">
							<span class="input-group-btn">
								<button class="btn btn-primary" type="submit">查找</button>
							</span>
						</div>
					</form>
				</div>
				<%
					if (inquireStr != null) {
						if (obj == null) {
							out.print("<h1>没有查询到有效用户</h1>");
						}
						else {
							user = (User) obj;
				%>
				<div class="row">
					<br> <br>
					<div class="col-sm-1"></div>
					<div class="col-sm-10">

						<div class="input-group">
							<span class="input-group-addon">用户姓名</span>
							<input type="text" class="form-control" value="<%=user.getUsername()%>">
						</div>
						<div class="input-group">
							<span class="input-group-addon">会员卡号</span>
							<input type="text" class="form-control" value="<%=user.getCardId()%>">
						</div>
						<div class="input-group">
							<span class="input-group-addon">手机号码</span>
							<input type="text" class="form-control" value="<%=user.getPhonenumber()%>">
						</div>
						<div class="input-group">
							<span class="input-group-addon">用户性别</span>
							<input type="text" class="form-control" value="<%=FinalValue.Gender.getStrOfGender(user.getGender())%>">
						</div>
						<div class="input-group">
							<span class="input-group-addon">用户等级</span>
							<input type="text" class="form-control" value="<%=FinalValue.UserLevel.getStrOfUserLevel(user.getLevel())%>">
						</div>
						<div class="input-group">
							<span class="input-group-addon">消费总额</span>
							<input type="text" class="form-control" value="<%=user.getConsumption()%>">
						</div>
						<div class="input-group">
							<span class="input-group-addon">账户余额</span>
							<input type="text" class="form-control" value="<%=user.getBalance()%>">
						</div>

					</div>
					<div class="col-sm-1"></div>
				</div>
				<%
					}
					}
					else {
						out.print("<h1>请输入查询条件</h1>");
					}
				%>
			</div>
			<div class="col-sm-1"></div>
		</div>
	</div>
	<s:include value="../common/footer.jsp"></s:include>
</body>
</html>