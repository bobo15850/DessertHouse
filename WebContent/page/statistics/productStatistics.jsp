<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String basePath = request.getContextPath();
	int month = (int) request.getAttribute("month");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=basePath%>/lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=basePath%>/css/main.css">
<link rel="stylesheet" href="<%=basePath%>/css/product/product.css">
<script src="<%=basePath%>/lib/jquery/jquery-1.12.1.min.js"></script>
<script src="<%=basePath%>/lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/lib/highcharts.js"></script>
<title>产品统计</title>
</head>
<body>
	<s:include value="../common/header.jsp"></s:include>
	<div class="container">
		<div class="row">
			<div class="col-sm-2">
				<h2>产品统计</h2>
				<s:include value="controlPanel.jsp"></s:include>
			</div>
			<div class="col-sm-10">
				<div>
					<form action="<%=basePath%>/statistics/productStatistics.action">
						<div class="input-group">
							<span class="input-group-addon">年份</span>
							<select name="year" class="form-control">
								<option value="2016" selected="selected">2016</option>
							</select>
							<span class="input-group-addon">月份</span>
							<select name="month" class="form-control">
								<%
									for (int i = 1; i <= 12; i++) {
								%>
								<option value="<%=i%>" <%=(i == month ? "selected='selected'" : "")%>><%=i%></option>
								<%
									}
								%>
							</select>
							<span class="input-group-btn">
								<button class="btn btn-primary" type="submit">切换月份</button>
							</span>
						</div>
					</form>
				</div>
				<%
					if (month != 3) {
				%>
				<h1>暂无数据</h1>
				<%
					} else {
				%>
				<div id="hot-product">
					<script type="text/javascript">
						$(function() {
							$('#hot-product')
									.highcharts(
											{
												chart : {
													type : 'column',
													margin : [ 50, 50, 100, 80 ]
												},
												title : {
													text : '产品销量统计'
												},
												xAxis : {
													categories : [ '巧克力生日蛋糕',
															'小黄鸭蛋糕', '小狗蛋糕',
															'草莓蛋糕', '五彩蛋糕',
															'水果蛋糕', '泡芙蛋糕',
															'抹茶蛋糕' ],
													labels : {
														rotation : -45,
														align : 'right',
														style : {
															fontSize : '13px',
															fontFamily : 'Verdana, sans-serif'
														}
													}
												},
												yAxis : {
													min : 0,
													title : {
														text : '销量 (个)'
													}
												},
												legend : {
													enabled : false
												},
												tooltip : {
													pointFormat : '产品销量: <b>{point.y:.1f} 个<b>',
												},
												series : [ {
													name : 'Population',
													data : [ 934, 1578, 1324,
															1987, 1497, 1786,
															2324, 2134 ],
													dataLabels : {
														enabled : true,
														rotation : -90,
														color : '#FFFFFF',
														align : 'right',
														x : 4,
														y : 10,
														style : {
															fontSize : '13px',
															fontFamily : 'Verdana, sans-serif',
															textShadow : '0 0 3px black'
														}
													}
												} ]
											});
						});
					</script>
				</div>
				<%
					}
				%>
				<div>
					<h1>当月热销</h1>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>排名</th>
								<th>图片</th>
								<th>销量</th>
								<th>默认售价</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1</td>
								<td><img class="product-img" alt="" src="<%=basePath%>/image/product/泡芙蛋糕.jpg"></td>
								<td>2324</td>
								<td>40</td>
							</tr>
							<tr>
								<td>2</td>
								<td><img class="product-img" alt="" src="<%=basePath%>/image/product/抹茶蛋糕.jpg"></td>
								<td>2134</td>
								<td>15</td>
							</tr>
							<tr>
								<td>3</td>
								<td><img class="product-img" alt="" src="<%=basePath%>/image/product/草莓蛋糕.jpg"></td>
								<td>1987</td>
								<td>130</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

		</div>
	</div>
	<s:include value="../common/footer.jsp"></s:include>
</body>