<%@page import="edu.nju.desserthouse.model.RechargeRecord"%>
<%@page import="java.util.List"%>
<%@page import="edu.nju.desserthouse.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String basePath = request.getContextPath();
	Object inquireStr = request.getAttribute("inquireStr");//查询条件
	Object obj = request.getAttribute("user");//用户
	User user = null;
	List<RechargeRecord> rechargeRecordList = null;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=basePath%>/lib/bootstrap/css/bootstrap.min.css">
<title>查看会员充值信息</title>
</head>
<body>
	<s:include value="../common/header.jsp"></s:include>
	<div class="container">
		<div class="row">
			<div class="col-sm-1"></div>
			<div class="col-sm-10">
				<div>
					<form action="<%=basePath%>/viewUserRecharge.action" method="post">
						<div class="input-group">
							<span class="input-group-addon">查找充值记录</span>
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
							rechargeRecordList = user.getRechargeRecordList();
				%>
				<div class="row">
					<br> <br>
					<div class="col-sm-1"></div>
					<div class="col-sm-10">
						<div class="input-group">
							<span class="input-group-addon">用户姓名</span>
							<input type="text" class="form-control" value="<%=user.getUsername()%>" readonly="readonly">
							<span class="input-group-addon">会员卡号</span>
							<input type="text" class="form-control" value="<%=user.getCardId()%>" readonly="readonly">
							<span class="input-group-addon">手机号码</span>
							<input type="text" class="form-control" value="<%=user.getPhonenumber()%>" readonly="readonly">
						</div>
						<%
							if (rechargeRecordList == null || rechargeRecordList.size() == 0) {
						%>
						<h2>该用户还没还没有充值过</h2>
						<%
							}
									else {
						%>
						<table class="table table-striped">
							<thead>
								<tr>
									<th>#</th>
									<th>金额</th>
									<th>充值者</th>
									<th>充值时间</th>
								</tr>
							</thead>
							<tbody>
								<%
									for (int i = 0; i < rechargeRecordList.size(); i++) {
													RechargeRecord rechargeRecord = rechargeRecordList.get(i);
								%>
								<tr>
									<td><%=i%></td>
									<td><%=rechargeRecord.getMoney()%></td>
									<td><%=rechargeRecord.getUser().getUsername()%></td>
									<td><%=rechargeRecord.getCreatedTime()%></td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
						<%
							}
						%>

					</div>
					<div class="col-sm-1"></div>
				</div>
				<%
					}
					}
				%>
			</div>
			<div class="col-sm-1"></div>
		</div>
	</div>
	<s:include value="../common/footer.jsp"></s:include>
</body>
</html>