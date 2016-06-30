<%@page import="edu.nju.desserthouse.model.RechargeRecord"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String basePath = request.getContextPath();
	List<RechargeRecord> rechargeRecordList = (List<RechargeRecord>) request.getAttribute("rechargeRecordList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=basePath%>/lib/bootstrap/css/bootstrap.min.css">
<script src="<%=basePath%>/lib/jquery/jquery-1.12.1.min.js"></script>
<script src="<%=basePath%>/lib/bootstrap/js/bootstrap.min.js"></script>
<title>充值记录展示</title>
</head>
<body>
	<s:include value="../common/header.jsp"></s:include>
	<div class="container">
		<div class="row">
			<div class="col-sm-1"></div>
			<div class="col-sm-10">
				<%
					if (rechargeRecordList == null || rechargeRecordList.size() == 0) {
				%>
				<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img class="crown" src="<%= basePath%>/image/coff-key.png">&nbsp;&nbsp;&nbsp;您还没有任何的充值记录哟~&nbsp;&nbsp;&nbsp;<img class="crown" src="<%= basePath%>/image/coff-key.png"></h1>
				<%
					}
					else {
				%>
				<h3>充值记录</h3>
				<br>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>#</th>
							<th>充值金额</th>
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
	</div>
	<s:include value="../common/footer.jsp"></s:include>
</body>
</html>