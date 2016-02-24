<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>计划</title>
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=basePath%>/css/schedule/schedule.css">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/schedule/schedule.js"></script>
</head>
<body>
	<s:include value="../common/header.jsp"></s:include>
	<div class="container">
		<div class="row">
			<div class="col-sm-2">
				<div class="control-panel">
					<button class="btn btn-primary btn-block">制定产品计划</button>
					<button class="btn btn-primary btn-block">正在审核的产品计划</button>
					<button class="btn btn-primary btn-block">未通过的产品计划</button>
					<button class="btn btn-primary btn-block">历史产品计划</button>
				</div>
			</div>
			<div class="col-sm-10">
				<div id="draft-schedule"></div>
			</div>
		</div>
	</div>
	<s:include value="../common/footer.jsp"></s:include>
</body>
</html>