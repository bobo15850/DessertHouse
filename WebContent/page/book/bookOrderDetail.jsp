<%@page import="java.sql.Date"%>
<%@page import="edu.nju.desserthouse.util.FinalValue"%>
<%@page import="edu.nju.desserthouse.model.BookGoodsItem"%>
<%@page import="edu.nju.desserthouse.model.BookRecord"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String basePath = request.getContextPath();
	BookRecord order = (BookRecord) request.getAttribute("order");//订单
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=basePath%>/lib/bootstrap/css/bootstrap.min.css">
<script src="<%=basePath%>/lib/jquery/jquery-1.12.1.min.js"></script>
<script src="<%=basePath%>/lib/bootstrap/js/bootstrap.min.js"></script>
<title>预订单详情</title>
</head>
<body>
	<s:include value="../common/header.jsp"></s:include>
	<div class="container">
		<div class="row">
			<div class="col-sm-1">
				<form action="<%=basePath%>/consumptionRecord.action">
					<input type="hidden" name="orderState" value=<%=order.getState()%>>
					<button class="btn btn-primary btn-block">返回</button>
				</form>
			</div>
			<div class="col-sm-10">
				<h1>订单详情</h1>
				<div class="input-group">
					<span class="input-group-addon">店铺</span>
					<input type="text" class="form-control" readonly="readonly" value=<%=order.getShop().getShopname()%>>
					<span class="input-group-addon">取货时间</span>
					<input type="text" class="form-control" readonly="readonly" value=<%=order.getTargetDate()%>>
				</div>
				<div class="input-group">
					<span class="input-group-addon">下单者</span>
					<input type="text" class="form-control" readonly="readonly" value=<%=order.getCustomer().getUsername()%>>
					<span class="input-group-addon">下单时间</span>
					<input type="text" class="form-control" readonly="readonly" value=<%=order.getCreatedTime().toLocalDateTime()%>>
				</div>
				<div class="input-group">
					<span class="input-group-addon">原始总金额</span>
					<input type="text" class="form-control" readonly="readonly" value=<%=order.getRawMoney()%>>
					<span class="input-group-addon">实际总金额</span>
					<input type="text" class="form-control" readonly="readonly" value=<%=order.getRealMoney()%>>
				</div>
				<div>
					<h2>订单明细</h2>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th>商品名称</th>
								<th>单价</th>
								<th>数量</th>
								<th>小计</th>
							</tr>
						</thead>
						<tbody>
							<%
								for (int i = 0; i < order.getGoodsItemList().size(); i++) {
									BookGoodsItem goodsItem = order.getGoodsItemList().get(i);
							%>
							<tr>
								<td><%=i%></td>
								<td><%=goodsItem.getGoods().getProduct().getName()%></td>
								<td><%=goodsItem.getGoods().getPrice()%></td>
								<td><%=goodsItem.getQuantity()%></td>
								<td><%=goodsItem.getMoney()%></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
				</div>
				<%
					if (order.getState() == FinalValue.BookState.NOT_PAY) {
				%>
				<div>
					<form action="<%=basePath%>/book/payBookOrder.action">
						<input type="hidden" name="orderId" value=<%=order.getId()%>>
						<button class="btn btn-primary float-right ">支付订单</button>
					</form>
					<form action="<%=basePath%>/consumptionRecord.action">
						<button class="btn btn-info float-right">暂不支付</button>
					</form>
					<form action="<%=basePath%>/book/cancleBookOrder.action">
						<input type="hidden" name="orderId" value=<%=order.getId()%>>
						<button class="btn btn-default float-right">取消订单</button>
					</form>
				</div>
				<%
					}
					else if (order.getState() == FinalValue.BookState.PAY) {
				%>
				<div>
					<form action="<%=basePath%>/book/confirmBook.action">
						<input type="hidden" name="orderId" value=<%=order.getId()%>>
						<button class="btn btn-primary float-right"
							<%=order.getTargetDate().toString().equals(new Date(System.currentTimeMillis())) ? ""
						: " disabled='disabled'"%>>确认收货</button>
						<!-- 只有当天可以确认收货 -->
					</form>
					<form action="<%=basePath%>/book/cancleBookOrder.action">
						<input type="hidden" name="orderId" value=<%=order.getId()%>>
						<button class="btn btn-default float-right"
							<%=order.getTargetDate().toString().equals(new Date(System.currentTimeMillis()))
						? " disabled='disabled'" : ""%>>取消预定</button>
					</form>
				</div>
				<%
					}
				%>
			</div>
			<div class="col-sm-1"></div>
		</div>
	</div>
	<s:include value="../common/footer.jsp"></s:include>
</body>
</html>