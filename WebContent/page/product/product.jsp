<%@page import="edu.nju.desserthouse.model.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String basePath = request.getContextPath();
	List<Product> products = (List<Product>) request.getAttribute("products");
	String errorMessage = (String) request.getAttribute("errorMessage");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品</title>
<link rel="stylesheet" href="<%=basePath%>/lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=basePath%>/css/product/product.css">
<script src="<%=basePath%>/lib/jquery/jquery-1.12.1.min.js"></script>
<script src="<%=basePath%>/lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/product/product.js"></script>
</head>
<body>
	<s:include value="../common/header.jsp"></s:include>
	<div id="main-body" class="container">
		<div class="row">
			<div class="col-sm-2 left-bar">
				<div id="add-product">
					<button class="btn btn-primary btn-block" type="button" data-toggle="modal" data-target="#productInfo">添加产品</button>
					<div id="productInfo" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
						<div class="modal-dialog" role="document">
							<form action="<%=basePath%>/product/addProduct.action" enctype="multipart/form-data" method="post" onsubmit="checkAddProduct()">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h4 class="modal-title" id="myModalLabel">添加产品</h4>
									</div>
									<div class="modal-body">
										<div class="input-group">
											<span class="input-group-addon">产品名称</span>
											<input name="product.name" type="text" class="form-control" placeholder="请输入产品名称">
										</div>
										<div class="input-group">
											<span class="input-group-addon">附加信息</span>
											<input name="product.info" type="text" class="form-control" placeholder="请输入产品附加信息">
										</div>
										<div class="input-group">
											<span class="input-group-addon">默认单价</span>
											<input name="product.price" type="number" class="form-control" placeholder="请输入产品默认单价">
										</div>
										<div class="input-group">
											<span class="input-group-addon">默认数量</span>
											<input name="product.number" type="number" class="form-control" placeholder="请输入默认可售数量">
										</div>
										<div class="input-group">
											<span class="input-group-addon">产品图片</span>
											<input name="picture" type="file">
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
				<div id="search-product">
					<form action="<%=basePath%>/product/selectProduct.action">
						<div class="input-group">
							<input type="text" class="form-control" name="selectInfo" placeholder="搜索产品...">
							<span class="input-group-btn">
								<button class="btn btn-primary" type="submit">查找产品</button>
							</span>
						</div>
					</form>
				</div>
				<div id="show-all">
					<form action="<%=basePath%>/product.action" method="post">
						<button class="btn btn-primary btn-block" type="submit">全部产品</button>
					</form>
				</div>
			</div>
			<div class="col-sm-10">
				<%
					if (errorMessage != null) {
						out.print("<h3>" + errorMessage + "</h3>");
					}
					else {

						if (products != null && products.size() != 0) {
				%>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>图片</th>
							<th>产品名称</th>
							<th>附加信息</th>
							<th>默认单价</th>
							<th>默认数量</th>
							<th>添加日期</th>
						</tr>
					</thead>
					<tbody>
						<%
							for (int i = 0; i < products.size(); i++) {
										Product product = products.get(i);
						%>
						<tr>
							<td><img class="product-img" alt="暂无图片" src="<%=basePath + "/" + product.getPicture()%>"></td>
							<td><%=product.getName()%></td>
							<td><%=product.getInfo()%></td>
							<td><%=product.getPrice()%></td>
							<td><%=product.getNumber()%></td>
							<td><%=product.getCreatedTime()%></td>
						</tr>
						<%
							}
						%>

					</tbody>
				</table>
				<%
					}
						else {
							out.print("<h1>没有找到任何产品</h1>");
						}
					}
				%>
			</div>
		</div>
	</div>
	<s:include value="../common/footer.jsp"></s:include>
</body>
</html>