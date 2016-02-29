<%@page import="edu.nju.desserthouse.util.FinalValue"%>
<%@page import="edu.nju.desserthouse.model.Schedule"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Date"%>
<%@page import="edu.nju.desserthouse.util.UserBase"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String basePath = request.getContextPath();
	UserBase userBase = (UserBase) session.getAttribute("userBase");
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
<title>审批</title>
<link rel="stylesheet" href="<%=basePath%>/lib/bootstrap/css/bootstrap.min.css">
<script src="<%=basePath%>/lib/jquery/jquery-1.12.1.min.js"></script>
<script src="<%=basePath%>/lib/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="<%=basePath%>/css/main.css">
<link rel="stylesheet" href="<%=basePath%>/css/schedule/schedule.css">
<script type="text/javascript" src="<%=basePath%>/js/schedule/schedule.js"></script>
</head>
<body>
	<s:include value="../common/header.jsp"></s:include>
	<div class="container">
		<div class="row">
			<div class="col-sm-2">
				<div class="control-panel">
					<div class="user-base-info">
						<h4>
							用户名：<%=userBase.getUsername()%></h4>
						<h5>
							当前日期：<%=new Date(System.currentTimeMillis())%>
						</h5>
					</div>
					<br> <br> <br>
					<div>
						<form action="<%=basePath%>/approval.action">
							<input class="display-none" name="scheduleState" value=<%=FinalValue.ScheduleState.APPROVING%>>
							<button class="btn btn-primary<%=scheduleState == FinalValue.ScheduleState.APPROVING ? " active" : ""%>">待审核的产品计划</button>
						</form>
					</div>
					<div>
						<form action="<%=basePath%>/approval.action">
							<input class="display-none" name="scheduleState" value=<%=FinalValue.ScheduleState.APPROVE_FAILED%>>
							<button class="btn btn-primary<%=scheduleState == FinalValue.ScheduleState.APPROVE_FAILED ? " active" : ""%>">未通过的产品计划</button>
						</form>
					</div>
					<div>
						<form action="<%=basePath%>/approval.action">
							<input class="display-none" name="scheduleState" value=<%=FinalValue.ScheduleState.APPROVE_SUCCEED%>>
							<button class="btn btn-primary<%=scheduleState == FinalValue.ScheduleState.APPROVE_SUCCEED ? " active" : ""%>">已通过的产品计划</button>
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
								<th>店铺</th>
								<th>开始日期</th>
								<th>结束日期</th>
								<th>创建者</th>
								<th>详细信息</th>
								<th>创建时间</th>
							</tr>
						</thead>
						<tbody>
							<%
								for (int k = schedules.size() - 1; k >= 0; k--) {

										int i = k;
										if (scheduleState == FinalValue.ScheduleState.APPROVING) {
											i = schedules.size() - 1 - k;
										} //使得待审批的产品计划按照从旧到新的顺序展示，审批过的按照从心到就的顺序展示
										Schedule schedule = schedules.get(i);
							%>
							<tr>
								<td><%=schedules.size() - 1 - k%></td>
								<td><%=schedule.getShop().getShopname()%></td>
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