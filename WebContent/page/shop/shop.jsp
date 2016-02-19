<%@page import="edu.nju.desserthouse.model.Shop"%>
<%@page import="edu.nju.desserthouse.model.Region"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String basePath = request.getContextPath();
	List<Region> provinces = (List<Region>) request.getAttribute("provinces");
	List<Shop> shops = (List<Shop>) request.getAttribute("shops");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>店铺</title>
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=basePath%>/css/main.css">
<link rel="stylesheet" href="<%=basePath%>/css/shop/shop.css">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/shop/shop.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/region/region.js"></script>
</head>
<body>
	<s:include value="../common/header.jsp"></s:include>
	<div id="main-body" class="container">
		<div class="row">
			<div class="col-sm-2">
				<div id="add-shop">
					<button class="btn btn-primary btn-block" data-toggle="modal" data-target="#add-shop-modal">添加分店</button>
					<div id="add-shop-modal" class="modal fade" tab-index="-1" role="dialog" aria-labelledby="myModalLabel">
						<div class="modal-dialog" role="document">
							<form action="<%=basePath%>/shop/addShop.action" method="post" onsubmit="checkAddShop()">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h4 class="modal-title" id="myModalLabel">添加分店</h4>
									</div>
									<div class="modal-body">
										<div class="input-group">
											<span class="input-group-addon">分店名称</span>
											<input id="shop-name-input" type="text" class="form-control" name="shop.shopname">
										</div>
										<div class="input-group">
											<span class="input-group-addon">电话号码</span>
											<input id="phonenumber-input" type="text" class="form-control" name="shop.phonenumber">
										</div>
										<div class="input-group">
											<span class="input-group-addon">所在省/直辖市</span>
											<select id="provinceSelect" class="form-control" onchange="changeProvince()">
												<option value="notset" selected="selected"></option>
												<%
													if (provinces != null && provinces.size() != 0) {
														for (Region province : provinces) {
												%>
												<option value="<%=province.getId()%>"><%=province.getName()%></option>
												<%
													}
													}
												%>
											</select>
											<span class="input-group-addon">地级市</span>
											<select id="citySelect" class="form-control" onchange="changeCity()">
											</select>
											<span class="input-group-addon">县/区</span>
											<select id="countySelect" class="form-control" name="regionId">
											</select>
										</div>
										<div class="input-group">
											<span class="input-group-addon">详细地址</span>
											<input id="location-input" type="text" class="form-control" name="shop.location">
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
										<button type="submit" class="btn btn-primary">确定</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-10">
				<%
					if (shops == null || shops.size() == 0) {
				%>
				<h1>没有找到店铺</h1>
				<%
					}
					else {
						for (int i = 0; i < shops.size(); i++) {
							Shop shop = shops.get(i);
				%>
				<div class="shop-item" onmouseover="showModifyBtn(<%=i%>)" onmouseout="hideModifyBtn(<%=i%>)">
					<label>
						<%=i + 1%>&nbsp;
					</label>
					<label class="shop-name">
						店名：<span><%=shop.getShopname()%></span>
					</label class="shop-phonenumber">
					<label>
						电话：<span><%=shop.getPhonenumber()%></span>
					</label>
					<label class="shop-location">
						地址：<span><%=shop.getLocation()%></span>
					</label>
					<form action="<%=basePath%>/shop/toModifyShop.action" method="get" class="display-inline">
						<input name="shopId" value="<%=shop.getId()%>" class="display-none">
						<button id="modify-btn-<%=i%>" type="submit" class="btn btn-primary modify-shop-btn hide">修改信息</button>
					</form>
				</div>
				<%
					}
					}
				%>
			</div>
		</div>
	</div>
	<s:include value="../common/footer.jsp"></s:include>
</body>
</html>