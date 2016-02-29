<%@page import="java.sql.Date"%>
<%@page import="edu.nju.desserthouse.model.Schedule"%>
<%@page import="edu.nju.desserthouse.util.FinalValue"%>
<%@page import="edu.nju.desserthouse.model.Shop"%>
<%@page import="edu.nju.desserthouse.model.Region"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String basePath = request.getContextPath();
	Shop shop = (Shop) request.getAttribute("shop");
	String shopRegionStr = (String) request.getAttribute("shopRegionStr");
	List<Region> provinces = (List<Region>) request.getAttribute("provinces");
	int scheduleState = (int) request.getAttribute("scheduleState");//计划状态
	List<Schedule> schedules = (List<Schedule>) request.getAttribute("schedules");
%>
<%!Date getNumDateAfter(Date rawDate, int num) {
		long timeLong = rawDate.getTime();
		timeLong += num * 24 * 60 * 60 * 1000;
		return new Date(timeLong);
	}%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>特定店铺产品计划</title>
<link rel="stylesheet" href="<%=basePath%>/lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=basePath%>/css/main.css">
<link rel="stylesheet" href="<%=basePath%>/css/schedule/schedule.css">
<script src="<%=basePath%>/lib/jquery/jquery-1.12.1.min.js"></script>
<script src="<%=basePath%>/lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/schedule/schedule.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/region/region.js"></script>
</head>
<body>
	<s:include value="../common/header.jsp"></s:include>
	<div class="container">
		<div class="row">
			<div class="col-sm-2">
				<div class="control-panel">
					<div class="shop-base-info">
						<h4>
							店名：<%=shop.getShopname()%></h4>
						<h5>
							电话：<%=shop.getPhonenumber()%></h5>
						<h6>
							地区：<%=shopRegionStr%></h6>
						<h6>
							地址：<%=shop.getLocation()%></h6>
					</div>
					<div>
						<form action="<%=basePath%>/schedule.action">
							<button class="btn btn-primary btn-block" type="submit">返回产品计划首页</button>
						</form>
					</div>
					<div>
						<form action="<%=basePath%>/schedule/targetShopSchedule.action">
							<input name="shopId" value="<%=shop.getId()%>" class="display-none">
							<input name="scheduleState" value="<%=FinalValue.ScheduleState.APPROVING%>" class="display-none">
							<button class="btn btn-primary btn-block<%=scheduleState == FinalValue.ScheduleState.APPROVING ? " active" : ""%>" type="submit">正在审核的产品计划</button>
						</form>
					</div>
					<div>
						<form action="<%=basePath%>/schedule/targetShopSchedule.action">
							<input name="shopId" value="<%=shop.getId()%>" class="display-none">
							<input name="scheduleState" value="<%=FinalValue.ScheduleState.APPROVE_SUCCEED%>" class="display-none">
							<button class="btn btn-primary btn-block<%=scheduleState == FinalValue.ScheduleState.APPROVE_SUCCEED ? " active" : ""%>">已通过的产品计划</button>
						</form>
					</div>
					<div>
						<form action="<%=basePath%>/schedule/targetShopSchedule.action">
							<input name="shopId" value="<%=shop.getId()%>" class="display-none">
							<input name="scheduleState" value="<%=FinalValue.ScheduleState.APPROVE_FAILED%>" class="display-none">
							<button class="btn btn-primary btn-block<%=scheduleState == FinalValue.ScheduleState.APPROVE_FAILED ? " active" : ""%>" type="submit">未通过的产品计划</button>
						</form>
					</div>
					<div>
						<form action="<%=basePath%>/schedule/draftSchedule.action">
							<input name="shopId" value="<%=shop.getId()%>" class="display-none">
							<button class="btn btn-primary btn-block">制定产品计划</button>
						</form>
					</div>
					<div>
						<button class="btn btn-primary btn-block" data-toggle="modal" data-target="#select-shop-modal">切换店铺</button>
					</div>
				</div>
				<div id="select-shop-modal" class="modal fade" tab-index="-1" role="dialog" aria-labelledby="myModalLabel">
					<div class="modal-dialog" role="document">
						<form action="<%=basePath%>/schedule/targetShopSchedule.action">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">选择店铺</h4>
								</div>
								<div class="modal-body">
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
										<input class="display-none" name="scheduleState" value="<%=FinalValue.ScheduleState.APPROVING%>">
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
			<div class="col-sm-10">
				<div class="schedule-display">
					<%
						if (schedules == null || schedules.size() == 0) {
					%>
					<h1>没有找到相关产品计划</h1>
					<%
						}
						else {
					%>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th>开始日期</th>
								<th>结束日期</th>
								<th>创建者</th>
								<th>详细信息</th>
								<th>创建时间</th>
							</tr>
						</thead>
						<tbody>
							<%
								for (int i = schedules.size() - 1; i >= 0; i--) {
										Schedule schedule = schedules.get(i);
							%>
							<tr>
								<td><%=schedules.size() - 1 - i%></td>
								<td><%=schedule.getStartDate()%></td>
								<td><%=getNumDateAfter(schedule.getStartDate(), 6)%></td>
								<td><%=schedule.getOperator().getUsername()%></td>
								<td><form action="<%=basePath%>/schedule/showTargetSchedule.action">
										<input class="display-none" name="scheduleId" value=<%=schedule.getId()%>>
										<button class="btn btn-primary">查看详情</button>
									</form></td>
								<td><%=schedule.getCreatedTime()%></td>
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
	</div>
	<s:include value="../common/footer.jsp"></s:include>
</body>
</html>