<%@page import="edu.nju.desserthouse.model.Region"%>
<%@page import="java.util.List"%>
<%@page import="edu.nju.desserthouse.util.FinalValue"%>
<%@page import="edu.nju.desserthouse.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	/*
	该页面只有login.action可以到达，需要传递user,region,等参数
	*/
	String basePath = request.getContextPath();
	User user = (User) request.getAttribute("user");
	List<Region> provinces = (List<Region>) request.getAttribute("provinces");
	List<Region> citys = (List<Region>) request.getAttribute("citys");
	List<Region> countys = (List<Region>) request.getAttribute("countys");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=basePath%>/css/user/account.css">
<link rel="stylesheet" href="<%=basePath%>/lib/bootstrap-datetimepicker.min.css">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/lib/moment.js"></script>
<script type="text/javascript" src="<%=basePath%>/lib/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/user/account.js"></script>
<title>账户信息</title>
</head>
<body>
	<s:include value="../common/header.jsp"></s:include>
	<div class="container">
		<div class="row">
			<div class="col-sm-3 unset-list">
				<label>不可变用户信息</label>
				<div class="unset-item">
					<label>用户编号</label>
					<input type="text" class="form-control" value="<%=user.getId()%>" readonly="readonly" />
				</div>
				<div class="unset-item">
					<label>会员卡号</label>
					<input type="text" class="form-control" value="<%=user.getCardId()%>" readonly="readonly" />
				</div>
				<div class="unset-item">
					<label>会员等级 </label>
					<input type="text" class="form-control" value="<%=FinalValue.UserLevel.getStrOfUserLevel(user.getLevel())%>" readonly="readonly" />
				</div>
				<div class="unset-item">
					<label>会员资格</label>
					<input type="text" class="form-control" value="<%=FinalValue.UserState.getStrOfUserState(user.getState())%>" readonly="readonly" />
				</div>
				<div class="unset-item">
					<label>消费总额</label>
					<input type="text" class="form-control" value="<%=user.getConsumption()%>" readonly="readonly" />
				</div>
				<div class="unset-item">
					<label>注册日期</label>
					<input type="text" class="form-control" value="<%=user.getCreatedTime()%>" readonly="readonly" />
				</div>
			</div>
			<div class="col-sm-9 reset-list">
				<label id="message">设置用户信息</label>
				<div class="input-group">
					<span class="input-group-addon">用户名称</span>
					<input id="usernameInput" type="text" class="form-control" value="<%=user.getUsername()%>">
					<span class="input-group-btn">
						<button class="btn btn-primary" type="button" onclick="changeUsername()">保存修改</button>
					</span>
				</div>
				<div class="input-group">
					<span class="input-group-addon">绑定电话</span>
					<input id="phonenumberInput" type="text" class="form-control" value="<%=user.getPhonenumber()%>">
					<span class="input-group-btn">
						<button class="btn btn-primary" type="button" onclick="changePhonenumber()">保存修改</button>
					</span>
				</div>
				<div class="input-group">
					<span class="input-group-addon">银行卡号</span>
					<input id="bankIdInput" type="text" class="form-control" value="<%=user.getBankId()%>">
					<span class="input-group-btn">
						<button class="btn btn-primary" type="button" onclick="changeBankId()">保存修改</button>
					</span>
				</div>
				<div class="input-group">
					<span class="input-group-addon">用户性别</span>
					<select id="genderSelect" class="form-control">
						<option value="1" <%=user.getGender() == 1 ? "selected='selected'" : ""%>>男</option>
						<option value="2" <%=user.getGender() == 2 ? "selected='selected'" : ""%>>女</option>
						<option value="0" <%=user.getGender() == 0 ? "selected='selected'" : ""%>>未设置</option>
					</select>
					<span class="input-group-btn">
						<button class="btn btn-primary" type="button" onclick="changeGender()">保存修改</button>
					</span>
				</div>
				<div class="input-group">
					<span class="input-group-addon">出生日期</span>
					<input type="text" id='birthdayInput' class="form-control"
						placeholder="<%=(user.getBirthday() == null ? "未设置" : user.getBirthday().toString())%>">
					<span class="input-group-btn">
						<button class="btn btn-primary" type="button" onclick="changeBirthday()">保存修改</button>
					</span>
				</div>
				<div class="input-group">
					<span class="input-group-addon">用户地区</span>
					<select id="provinceSelect" class="form-control" onchange="changeProvince()">
						<%
							if (request.getAttribute("province").equals("未设置")) {
								out.print("<option value='notset'>未设置</option>");
								for (int i = 0; i < provinces.size(); i++) {
									String optionStr = "";
									optionStr = "<option value='" + provinces.get(i).getId() + "'>" + provinces.get(i).getName()
											+ "</option>";
									out.print(optionStr);
								}
							} //没有设置
							else {
								for (int i = 0; i < provinces.size(); i++) {
									String optionStr = "";
									if (request.getAttribute("province").equals(provinces.get(i).getName())) {
										optionStr = "<option selected='selected' value='" + provinces.get(i).getId() + "'>"
												+ provinces.get(i).getName() + "</option>";
									}
									else {
										optionStr = "<option value='" + provinces.get(i).getId() + "'>" + provinces.get(i).getName()
												+ "</option>";
									}
									out.print(optionStr);
								}
							} //已设置
						%>
					</select>
					<span class="input-group-addon">省/直辖市</span>
					<select id="citySelect" class="form-control" onchange="changeCity()">
						<%
							if (request.getAttribute("city").equals("未设置")) {
								out.print("<option  value='notset'>未设置</option>");
							}
							else {
								for (int i = 0; i < citys.size(); i++) {
									String optionStr = "";
									if (request.getAttribute("city").equals(citys.get(i).getName())) {
										optionStr = "<option selected='selected' value='" + citys.get(i).getId() + "'>"
												+ citys.get(i).getName() + "</option>";
									}
									else {
										optionStr = "<option value='" + citys.get(i).getId() + "'>" + citys.get(i).getName()
												+ "</option>";
									}
									out.print(optionStr);
								}
							}
						%>
					</select>
					<span class="input-group-addon">地级市</span>
					<select id="countySelect" class="form-control">
						<%
							if (request.getAttribute("county").equals("未设置")) {
								out.print("<option  value='notset'>未设置</option>");
							}
							else {
								for (int i = 0; i < countys.size(); i++) {
									String optionStr = "";
									if (request.getAttribute("county").equals(countys.get(i).getName())) {
										optionStr = "<option selected='selected' value='" + countys.get(i).getId() + "'>"
												+ countys.get(i).getName() + "</option>";
									}
									else {
										optionStr = "<option value='" + countys.get(i).getId() + "'>" + countys.get(i).getName()
												+ "</option>";
									}
									out.print(optionStr);
								}
							}
						%>
					</select>
					<span class="input-group-addon">县/区</span> <span class="input-group-btn">
						<button class="btn btn-primary" type="button" onclick="changeRegion()">保存修改</button>
					</span>
				</div>
				<div class="input-group">
					<span class="input-group-addon">具体地址</span>
					<input id="locationInput" type="text" class="form-control" value="<%=(user.getLocation() == null ? "未设置" : user.getLocation())%>">
					<span class="input-group-btn">
						<button class="btn btn-primary" type="button" onclick="changeLocation()">保存修改</button>
					</span>
				</div>
				<div class="input-group">
					<span class="input-group-addon">账户余额</span>
					<input type="text" class="form-control" value="<%=user.getBalance()%>" readonly="readonly">
					<span class="input-group-btn">
						<button class="btn btn-primary" type="button">账户充值</button>
					</span>
				</div>
				<div class="input-group">
					<span class="input-group-addon">账户积分</span>
					<input type="text" class="form-control" value="<%=user.getPoint()%>" readonly="readonly">
					<span class="input-group-btn">
						<button class="btn btn-primary" type="button">兑换积分</button>
					</span>
				</div>
				<div class="input-group">
					<span class="input-group-addon">原密码</span>
					<input id="oldPasswordInput" type="password" class="form-control" placeholder="输入原密码">
					<span class="input-group-addon">新密码</span>
					<input id="newPasswordInput" type="password" class="form-control" placeholder="输入新密码">
					<span class="input-group-addon">确认密码</span>
					<input id="passwordConfirmInput" type="password" class="form-control" placeholder="再次输入新密码">
					<span class="input-group-btn">
						<button class="btn btn-primary" type="button" onclick="changePassword()">修改密码</button>
					</span>
				</div>

			</div>
		</div>
	</div>
	<s:include value="../common/footer.jsp"></s:include>
</body>
</html>