<%@page import="edu.nju.desserthouse.model.Product"%>
<%@page import="edu.nju.desserthouse.model.ScheduleGoodsItem"%>
<%@page import="edu.nju.desserthouse.model.ScheduleItem"%>
<%@page import="edu.nju.desserthouse.model.Shop"%>
<%@page import="edu.nju.desserthouse.model.Schedule"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String basePath = request.getContextPath();
	Schedule schedule = (Schedule) request.getAttribute("schedule");
	Shop shop = schedule.getShop();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改产品计划</title>
<link rel="stylesheet" href="<%=basePath%>/lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=basePath%>/css/main.css">
<link rel="stylesheet" href="<%=basePath%>/css/schedule/schedule.css">
<script src="<%=basePath%>/lib/jquery/jquery-1.12.1.min.js"></script>
<script src="<%=basePath%>/lib/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<s:include value="../common/header.jsp"></s:include>
	<div class="container">
		<div class="row">
			<div class="col-sm-1"></div>
			<div class="col-sm-10">
				<div>
					<form action="<%=basePath%>/schedule/targetShopSchedule.action" class="display-inline">
						<h3 class="display-inline"><%=shop.getShopname()%></h3>
						<input name="shopId" value="<%=shop.getId()%>" class="display-none">
						<input name="scheduleState" value=<%=schedule.getState()%> class="display-none">
						<button class="btn btn-primary">放弃修改返回店铺产品计划</button>
					</form>
				</div>
				<div>
					<form action="<%=basePath%>/schedule/submitModify.action">
						<%
							for (int dayNum = 0; dayNum < schedule.getScheduleItemList().size(); dayNum++) {
								ScheduleItem scheduleItem = schedule.getScheduleItemList().get(dayNum);
						%>
						<div>
							<h2 class="text-center">
								第<%=dayNum%>天：<%=scheduleItem.getEffectiveDate()%>
							</h2>
							<table class="table table-striped">
								<thead>
									<tr>
										<th>#</th>
										<th>图片</th>
										<th>产品名称</th>
										<th>产品描述</th>
										<th>单价</th>
										<th>可售数量</th>
									</tr>
								</thead>
								<tbody>
									<%
										for (int productNum = 0; productNum < scheduleItem.getGoodsItemList().size(); productNum++) {
												ScheduleGoodsItem goodsItem = scheduleItem.getGoodsItemList().get(productNum);
												Product product = goodsItem.getProduct();
									%>
									<tr>
										<td><%=productNum%></td>
										<td><img class="product-img" alt="暂无图片" src="<%=basePath + "/" + product.getPicture()%>"></td>
										<td><%=product.getName()%></td>
										<td><%=product.getInfo()%></td>
										<td><input type="number" class="form-control price-input"
												name="schedule.scheduleItemList[<%=dayNum%>].goodsItemList[<%=productNum%>].price" value=<%=goodsItem.getPrice()%>></td>
										<td><input type="number" class="form-control number-input"
												name="schedule.scheduleItemList[<%=dayNum%>].goodsItemList[<%=productNum%>].quantity" value=<%=goodsItem.getQuantity()%>></td>
									</tr>
									<%
										}
									%>
								</tbody>
							</table>
						</div>
						<%
							}
						%>
						<input name="shopId" value="<%=shop.getId()%>" class="display-none">
						<input name="scheduleId" value="<%=schedule.getId()%>" class="display-none">
						<button class="btn btn-primary float-right">重新提交该计划给总经理审批</button>
						<button class="btn btn-default float-right" type="reset">全部重新设置</button>
					</form>
				</div>
			</div>
			<div class="col-sm-1"></div>
		</div>
	</div>
	<s:include value="../common/footer.jsp"></s:include>
</body>
</html>