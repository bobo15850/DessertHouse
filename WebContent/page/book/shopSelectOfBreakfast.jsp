<%@page import="edu.nju.desserthouse.util.FinalValue"%>
<%@page import="edu.nju.desserthouse.model.User"%>
<%@page import="java.util.List"%>
<%@page import="edu.nju.desserthouse.model.Shop"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String basePath = request.getContextPath();
	List<Shop> shops = (List<Shop>) request.getAttribute("shops");
	User user = (User) request.getAttribute("user");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=basePath%>/lib/bootstrap/css/bootstrap.min.css">
<script src="<%=basePath%>/lib/jquery/jquery-1.12.1.min.js"></script>
<script src="<%=basePath%>/lib/bootstrap/js/bootstrap.min.js"></script>
<title>选择商店</title>
</head>
<body>
	<s:include value="../common/header.jsp"></s:include>
	<div class="container">
		<div clas="row">
			<div class="col-sm-1"></div>
			<div class="col-sm-10">
				<h3>请选择店铺</h3>
				<%
					if (shops == null || shops.size() == 0) {
						out.print("<h2>没有店铺可供预订</h2>");
					}
					else {
				%>
				<br>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>#</th>
							<th>店铺名称</th>
							<th>店铺地址</th>
							<th>联系电话</th>
							<th>选择</th>
						</tr>
					</thead>
					<tbody>
						<%
							for (int i = 0; i < shops.size(); i++) {
									Shop shop = shops.get(i);
						%>
						<tr>
							<td><%=i%></td>
							<td><%=shop.getShopname()%></td>
							<td><%=shop.getLocation()%></td>
							<td><%=shop.getPhonenumber()%></td>
							<td>
								<form action="<%=basePath%>/book/shopBreakfastBook.action">
									<input type="hidden" name="shopId" value=<%=shop.getId()%>>
									<button type="submit" class="btn btn-primary" <%=user.getState() == FinalValue.UserState.NORMAL ? "" : " disabled='disabled'"%>>预定</button>
								</form>
							</td>
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