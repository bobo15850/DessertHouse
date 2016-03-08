<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
%>
<div class="control-panel">
	<br> <br> <br>
	<div>
		<form action="<%=basePath%>/statistics/userStatistics.action">
			<button class="btn btn-primary btn-block">会员统计</button>
		</form>
	</div>
	<div>
		<form action="<%=basePath%>/statistics/shopStatistics.action">
			<button class="btn btn-primary btn-block">分店统计</button>
		</form>
	</div>
	<div>
		<form action="<%=basePath%>/statistics/productStatistics.action">
			<button class="btn btn-primary btn-block">产品统计</button>
		</form>
	</div>
</div>