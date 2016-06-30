<%@page import="edu.nju.desserthouse.util.UserBase"%>
<%@page import="edu.nju.desserthouse.util.FinalValue"%>
<%@page import="edu.nju.desserthouse.model.SalesRecord"%>
<%@page import="edu.nju.desserthouse.model.BookRecord"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String basePath = request.getContextPath();
	Object orders = request.getAttribute("orders");
	int orderState = (int) request.getAttribute("orderState");
	UserBase userBase = (UserBase) session.getAttribute("userBase");
	List<BookRecord> bookRecords = null;
	List<SalesRecord> salesRecords = null;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=basePath%>/lib/bootstrap/css/bootstrap.min.css">
<script src="<%=basePath%>/lib/jquery/jquery-1.12.1.min.js"></script>
<script src="<%=basePath%>/lib/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="<%=basePath%>/css/main.css">
<title>我的订单</title>
</head>
<body>
	<s:include value="../common/header.jsp"></s:include>
	<div class="container">
		<div class="row">
			<div class="col-sm-2">
				<div class="control-panel">
					<br>
					<div class="user-info">
						<h1>
							<%=userBase.getUsername()%></h1>
					</div>
					<br> <br> <br>
					<div>
						<form action="<%=basePath%>/consumptionRecord.action">
							<input type="hidden" name="orderState" value=<%=FinalValue.BookState.NOT_PAY%>>
							<button class="btn btn-primary btn-block<%=orderState == FinalValue.BookState.NOT_PAY ? " active" : ""%>">未付款订单</button>
						</form>
					</div>
					<div>
						<form action="<%=basePath%>/consumptionRecord.action">
							<input type="hidden" name="orderState" value=<%=FinalValue.BookState.PAY%>>
							<button class="btn btn-primary btn-block<%=orderState == FinalValue.BookState.PAY ? " active" : ""%>">未收货订单</button>
						</form>
					</div>
					<div>
						<form action="<%=basePath%>/consumptionRecord.action">
							<input type="hidden" name="orderState" value=<%=FinalValue.BookState.FINISH%>>
							<button class="btn btn-primary btn-block<%=orderState == FinalValue.BookState.FINISH ? " active" : ""%>">已完成订单</button>
						</form>
					</div>
					<div>
						<form action="<%=basePath%>/consumptionRecord.action">
							<input type="hidden" name="orderState" value=<%=FinalValue.BookState.CANCLE%>>
							<button class="btn btn-primary btn-block<%=orderState == FinalValue.BookState.CANCLE ? " active" : ""%>">已取消订单</button>
						</form>
					</div>
					<div>
						<form action="<%=basePath%>/consumptionRecord.action">
							<input type="hidden" name="orderState" value=<%=FinalValue.SALES_RECORD%>>
							<button class="btn btn-primary btn-block<%=orderState == FinalValue.SALES_RECORD ? " active" : ""%>">实体店订单</button>
						</form>
					</div>
				</div>
			</div>
			<div class="col-sm-10">
				<%
					if (orders == null) {
						%>
						<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img class="crown" src="<%= basePath%>/image/coff-key.png">&nbsp;&nbsp;&nbsp;没有该种订单&nbsp;&nbsp;&nbsp;<img class="crown" src="<%= basePath%>/image/coff-key.png"></h1> 
						<% 
					}
					else {
						if (orderState == FinalValue.SALES_RECORD) {
							salesRecords = (List<SalesRecord>) orders;
				%>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>#</th>
							<th>店铺</th>
							<th>商品总价</th>
							<th>实际付款</th>
							<th>服务员</th>
							<th>购买时间</th>
							<th>查看详情</th>
						</tr>
					</thead>
					<tbody>
						<%
							for (int i = 0; i < salesRecords.size(); i++) {
										SalesRecord salesRecord = salesRecords.get(i);
						%>
						<tr>
							<td><%=i%></td>
							<td><%=salesRecord.getShop().getShopname()%></td>
							<td><%=salesRecord.getRawMoney()%></td>
							<td><%=salesRecord.getRealMoney()%></td>
							<td><%=salesRecord.getOperator().getUsername()%></td>
							<td><%=salesRecord.getCreatedTime()%></td>
							<td><form action="<%=basePath%>/sale/viewOrder.action">
									<input type="hidden" name="orderId" value=<%=salesRecord.getId()%>>
									<button class="btn btn-primary">查看详情</button>
								</form></td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>

				<%
					}
						else {
							bookRecords = (List<BookRecord>) orders;
				%>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>#</th>
							<th>店铺</th>
							<th>取货日期</th>
							<th>商品总价</th>
							<th>实际付款</th>
							<th>下单时间</th>
							<th>查看详情</th>
						</tr>
					</thead>
					<tbody>
						<%
							for (int i = 0; i < bookRecords.size(); i++) {
										BookRecord bookRecord = bookRecords.get(i);
						%>
						<tr>
							<td><%=i%></td>
							<td><%=bookRecord.getShop().getShopname()%></td>
							<td><%=bookRecord.getTargetDate()%></td>
							<td><%=bookRecord.getRawMoney()%></td>
							<td><%=bookRecord.getRealMoney()%></td>
							<td><%=bookRecord.getCreatedTime()%></td>
							<td><form action="<%=basePath%>/book/viewBookOrder.action">
									<input type="hidden" name="orderId" value=<%=bookRecord.getId()%>>
									<button class="btn btn-primary">查看详情</button>
								</form></td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
				<%
					}
					}
				%>
			</div>
		</div>
	</div>
	<s:include value="../common/footer.jsp"></s:include>
</body>
</html>