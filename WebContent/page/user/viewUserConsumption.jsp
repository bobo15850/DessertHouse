<%@page import="edu.nju.desserthouse.model.SalesRecord"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String basePath = request.getContextPath();
	Object inquireStr = request.getAttribute("inquireStr");//查询条件
	Object obj = request.getAttribute("orders");
	List<SalesRecord> orders = null;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=basePath%>/lib/bootstrap/css/bootstrap.min.css">
<script src="<%=basePath%>/lib/jquery/jquery-1.12.1.min.js"></script>
<script src="<%=basePath%>/lib/bootstrap/js/bootstrap.min.js"></script>
<title>查看会员消费信息</title>
</head>
<body>
	<s:include value="../common/header.jsp"></s:include>
	<div class="container">
		<div class="row">
			<div class="col-sm-1"></div>
			<div class="col-sm-10">
				<div>
					<form action="<%=basePath%>/viewUserConsumption.action" method="post">
						<div class="input-group">
							<span class="input-group-addon">查找消费记录</span>
							<input name="inquire-input" type="text" class="form-control" value="<%=inquireStr != null ? inquireStr : ""%>"
								placeholder="请输入用户名或注册手机号或会员卡号，请注意大小写">
							<span class="input-group-btn">
								<button class="btn btn-primary" type="submit">查找</button>
							</span>
						</div>
					</form>
					<%
						if (obj != null) {
							orders = (List<SalesRecord>) obj;
					%>
					<div>
					<br>
					<br>
						<h2>销售列表</h2>
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
									for (int i = orders.size() - 1; i >= 0; i--) {
											SalesRecord salesRecord = orders.get(i);
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
					</div>
					<%
						}
						else {
							out.print("<h2>没有找到任何相关记录</h2>");
						}
					%>
				</div>
			</div>
			<div class="col-sm-1"></div>
		</div>
	</div>
	<s:include value="../common/footer.jsp"></s:include>
</body>
</html>