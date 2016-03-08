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
<script src="<%=basePath%>/lib/jquery/jquery-1.12.1.min.js"></script>
<script src="<%=basePath%>/lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/lib/highcharts.js"></script>
<title>分店统计</title>
</head>
<body>
	<s:include value="../common/header.jsp"></s:include>
	<div class="container">
		<div class="row">
			<div class="col-sm-2">
				<h2>分店统计</h2>
				<s:include value="controlPanel.jsp"></s:include>
			</div>
			<div class="col-sm-10">
				<div>
					<form action="<%=basePath%>/statistics/shopStatistics.action">
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
				<div id="shop-sale-book">
					<script type="text/javascript">
						$(function() {
							$('#shop-sale-book')
									.highcharts(
											{
												chart : {
													type : 'column'
												},
												title : {
													text : '分店销售预订情况'
												},
												subtitle : {
													text : '数据来自统计'
												},
												xAxis : {
													categories : [ '北京大学店',
															'清华大学店', '上海交通大学店',
															'复旦大学店', '南京大学店',
															'东南大学店', '浙江大学店' ]
												},
												yAxis : {
													min : 0,
													title : {
														text : '总额(元)'
													}
												},
												tooltip : {
													headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
													pointFormat : '<tr><td style="color:{series.color};padding:0">{series.name}: </td>'
															+ '<td style="padding:0"><b>{point.y:.1f} 元</b></td></tr>',
													footerFormat : '</table>',
													shared : true,
													useHTML : true
												},
												plotOptions : {
													column : {
														pointPadding : 0.2,
														borderWidth : 0
													}
												},
												series : [
														{
															name : '实体店销售额',
															data : [ 3534,
																	3268, 2487,
																	5940, 3456,
																	4854, 4156 ]

														},
														{
															name : '预定总额',
															data : [ 2345,
																	2567, 2134,
																	3245, 2643,
																	2712, 2435 ]

														},
														{
															name : '无卡消费额',
															data : [ 4563,
																	4678, 4123,
																	5345, 4532,
																	4123, 3521 ]
														} ]
											});
						});
					</script>
				</div>
				<%
					}
				%>
				<div></div>
			</div>
		</div>
	</div>
	<s:include value="../common/footer.jsp"></s:include>
</body>