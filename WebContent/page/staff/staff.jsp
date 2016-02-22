<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page import="edu.nju.desserthouse.util.FinalValue"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>店员管理</title>
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=basePath%>/css/main.css">
<link rel="stylesheet" href="<%=basePath%>/css/staff/staff.css">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
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
						<div class="modal-dialog" role="document">
							<form action="">
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
												<input type="text" class="form-control" name="user.username">
											</div>
											<div class="input-group">
												<span class="input-group-addon">账户密码</span>
												<input type="password" class="form-control" name="user.password">
												<span class="input-group-addon">确认密码</span>
												<input type="password" class="form-control">
											</div>
											<div class="input-group">
												<span class="input-group-addon">手机号码</span>
												<input type="text" class="form-control">
											</div>
											<div class="input-group">
												<span class="input-group-addon">员工种类</span>
												<select class="form-control">
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
													<span class="input-group-addon">员工店铺所在省</span>
													<select id="provinceSelect" class="form-control" onchange="changeProvince()">
														<option value="notset" selected="selected"></option>
													</select>
													<span class="input-group-addon">地级市</span>
													<select id="citySelect" class="form-control" onchange="changeCity()">
													</select>
													<span class="input-group-addon">县/区</span>
													<select id="countySelect" class="form-control" name="regionId">
													</select>
												</div>
												<div class="input-group">
													<span class="input-group-addon">店铺名称</span>
													<select class="form-control">
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
				<div id="select-staff">筛选员工</div>
			</div>
			<div class="col-sm-10">右侧内容栏</div>
		</div>
	</div>
	<s:include value="../common/footer.jsp"></s:include>
</body>
</html>