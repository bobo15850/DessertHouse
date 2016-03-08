<%@page import="edu.nju.desserthouse.model.SalesGoodsItem"%>
<%@page import="edu.nju.desserthouse.util.FinalValue"%>
<%@page import="edu.nju.desserthouse.model.User"%>
<%@page import="edu.nju.desserthouse.model.SalesRecord"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String basePath = request.getContextPath();
	Object obj = session.getAttribute("order");
	if (obj == null) {
		obj = request.getAttribute("order");
	}
	SalesRecord order = (SalesRecord) obj;
	User customer = (User) order.getCustomer();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=basePath%>/lib/bootstrap/css/bootstrap.min.css">
<script src="<%=basePath%>/lib/jquery/jquery-1.12.1.min.js"></script>
<script src="<%=basePath%>/lib/bootstrap/js/bootstrap.min.js"></script>
<title>订单详情</title>
</head>
<body>
	<s:include value="../common/header.jsp"></s:include>
	<div class="container">
		<div class="row">
			<div class="col-sm-1"></div>
			<div class="col-sm-10">
				<h1>订单详情</h1>
				<div>
					<div class="input-group">
						<span class="input-group-addon">店铺</span>
						<input type="text" class="form-control" readonly="readonly" value=<%=order.getShop().getShopname()%>>
						<span class="input-group-addon">销售员工</span>
						<input type="text" class="form-control" readonly="readonly" value=<%=order.getOperator().getUsername()%>>
						<span class="input-group-addon">创建时间</span>
						<input type="text" class="form-control" readonly="readonly" value=<%=order.getCreatedTime()%>>
					</div>
					<%
						if (order.getPayMode() == FinalValue.PayMode.WITH_CARD_CASH) {
					%>
					<label class="form-control">账户余额不足转为现金支付</label>
					<%
						}
						else if (order.getPayMode() == FinalValue.PayMode.NO_CARD_CASH) {
					%>
					<label class="form-control">没有会员卡，现金支付</label>
					<%
						}
						else if (order.getPayMode() == FinalValue.PayMode.CARD_PAY) {
					%>
					<label class="form-control">会员卡支付</label>
					<div class="input-group">
						<span class="input-group-addon">会员姓名</span>
						<input type="text" class="form-control" readonly="readonly" value=<%=customer.getUsername()%>>
						<span class="input-group-addon">会员卡号</span>
						<input type="text" class="form-control" readonly="readonly" value=<%=customer.getCardId()%>>
						<span class="input-group-addon">会员等级</span>
						<input type="text" class="form-control" readonly="readonly" value=<%=FinalValue.UserLevel.getStrOfUserLevel(customer.getLevel())%>>
					</div>
					<%
						}
					%>
					<div class="input-group">
						<span class="input-group-addon">原始总金额</span>
						<input type="text" class="form-control" readonly="readonly" value=<%=order.getRawMoney()%>>
						<span class="input-group-addon">实际总金额</span>
						<input type="text" class="form-control" readonly="readonly" value=<%=order.getRealMoney()%>>
					</div>
				</div>
				<div>
					<h4>订单明细</h4>
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
									SalesGoodsItem goodsItem = order.getGoodsItemList().get(i);
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
					if (order.getId() == 0) {
				%>
				<div>
					<form action="<%=basePath%>/sale/confirmPay.action">
						<button class="btn btn-primary float-right ">确认支付</button>
					</form>
					<form action="<%=basePath%>/sale/canclePay.action">
						<button class="btn btn-default float-right">放弃支付</button>
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