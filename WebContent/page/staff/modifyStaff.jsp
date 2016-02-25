<%@page import="edu.nju.desserthouse.util.FinalValue"%>
<%@page import="edu.nju.desserthouse.model.Shop"%>
<%@page import="java.io.IOException"%>
<%@page import="edu.nju.desserthouse.model.Region"%>
<%@page import="java.util.List"%>
<%@page import="edu.nju.desserthouse.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String basePath = request.getContextPath();
	User staff = (User) request.getAttribute("staff");
	//以下为所有的地区
	List<Region> provinces = (List<Region>) request.getAttribute("provinces");
	List<Region> cities = (List<Region>) request.getAttribute("cities");
	List<Region> counties = (List<Region>) request.getAttribute("counties");
	//以下为店铺所属的地区
	Region targetProvince = (Region) request.getAttribute("targetProvince");
	Region targetCity = (Region) request.getAttribute("targetCity");
	Region targetCounty = (Region) request.getAttribute("targetCounty");
	//
	List<Shop> shopes = (List<Shop>) request.getAttribute("shopes");//该县区内所有的店铺
	Shop targetShop = (Shop) request.getAttribute("targetShop");
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
<title>修改员工信息</title>
<link rel="stylesheet" href="<%=basePath%>/lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=basePath%>/css/main.css">
<link rel="stylesheet" href="<%=basePath%>/css/staff/staff.css">
<script src="<%=basePath%>/lib/jquery/jquery-1.12.1.min.js"></script>
<script src="<%=basePath%>/lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/staff/staff.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/region/region.js"></script>
</head>
<body>
	<s:include value="../common/header.jsp"></s:include>
	<div class="container">
		<div class="row">
			<div class="col-sm-2">
				<form action="<%=basePath%>/staff.action" method="post">
					<button type="submit" class="btn btn-primary">返回员工管理</button>
				</form>
			</div>
			<div class="col-sm-8">
				<div class="staff-info">
					<form action="<%=basePath%>/staff/modifyStaff.action" method="post" onsubmit="return checkModifyStaff()">
						<input name="staffId" value="<%=staff.getId()%>" class="display-none">
						<h1>员工信息：</h1>
						<div class="input-group">
							<span class="input-group-addon">员工名称</span>
							<input id="staff-name-input" type="text" class="form-control" name="staffname" value="<%=staff.getUsername()%>">
						</div>
						<div class="input-group">
							<span class="input-group-addon">电话号码</span>
							<input id="phonenumber-input" type="text" class="form-control" name="phonenumber" value="<%=staff.getPhonenumber()%>">
						</div>
						<div class="input-group">
							<span class="input-group-addon">员工种类</span>
							<select class="form-control" name="category">
								<option value=<%=FinalValue.UserCategory.BRANCH_WAITER%>
									<%=FinalValue.UserCategory.BRANCH_WAITER == staff.getCategory() ? " selected='selected'" : ""%>>
									<%=FinalValue.UserCategory.getStrOfUserCategory(FinalValue.UserCategory.BRANCH_WAITER)%>
								</option>
								<option value=<%=FinalValue.UserCategory.HEAD_WAITER%>
									<%=FinalValue.UserCategory.HEAD_WAITER == staff.getCategory() ? " selected='selected'" : ""%>>
									<%=FinalValue.UserCategory.getStrOfUserCategory(FinalValue.UserCategory.HEAD_WAITER)%>
								</option>
								<option value=<%=FinalValue.UserCategory.MANAGER%> <%=FinalValue.UserCategory.MANAGER == staff.getCategory() ? " selected='selected'" : ""%>>
									<%=FinalValue.UserCategory.getStrOfUserCategory(FinalValue.UserCategory.MANAGER)%>
								</option>
							</select>
						</div>
						<div>
							<span class="input-group-addon">所属店铺</span>
						</div>
						<div id="staff-shop-selection">
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
								<select id="countySelect" class="form-control">
									<%
										this.addRegionOption(counties, targetCounty, out);
									%>
								</select>
								<span class="input-group-btn">
									<button class="btn btn-primary" type="button" onclick="fillShopSelect()">搜索店铺</button>
								</span>
							</div>
							<label id="shop-select-result"></label>
							<div class="input-group">
								<span class="input-group-addon">店铺名称</span>
								<select id="shopSelect" class="form-control" name="shopId">
									<%
										if (shopes != null) {
											for (int i = 0; i < shopes.size(); i++) {
												Shop temp = shopes.get(i);
												if (temp.getId() == targetShop.getId()) {
													out.print("<option value=" + temp.getId() + " selected='selected'>" + temp.getShopname() + "（"
															+ temp.getLocation() + "）" + "</option>");
												}
												else {
													out.print("<option value=" + temp.getId() + ">" + temp.getShopname() + "（" + temp.getLocation()
															+ "）" + "</option>");
												}
											}
										}
									%>
								</select>
							</div>
						</div>
						<div>
							<span class="input-group-addon">---</span>
						</div>
						<div>
							<button class="btn btn-primary btn-block" type="submit">保存修改</button>
						</div>
					</form>
					<form action="<%=basePath%>/staff/toModifyStaff.action" method="get">
						<input name="staffId" value="<%=staff.getId()%>" class="display-none">
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