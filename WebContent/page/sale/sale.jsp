<%@page import="edu.nju.desserthouse.model.User"%>
<%@page import="edu.nju.desserthouse.model.Shop"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String basePath = request.getContextPath();
	User staff = (User) request.getAttribute("staff");//员工
	Shop shop = staff.getShop();//员工所属店铺
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>销售</title>
<link rel="stylesheet" href="<%=basePath%>/lib/bootstrap/css/bootstrap.min.css">
<script src="<%=basePath%>/lib/jquery/jquery-1.12.1.min.js"></script>
<script src="<%=basePath%>/lib/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<s:include value="../common/header.jsp"></s:include>
	<div class="container">
		<div class="row">
			<div class="col-sm-2">
				<div class="control-panel">
					<div class="base-info">
						<h4>
							店名：<%=shop.getShopname()%></h4>
						<h4>
							服务员：<%=staff.getUsername()%></h4>
					</div>
					<br> <br> <br>
					<div>
						<form action="">
							<button class="btn btn-primary btn-block">选购商品</button>
						</form>
					</div>
					<div>
						<form action="">
							<button class="btn btn-primary btn-block">结算中心</button>
						</form>
					</div>
				</div>
			</div>
			<div class="col-sm-10"></div>
		</div>
	</div>
	<s:include value="../common/footer.jsp"></s:include>
</body>
</html>