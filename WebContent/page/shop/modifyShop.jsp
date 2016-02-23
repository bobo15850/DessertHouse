<%@page import="java.io.IOException"%>
<%@page import="edu.nju.desserthouse.model.Region"%>
<%@page import="java.util.List"%>
<%@page import="edu.nju.desserthouse.model.Shop"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String basePath = request.getContextPath();
	Shop shop = (Shop) request.getAttribute("shop");
	//以下为所有的地区
	List<Region> provinces = (List<Region>) request.getAttribute("provinces");
	List<Region> cities = (List<Region>) request.getAttribute("cities");
	List<Region> counties = (List<Region>) request.getAttribute("counties");
	//以下为店铺所属的地区
	Region targetProvince = (Region) request.getAttribute("targetProvince");
	Region targetCity = (Region) request.getAttribute("targetCity");
	Region targetCounty = (Region) request.getAttribute("targetCounty");
%>
<%!void addRegionOption(List<Region> regions, Region targetRegion, JspWriter out) throws IOException {
		if (regions != null) {
			for (int i = 0; i < regions.size(); i++) {
				Region temp = regions.get(i);
				if (temp.getId() == targetRegion.getId()) {
					out.print("<option value=" + temp.getId() + " selected='selected'>" + temp.getName() + "</option>");
				}
				else {
					out.print("<option value=" + temp.getId() + ">" + temp.getName() + "</option>");
				}
			}
		}
	}%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改分店信息</title>
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
	<div class="container">
		<div class="row">
			<div class="col-sm-2">
				<form action="<%=basePath%>/shop.action" method="post">
					<button type="submit" class="btn btn-primary">返回店铺管理</button>
				</form>
			</div>
			<div class="col-sm-8">
				<div class="shop-info">
					<form action="<%=basePath%>/shop/modifyShop.action" method="post" onsubmit="return checkModifyShop()">
						<input name="shopId" value="<%=shop.getId()%>" class="display-none">
						<h1>店铺信息：</h1>
						<div class="input-group">
							<span class="input-group-addon">分店名称</span>
							<input id="shop-name-input" type="text" class="form-control" name="shopname" value="<%=shop.getShopname()%>">
						</div>
						<div class="input-group">
							<span class="input-group-addon">电话号码</span>
							<input id="phonenumber-input" type="text" class="form-control" name="phonenumber" value="<%=shop.getPhonenumber()%>">
						</div>
						<div class="input-group">
							<span class="input-group-addon">所在省/直辖市</span>
							<select id="provinceSelect" class="form-control" onchange="changeProvince()">
								<%
									this.addRegionOption(provinces, targetProvince, out);
								%>
							</select>
							<span class="input-group-addon">地级市</span>
							<select id="citySelect" class="form-control" onchange="changeCity()">
								<%
									this.addRegionOption(cities, targetCity, out);
								%>
							</select>
							<span class="input-group-addon">县/区</span>
							<select id="countySelect" class="form-control" name="regionId">
								<%
									this.addRegionOption(counties, targetCounty, out);
								%>
							</select>
						</div>
						<div class="input-group">
							<span class="input-group-addon">详细地址</span>
							<input id="location-input" type="text" class="form-control" name="location" value="<%=shop.getLocation()%>">
						</div>
						<div>
							<button class="btn btn-primary btn-block" type="submit">保存修改</button>
						</div>
					</form>
					<form action="<%=basePath%>/shop/toModifyShop.action" method="get">
						<input name="shopId" value="<%=shop.getId()%>" class="display-none">
						<button class="btn btn-primary btn-block">重新设置</button>
					</form>
				</div>
			</div>
			<div class="col-sm-2"></div>
		</div>
	</div>
	<s:include value="../common/footer.jsp"></s:include>
</body>
</html>