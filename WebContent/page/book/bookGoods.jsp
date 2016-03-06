<%@page import="java.sql.Date"%>
<%@page import="edu.nju.desserthouse.model.Goods"%>
<%@page import="java.util.List"%>
<%@page import="edu.nju.desserthouse.model.Shop"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String basePath = request.getContextPath();
	Shop shop = (Shop) request.getAttribute("shop");
	String shopRegionStr = (String) request.getAttribute("shopRegionStr");
	Date date = (Date) request.getAttribute("date");
	List<Goods> goodsList = (List<Goods>) request.getAttribute("goodsList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=basePath%>/lib/bootstrap/css/bootstrap.min.css">
<script src="<%=basePath%>/lib/jquery/jquery-1.12.1.min.js"></script>
<script src="<%=basePath%>/lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/lib/moment.js"></script>
<script type="text/javascript" src="<%=basePath%>/lib/bootstrap-datetimepicker.js"></script>
<link rel="stylesheet" href="<%=basePath%>/css/main.css">
<link rel="stylesheet" href="<%=basePath%>/css/sale/sale.css">
<link rel="stylesheet" href="<%=basePath%>/css/book/book.css">
<script type="text/javascript" src="<%=basePath%>/js/sale/sale.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/book/book.js"></script>
<title>预定商品展示</title>
</head>
<body>
	<s:include value="../common/header.jsp"></s:include>
	<div class="container">
		<div class="row">
			<div class="col-sm-2">
				<div class="control-panel">
					<div class="shop-info">
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
						<button id="goods-list-btn" class="btn btn-info btn-block" onclick="showPanel(this)" value="goods-list-panel">预定商品</button>
					</div>
					<div>
						<button id="goods-select-btn" class="btn btn-primary btn-block" onclick="showPanel(this)" value="goods-select-panel">购物车</button>
					</div>
					<div>
						<button class="btn btn-primary btn-block" data-toggle="modal" data-target="#selsct-date">切换日期</button>
						<div id="selsct-date" class="modal fade" tab-index="-1" role="dialog" aria-labelledby="myModalLabel">
							<div class="modal-dialog" role="document">
								<form action="<%=basePath%>/book/shopTarDay.action" method="post">
									<input type="hidden" name="shopId" value=<%=shop.getId()%>>
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
											<h4 class="modal-title" id="myModalLabel">选择日期</h4>
										</div>
										<div class="modal-body">
											<div class="main-modal">
												<div class="input-group">
													<span class="input-group-addon">日期</span>
													<input id="dateInput" type="text" class="form-control" name="targetDateStr" value=<%=date%>>
												</div>
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
					<div>
						<form action="<%=basePath%>/shopSelectOfBook.action">
							<button class="btn btn-primary btn-block">返回首页</button>
						</form>
					</div>
				</div>
			</div>
			<div class="col-sm-10">
				<div id="goods-list-panel" class="unhide">
					<div class="display-inline float-left">
						<form action="<%=basePath%>/book/shopPreDay.action">
							<input type="hidden" name="shopId" value=<%=shop.getId()%>>
							<input type="hidden" name="curDateStr" value=<%=date.toString()%>>
							<button class="btn btn-primary">前一天</button>
						</form>
					</div>
					<label class="date-label"><%=date%></label>
					<div class="float-right">
						<form action="<%=basePath%>/book/shopNextDay.action">
							<input type="hidden" name="shopId" value=<%=shop.getId()%>>
							<input type="hidden" name="curDateStr" value=<%=date.toString()%>>
							<button class="btn btn-primary">后一天</button>
						</form>
					</div>
					<%
						if (goodsList == null || goodsList.size() == 0) {
					%>
					<h1>没有任何商品可供预定</h1>
					<%
						}
						else {
					%>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th>图片</th>
								<th>商品名称</th>
								<th>商品描述</th>
								<th>单价</th>
								<th>剩余数量</th>
								<th>选择</th>
							</tr>
						</thead>
						<tbody>
							<%
								for (int i = 0; i < goodsList.size(); i++) {
										Goods goods = goodsList.get(i);
							%>
							<tr>
								<td><%=i%></td>
								<td><img class="goods-img" alt="商品图片" src="<%=basePath + "/" + goods.getProduct().getPicture()%>"></td>
								<td id="name-<%=goods.getId()%>"><%=goods.getProduct().getName()%></td>
								<td><%=goods.getProduct().getInfo()%></td>
								<td id="price-<%=goods.getId()%>"><%=goods.getPrice()%></td>
								<td id="quantity-<%=goods.getId()%>"><%=goods.getQuantity()%></td>
								<td><button class="btn btn-primary" value="<%=goods.getId()%>" onclick="chooseGoods(this)">预定</button></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
					<div class="modal fade" id="numInputModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="modalLabel">预定商品</h4>
								</div>
								<div class="modal-body">
									<div class="input-group">
										<span class="input-group-addon">商品名称</span>
										<input id="cur-goods-name" type="text" class="form-control" readonly="readonly">
									</div>
									<div class="input-group">
										<span class="input-group-addon">商品单价</span>
										<input id="cur-goods-price" type="number" class="form-control" readonly="readonly">
									</div>
									<div class="input-group">
										<span class="input-group-addon">预定数量</span>
										<input id="buy-quantity" type="number" class="form-control" value="1">
										<span class="input-group-addon">剩余数量</span>
										<input id="max-quantity" type="number" class="form-control" readonly="readonly">
									</div>
									<div class="input-group">
										<span class="input-group-addon">备注信息</span>
										<input type="text" class="form-control" placeholder="请输入备注信息">
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
									<button type="button" class="btn btn-primary" onclick="addSelectGoods()">加入购物车</button>
								</div>
							</div>
						</div>
					</div>
					<%
						}
					%>

				</div>
				<div id="goods-select-panel" class="hide">
					<h1>选购的商品</h1>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>商品编号</th>
								<th>商品名称</th>
								<th>单价</th>
								<th>数量</th>
								<th>删除</th>
							</tr>
						</thead>
						<tbody id="goods-select-table">
						</tbody>
					</table>
					<label id="generateOrderMsg"></label>
					<button id="generate-order-btn" class="btn btn-primary float-right" onclick="generateOrder()">生成订单</button>
					<button id="clear-all-select" class="btn btn-default float-right" onclick="deleteAllSelect()">清空购物车</button>
				</div>
				<div id="order-panel" class="hide">
					<h1>支付订单</h1>
					<form action="<%=basePath%>/book/submitBook.action">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>#</th>
									<th>商品名称</th>
									<th>商品编号</th>
									<th>商品单价</th>
									<th>商品数量</th>
									<th>总价</th>
								</tr>
							</thead>
							<tbody id="order-table"></tbody>
						</table>
						<input type="hidden" name="shopId" value=<%=shop.getId()%>>
						<button class="btn btn-primary float-right" type="submit">提交订单</button>
						<button class="btn btn-default float-right" type="button" onclick="cancleOrder()">取消订单</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<s:include value="../common/footer.jsp"></s:include>
</body>
</html>