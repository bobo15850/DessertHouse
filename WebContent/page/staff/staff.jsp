<%@page import="edu.nju.desserthouse.model.User"%>
<%@page import="edu.nju.desserthouse.model.Region"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page import="edu.nju.desserthouse.util.FinalValue"%>
<%
	String basePath = request.getContextPath();
	List<Region> provinces = (List<Region>) request.getAttribute("provinces");
	List<User> staffs = (List<User>) request.getAttribute("staffs");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>店员管理</title>
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
				<div id="add-staff">
					<button class="btn btn-primary btn-block" data-toggle="modal" data-target="#add-staff-modal">添加员工</button>
					<div id="add-staff-modal" class="modal fade" tab-index="-1" role="dialog" aria-labelledby="myModalLabel">
						<div class="modal-dialog" role="document" style="width:700px;">
							<form action="<%=basePath%>/staff/addStaff.action" method="post" onsubmit="return checkAddStaff()">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h4 class="modal-title" id="myModalLabel">添加员工</h4>
									</div>
									<div class="modal-body">
										<div class="main-modal">
											<div class="input-group">
												<span class="input-group-addon">员工名称</span>
												<input type="text" class="form-control" name="staff.username">
											</div>
											<div class="input-group">
												<span class="input-group-addon">账户密码</span>
												<input type="password" class="form-control" name="staff.password">
												<span class="input-group-addon">确认密码</span>
												<input type="password" class="form-control">
											</div>
											<div class="input-group">
												<span class="input-group-addon">手机号码</span>
												<input type="text" class="form-control" name="staff.phonenumber">
											</div>
											<div class="input-group">
												<span class="input-group-addon">员工种类</span>
												<select class="form-control" name="staff.category">
													<option value=<%=FinalValue.UserCategory.BRANCH_WAITER%>>
														<%=FinalValue.UserCategory.getStrOfUserCategory(FinalValue.UserCategory.BRANCH_WAITER)%>
													</option>
													<option value=<%=FinalValue.UserCategory.HEAD_WAITER%>>
														<%=FinalValue.UserCategory.getStrOfUserCategory(FinalValue.UserCategory.HEAD_WAITER)%>
													</option>
													<option value=<%=FinalValue.UserCategory.MANAGER%>>
														<%=FinalValue.UserCategory.getStrOfUserCategory(FinalValue.UserCategory.MANAGER)%>
													</option>
												</select>
											</div>
											<div>
												<span class="input-group-addon">所属店铺</span>
											</div>
											<div id="staff-shop-selection">
												<div class="input-group">
													<span class="input-group-addon">所在省</span>
													<select id="provinceSelect" class="form-control" onchange="changeProvince()">
														<option value="notset"></option>
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
													<select id="countySelect" class="form-control">
													</select>
													<span class="input-group-btn">
														<button class="btn btn-primary" type="button" onclick="fillShopSelect()">搜索店铺</button>
													</span>
												</div>
												<label id="shop-select-result"></label>
												<div class="input-group">
													<span class="input-group-addon">店铺名称</span>
													<select id="shopSelect" class="form-control" name="shopId">
													</select>
												</div>
											</div>
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
					if (staffs == null || staffs.size() == 0) {
				%>
				<h1>没有找到员工</h1>
				<%
					}
					else {
				%>
				<table class="table table-striped" >
						<thead>
							<tr>
								<th>#</th>
								<th>用户名</th>
								<th>员工种类</th>
								<th>所属店名</th>
								
							</tr>
						</thead>
						<tbody>
				<%
						for (int i = 0; i < staffs.size(); i++) {
							User staff = staffs.get(i);
				%>
				<tr>
					<td><%=i+1%></td>
					<td><%=staff.getUsername()%></td>
					<td ><%=FinalValue.UserCategory.getStrOfUserCategory(staff.getCategory())%></td>
					<td><%=staff.getShop().getShopname()%></td>
					<td style="width:120px;"><form action="<%=basePath%>/staff/toModifyStaff.action" method="get" class="display-inline">
						<input name="staffId" value="<%=staff.getId()%>" class="display-none">
						<button id="modify-btn-<%=i%>" type="submit" class="btn btn-primary">查看或修改</button>
					</form></td>
					<td style="width:120px;"><form action="<%=basePath%>/staff/deleteStaff.action" method="get" class="display-inline" onsubmit="return confirmDelete()">
						<input name="staffId" value="<%=staff.getId()%>" class="display-none">
						<button id="delete-btn-<%=i%>" type="submit" class="btn btn-primary">刪除</button>
					</form></td>
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
		</div>
	</div>
	<s:include value="../common/footer.jsp"></s:include>
</body>
</html>