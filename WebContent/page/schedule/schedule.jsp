<%@page import="edu.nju.desserthouse.util.FinalValue"%>
<%@page import="java.sql.Date"%>
<%@page import="edu.nju.desserthouse.model.Shop"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String basePath = request.getContextPath();
	List<Shop> shops = (List<Shop>) request.getAttribute("shops");
	List<Date> lastScheduleDates = (List<Date>) request.getAttribute("lastScheduleDates");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>计划</title>
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=basePath%>/css/main.css">
<link rel="stylesheet" href="<%=basePath%>/css/schedule/schedule.css">
<link rel="stylesheet" href="<%=basePath%>/css/shop/shop.css">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/schedule/schedule.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/shop/shop.js"></script>
</head>
<body>
	<s:include value="../common/header.jsp"></s:include>
	<div class="container">
		<div class="row">
			<div class="col-sm-1"></div>
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
							Date date = lastScheduleDates.get(i);
				%>
				<div class="shop-item" onmouseover="showModifyBtn(<%=i%>)" onmouseout="hideModifyBtn(<%=i%>)">
					<label>
						<%=i + 1%>&nbsp;
					</label>
					<label class="shop-name">
						店名：<span><%=shop.getShopname()%></span>
					</label>
					<label class="last-schedule-date">
						最后计划日期 ：<span><%=date%></span>
					</label>
					<form action="<%=basePath%>/schedule/targetShopSchedule.action" method="get" class="display-inline">
						<input name="shopId" value="<%=shop.getId()%>" class="display-none">
						<input name="scheduleState" value=<%=FinalValue.ScheduleState.APPROVING%> class="display-none">
						<button id="modify-btn-<%=i%>" type="submit" class="btn btn-primary hide">产品计划管理</button>
					</form>
				</div>
				<%
					}
					}
				%>
			</div>
			<div class="col-sm-1"></div>
		</div>
	</div>
	<s:include value="../common/footer.jsp"></s:include>
</body>
</html>