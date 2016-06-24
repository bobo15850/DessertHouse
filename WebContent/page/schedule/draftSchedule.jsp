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
					<form action="<%=basePath%>/schedule/targetShopSchedule.action">
						<h3 class="display-inline"><%=shop.getShopname()%></h3>
						<input name="shopId" value="<%=shop.getId()%>" class="display-none">
						<input name="scheduleState" value=<%=FinalValue.ScheduleState.APPROVING%> class="display-none">
						<button class="btn btn-primary">返回店铺计划页面</button>
					</form>
				</div>
				<div>
					<form action="<%=basePath%>/schedule/submitSchedule.action" method="post">
						<%
							for (int i = 0; i < products.size(); i++) {
						%>
						<input class="display-none" name="productIdList[<%=i%>]" value="<%=products.get(i).getId()%>">
						<%
							} //传递产品
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
										<th>图片</th>
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
										<td><%=productNum+1%></td>
										<td><img class="product-img" alt="暂无图片" src="<%=basePath + "/" + product.getPicture()%>"></td>
										<td><%=product.getName()%></td>
										<td><%=product.getInfo()%></td>
										<td><input type="number" class="form-control price-input"
												name="schedule.scheduleItemList[<%=dayNum%>].goodsItemList[<%=productNum%>].price" value=<%=product.getPrice()%>></td>
										<!-- number类型是bootstrap中的类型 -->
										<td><input type="number" class="form-control number-input"
												name="schedule.scheduleItemList[<%=dayNum%>].goodsItemList[<%=productNum%>].quantity" value=<%=product.getNumber()%>></td>
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


	<div style="height: 200px;">
		<p id='back-ltop' class='hide_element'>
			<a href='#top' style="text-decoration: none;">
				<span>回到顶部</span>
			</a>
		</p>

		<style>
#back-ltop {
	position: fixed;
	z-index: 999;
	right: 35px;
	bottom: 60px;
}

/* arrow icon (span tag) */
#back-ltop span {
	width: 80px;
	height: 40px;
	display: block;
	margin-bottom: 7px;
	/* rounded corners */
	-webkit-border-radius: 15px;
	-moz-border-radius: 15px;
	border-radius: 15px;
	/* transition */
	-webkit-transition: 1s;
	-moz-transition: 1s;
	transition: 1s;
}
</style>

		<script>
			$(document).ready(function() {

				// hide #back-top first 
				$("#back-ltop").hide();

				// fade in #back-top 
				$(function() {
					$(window).scroll(function() {
						if ($(this).scrollTop() > 100) {
							$('#back-ltop').fadeIn();
						} else {
							$('#back-ltop').fadeOut();
						}

					});

					// scroll body to 0px on click 
					$('#back-ltop a').click(function() {
						$('body,html').animate({
							scrollTop : 0
						}, 800);
						return false;
					});
				});
			});
		</script>
	</div>
	<s:include value="../common/footer.jsp"></s:include>
</body>
</html>