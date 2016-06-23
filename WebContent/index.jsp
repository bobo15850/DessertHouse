<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
<link rel="stylesheet" href="<%=basePath%>/lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=basePath%>/css/index.css">
<script src="<%=basePath%>/lib/jquery/jquery-1.12.1.min.js"></script>
<script src="<%=basePath%>/lib/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<s:include value="page/common/header.jsp"></s:include>
	<h1 class="text-center"><img class="crown" src="<%= basePath%>/image/crown.jpg">甜品屋管理系统<img class="crown" src="<%= basePath%>/image/crown.jpg"></h1>
	<div class="center product">
		<h2><img class="crown" src="<%= basePath%>/image/crown.jpg">商品展示</h2>
		<div class="product_item">
			<img src="<%=basePath %>/image/product/草莓蛋糕.jpg" />
			<div class="product_item_description">
				<div class="description_left">
					<span>草莓蛋糕</span>
				</div>
				<div class="description_right">
					<span class="glyphicon glyphicon-yen"></span>
					<span class="price">12.0</span>
				</div>
			</div>
		</div>
		<div class="product_item">
			<img src="<%=basePath %>/image/product/抹茶蛋糕.jpg" />
			<div class="product_item_description">
				<div class="description_left">
					<span>抹茶蛋糕</span>
				</div>
				<div class="description_right">
					<span class="glyphicon glyphicon-yen"></span>
					<span class="price">12.0</span>
				</div>
			</div>
		</div>
		<div class="product_item">
			<img src="<%=basePath %>/image/product/泡芙蛋糕.jpg" />
			<div class="product_item_description">
				<div class="description_left">
					<span>泡芙蛋糕</span>
				</div>
				<div class="description_right">
					<span class="glyphicon glyphicon-yen"></span>
					<span class="price">12.0</span>
				</div>
			</div>
		</div>
		<div class="product_item">
			<img src="<%=basePath %>/image/product/巧克力生日蛋糕.jpg" />
			<div class="product_item_description">
				<div class="description_left">
					<span>巧克力生日蛋糕</span>
				</div>
				<div class="description_right">
					<span class="glyphicon glyphicon-yen"></span>
					<span class="price">12.0</span>
				</div>
			</div>
		</div>
		<div class="product_item">
			<img src="<%=basePath %>/image/product/水果蛋糕.jpg" />
			<div class="product_item_description">
				<div class="description_left">
					<span>水果蛋糕</span>
				</div>
				<div class="description_right">
					<span class="glyphicon glyphicon-yen"></span>
					<span class="price">12.0</span>
				</div>
			</div>
		</div>
		<div class="product_item">
			<img src="<%=basePath %>/image/product/五彩蛋糕.jpg" />
			<div class="product_item_description">
				<div class="description_left">
					<span>五彩蛋糕</span>
				</div>
				<div class="description_right">
					<span class="glyphicon glyphicon-yen"></span>
					<span class="price">12.0</span>
				</div>
			</div>
		</div>
		<div class="product_item">
			<img src="<%=basePath %>/image/product/小狗蛋糕.jpg" />
			<div class="product_item_description">
				<div class="description_left">
					<span>小狗蛋糕</span>
				</div>
				<div class="description_right">
					<span class="glyphicon glyphicon-yen"></span>
					<span class="price">12.0</span>
				</div>
			</div>
		</div>
		<div class="product_item">
			<img src="<%=basePath %>/image/product/小黄鸭蛋糕.jpg" />
			<div class="product_item_description">
				<div class="description_left">
					<span>小黄鸭蛋糕</span>
				</div>
				<div class="description_right">
					<span class="glyphicon glyphicon-yen"></span>
					<span class="price">12.0</span>
				</div>
			</div>
		</div>
	</div>
	
	<div class="center service">
		<h2><img class="crown" src="<%= basePath%>/image/crown.jpg">特色功能</h2>
		<div class="service_item farleft">
			<img src="<%=basePath %>/image/cake.jpg" />
			<div class="service_item_description">
				<span>蛋糕订做</span>
			</div>
		</div>
		<div class="service_item left">
			<img src="<%=basePath %>/image/takeout.jpg" />
			<div class="service_item_description">
				<span>甜品外送</span>
			</div>
		</div>
		<div class="service_item left">
			<img src="<%=basePath %>/image/breakfast.jpg" />
			<div class="service_item_description">
				<span>每日早餐</span>
			</div>
		</div>
	</div>
	<s:include value="page/common/footer.jsp"></s:include>
</body>
</html>