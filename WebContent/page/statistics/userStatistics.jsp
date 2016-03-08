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
<title>会员统计</title>
</head>
<body>
	<s:include value="../common/header.jsp"></s:include>
	<div class="container">
		<div class="row">
			<div class="col-sm-2">
				<h2>会员统计</h2>
				<s:include value="controlPanel.jsp"></s:include>
			</div>
			<div class="col-sm-10">
				<div>
					<form action="<%=basePath%>/statistics/userStatistics.action">
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
				<!-- 使用饼图 -->
				<div id="user-age">
					<script type="text/javascript">
						$(function() {
							$('#user-age')
									.highcharts(
											{
												chart : {
													plotBackgroundColor : null,
													plotBorderWidth : null,
													plotShadow : false
												},
												title : {
													text : '会员年龄分布统计图'
												},
												tooltip : {
													pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
												},
												plotOptions : {
													pie : {
														allowPointSelect : true,
														cursor : 'pointer',
														dataLabels : {
															enabled : true,
															color : '#000000',
															connectorColor : '#000000',
															format : '<b>{point.name}</b>: {point.percentage:.1f} %'
														}
													}
												},
												series : [ {
													type : 'pie',
													name : 'Browser share',
													data : [ [ '0-10', 5 ],
															[ '10-20', 20 ],
															[ '20-30', 30 ],
															[ '30-40', 20 ],
															[ '40-50', 15 ],
															[ '50以上', 10 ], ]
												} ]
											});
						});
					</script>
				</div>
				<br>
				<hr>
				<div id="user-gender">
					<script type="text/javascript">
						$(function() {
							$('#user-gender')
									.highcharts(
											{
												chart : {
													plotBackgroundColor : null,
													plotBorderWidth : null,
													plotShadow : false
												},
												title : {
													text : '会员性别分布统计图'
												},
												tooltip : {
													pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
												},
												plotOptions : {
													pie : {
														allowPointSelect : true,
														cursor : 'pointer',
														dataLabels : {
															enabled : true,
															color : '#000000',
															connectorColor : '#000000',
															format : '<b>{point.name}</b>: {point.percentage:.1f} %'
														}
													}
												},
												series : [ {
													type : 'pie',
													name : 'Browser share',
													data : [ [ '男', 38.0 ],
															[ '女', 53.8 ],
															[ '未设置', 12.8 ] ]
												} ]
											});
						});
					</script>
				</div>
				<br>
				<hr>
				<div id="user-location">
					<script type="text/javascript">
						$(function() {
							$('#user-location')
									.highcharts(
											{
												chart : {
													plotBackgroundColor : null,
													plotBorderWidth : null,
													plotShadow : false
												},
												title : {
													text : '会员地区分布统计图'
												},
												tooltip : {
													pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
												},
												plotOptions : {
													pie : {
														allowPointSelect : true,
														cursor : 'pointer',
														dataLabels : {
															enabled : true,
															color : '#000000',
															connectorColor : '#000000',
															format : '<b>{point.name}</b>: {point.percentage:.1f} %'
														}
													}
												},
												series : [ {
													type : 'pie',
													name : 'Browser share',
													data : [ [ '江苏', 30.0 ],
															[ '北京', 20.0 ],
															[ '上海', 22.0 ],
															[ '浙江', 28.0 ], ]
												} ]
											});
						});
					</script>
				</div>
				<br>
				<hr>
				<div id="user-consumption">
					<script type="text/javascript">
						$(function() {
							$('#user-consumption')
									.highcharts(
											{
												chart : {
													plotBackgroundColor : null,
													plotBorderWidth : null,
													plotShadow : false
												},
												title : {
													text : '会员消费分布统计图'
												},
												tooltip : {
													pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
												},
												plotOptions : {
													pie : {
														allowPointSelect : true,
														cursor : 'pointer',
														dataLabels : {
															enabled : true,
															color : '#000000',
															connectorColor : '#000000',
															format : '<b>{point.name}</b>: {point.percentage:.1f} %'
														}
													}
												},
												series : [ {
													type : 'pie',
													name : 'Browser share',
													data : [ [ '预定消费', 22.6 ],
															[ '实体店消费', 58.8 ],
															[ '无卡消费', 18.6 ], ]
												} ]
											});
						});
					</script>
				</div>
				<br>
				<hr>
				<div id="user-card">
					<script type="text/javascript">
						$(function() {
							$('#user-consumption')
									.highcharts(
											{
												chart : {
													plotBackgroundColor : null,
													plotBorderWidth : null,
													plotShadow : false
												},
												title : {
													text : '会员卡状态情况分布统计图'
												},
												tooltip : {
													pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
												},
												plotOptions : {
													pie : {
														allowPointSelect : true,
														cursor : 'pointer',
														dataLabels : {
															enabled : true,
															color : '#000000',
															connectorColor : '#000000',
															format : '<b>{point.name}</b>: {point.percentage:.1f} %'
														}
													}
												},
												series : [ {
													type : 'pie',
													name : 'Browser share',
													data : [ [ '正常', 85.3 ],
															[ '暂停', 5.8 ],
															[ '停止', 8.9 ], ]
												} ]
											});
						});
					</script>
				</div>
				<%
					}
				%>
			</div>
		</div>
	</div>
	<s:include value="../common/footer.jsp"></s:include>
</body>