<%@page import="java.sql.Date"%>
<%@page import="edu.nju.desserthouse.util.FinalValue"%>
<%@page import="edu.nju.desserthouse.model.Product"%>
<%@page import="java.util.List"%>
<%@page import="edu.nju.desserthouse.model.Shop"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String basePath = request.getContextPath();
	Shop shop = (Shop) request.getAttribute("shop");//店铺
	List<Date> dates = (List<Date>) request.getAttribute("dates");//产品计划的日期列表，按顺序
	List<Product> products = (List<Product>) request.getAttribute("products");//所有产品
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>特定店铺产品计划</title>
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=basePath%>/css/main.css">
<link rel="stylesheet" href="<%=basePath%>/css/schedule/schedule.css">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/schedule/schedule.js"></script>
</head>
<body>
	<s:include value="../common/header.jsp"></s:include>
	<div class="container">
		<div class="row">
			<div class="col-sm-1"></div>
			<div class="col-sm-10">
				<div>
					<form action="<%=basePath%>/schedule/targetShopSchedule.action">
						<h3 class="display-inline"><%=shop.getShopname()%></h3>
						<input name="shopId" value="<%=shop.getId()%>" class="display-none">
						<input name="scheduleState" value=<%=FinalValue.ScheduleState.APPROVING%> class="display-none">
						<button class="btn btn-primary">返回店铺计划页面</button>
					</form>
				</div>
				<div>
					<form action="<%=basePath%>/schedule/submitSchedule.action">
						<%
							for (int dayNum = 0; dayNum < 7; dayNum++) {
								Date date = dates.get(dayNum);
						%>
						<div>
							<h2 class="text-center">
								第<%=dayNum%>天：<%=date%>
							</h2>
							<table class="table table-striped">
								<thead>
									<tr>
										<th>#</th>
										<th>产品名称</th>
										<th>产品描述</th>
										<th>单价</th>
										<th>可售数量</th>
									</tr>
								</thead>
								<tbody>
									<%
										for (int productNum = 0; productNum < products.size(); productNum++) {
												Product product = products.get(productNum);
									%>
									<tr>
										<td><%=productNum%></td>
										<td><%=product.getName()%></td>
										<td><%=product.getInfo()%></td>
										<td><input class="form-control"></td>
										<td><input class="form-control" value="100"></td>
									</tr>
									<%
										}
									%>
								</tbody>
							</table>
						</div>
						<hr>
						<%
							}
						%>
						<input name="shopId" value="<%=shop.getId()%>" class="display-none">
						<button class="btn btn-primary float-right">提交该计划给总经理审批</button>
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