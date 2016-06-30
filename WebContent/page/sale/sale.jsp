<%@page import="edu.nju.desserthouse.model.Goods"%>
<%@page import="java.util.List"%>
<%@page import="edu.nju.desserthouse.model.User"%>
<%@page import="edu.nju.desserthouse.model.Shop"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String basePath = request.getContextPath();
	User staff = (User) request.getAttribute("staff");//员工
	Shop shop = staff.getShop();//员工所属店铺
	List<Goods> goodsList = (List<Goods>) request.getAttribute("goodsList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>销售</title>
<link rel="stylesheet" href="<%=basePath%>/lib/bootstrap/css/bootstrap.min.css">
<script src="<%=basePath%>/lib/jquery/jquery-1.12.1.min.js"></script>
<script src="<%=basePath%>/lib/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="<%=basePath%>/css/main.css">
<link rel="stylesheet" href="<%=basePath%>/css/sale/sale.css">
<script type="text/javascript" src="<%=basePath%>/js/sale/sale.js"></script>
</head>
<body>
	<s:include value="../common/header.jsp"></s:include>
	<div class="container">
		<div class="row">
			<div class="col-sm-2">
				<div class="control-panel">
					<div class="base-info">
						<h4>
							店名：<%=shop.getShopname()%></h4>
						<h4>
							服务员：<%=staff.getUsername()%></h4>
					</div>
					<br> <br> <br>
					<div>
						<button id="goods-list-btn" class="btn btn-primary btn-block" onclick="showPanel(this)" value="goods-list-panel">选购商品</button>
					</div>
					<div>
						<button id="goods-select-btn" class="btn btn-primary btn-block" onclick="showPanel(this)" value="goods-select-panel">购物车</button>
					</div>
				</div>
			</div>
			<div class="col-sm-10">
				<div id="goods-list-panel" class="unhide">
					<%
						if (goodsList == null || goodsList.size() == 0) {
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
					<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img class="crown" src="<%= basePath%>/image/coff-key.png">&nbsp;&nbsp;&nbsp;没有任何商品可供购买&nbsp;&nbsp;&nbsp;<img class="crown" src="<%= basePath%>/image/coff-key.png"></h1>
					<%
						}
						else {
					%>
							<%
								for (int i = 0; i < goodsList.size(); i++) {
										Goods goods = goodsList.get(i);
							%>
								<div class="good">
									<img alt="商品图片" src="<%=basePath + "/" + goods.getProduct().getPicture()%>" />
									<div class="good_col1">
										<span id="name-<%=goods.getId()%>"><%=goods.getProduct().getName()%></span>
										<div class="g_price">
											<small><span class="glyphicon glyphicon-yen"></span></small><span id="price-<%=goods.getId()%>"><%=goods.getPrice()%></span>
										</div>
									</div>
									<div class="good_col2">
										<span><%=goods.getProduct().getInfo()%></span>
									</div>
									<div class="good_col3">
										<span class="g_remain">剩余<span id="quantity-<%=goods.getId()%>"><%=goods.getQuantity()%></span>件</span>
										<div class="g_opera">
											<button class="btn btn-primary" value="<%=goods.getId()%>" onclick="chooseGoods(this)"><span class="glyphicon glyphicon-edit"></span></button>
										</div>
									</div>
								</div>
							<%
								}
							%>
					<div class="modal fade" id="numInputModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="modalLabel">购买商品</h4>
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
										<span class="input-group-addon">购买数量</span>
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
					<h2>选购的商品</h2>
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
					<br>
					<button id="generate-order-btn" class="btn btn-primary float-right" onclick="generateOrder()">生成订单</button>
					<button id="clear-all-select" class="btn btn-default float-right" onclick="deleteAllSelect()">清空购物车</button>
				</div>
				<div id="order-panel" class="hide">
					<h1>支付订单</h1>
					<form action="<%=basePath%>/sale/submitOrder.action">
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
						<div class="input-group">
							<span class="input-group-addon">会员卡号/手机号码</span>
							<input name="identity" type="text" class="form-control" onchange="validateId(this)" id="identity" placeholder="请输入会员卡号或注册手机号码，如没有会员卡请不要填写" />
						</div>
						<div class="clearfix"></div>
						<div id="discount-panel" class="hide">
							<p>会员等级为：<strong>会员</strong>，享受<strong>9</strong>折</p>
						</div>
						<div id="noneDiscount-panel" class="hide">
							<p>此号码没有对应相应人员</p>
						</div>
						<div class="clearfix"></div>
						<input type="hidden" name="operatorId" value=<%=staff.getId()%>>
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