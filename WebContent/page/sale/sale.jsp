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
						<button id="goods-list-btn" class="btn btn-info btn-block" onclick="showPanel(this)" value="goods-list-panel">选购商品</button>
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
					<h1>没有任何商品可供购买</h1>
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
								<td><button class="btn btn-primary" value="<%=goods.getId()%>" onclick="chooseGoods(this)">选购</button></td>
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
					<h1>我选购的商品</h1>
					<table class="table table-striped">
						<thead>
							<tr>
								<td>#</td>
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
					<button class="btn btn-primary btn-block">确认购买</button>
				</div>
			</div>
		</div>
	</div>
	<s:include value="../common/footer.jsp"></s:include>
</body>
</html>