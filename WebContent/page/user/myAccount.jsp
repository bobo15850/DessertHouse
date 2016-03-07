<%@page import="edu.nju.desserthouse.model.Region"%>
<%@page import="java.util.List"%>
<%@page import="edu.nju.desserthouse.util.FinalValue"%>
<%@page import="edu.nju.desserthouse.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
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
<link rel="stylesheet" href="<%=basePath%>/lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=basePath%>/css/user/account.css">
<link rel="stylesheet" href="<%=basePath%>/lib/bootstrap-datetimepicker.min.css">
<script src="<%=basePath%>/lib/jquery/jquery-1.12.1.min.js"></script>
<script src="<%=basePath%>/lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/lib/moment.js"></script>
<script type="text/javascript" src="<%=basePath%>/lib/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/user/account.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/region/region.js"></script>
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
					<input id="userLevelInput" type="text" class="form-control" value="<%=FinalValue.UserLevel.getStrOfUserLevel(user.getLevel())%>"
						readonly="readonly" />
				</div>
				<div class="unset-item">
					<label>会员资格</label>
					<input id="userStateInput" type="text" class="form-control" value="<%=FinalValue.UserState.getStrOfUserState(user.getState())%>"
						readonly="readonly" />
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
					<span class="input-group-addon">所在省/直辖市</span>
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
					<span class="input-group-addon">地级市</span>
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
					<span class="input-group-addon">县/区</span>
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
					<span class="input-group-btn">
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
					<input id="balanceInput" type="text" class="form-control" value="<%=user.getBalance()%>" readonly="readonly">
					<%
						if (user.getState() == FinalValue.UserState.INACTIVE) {
					%>
					<span class="input-group-btn">
						<button id="inactiveAccountBtn" class="btn btn-primary" type="button" data-toggle="modal" data-target="#inactiveAccountModel">激活账户</button>
					</span>

					<%
						}
						else if (user.getState() == FinalValue.UserState.STOP) {
					%>
					<span class="input-group-btn">
						<button class="btn btn-primary" type="button" disabled="disabled">账户停止</button>
					</span>
					<%
						}
						else if (user.getState() == FinalValue.UserState.SUSPEND) {
					%>
					<span class="input-group-btn">
						<button id="renewalAccountBtn" class="btn btn-primary" type="button" data-toggle="modal" data-target="#renewalAccountModel">账户续费</button>
					</span>
					<%
						}
						else {
					%>
					<span class="input-group-btn">
						<button id="rechargeAccountBtn" class="btn btn-primary" type="button" data-toggle="modal" data-target="#rechargeAccountModel">账户充值</button>
					</span>
					<%
						}
					%>
					<div class="modal fade" id="inactiveAccountModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">激活账户</h4>
								</div>
								<div class="modal-body">激活账户需要充值200元，且从绑定的银行卡扣款，如果未绑定银行卡请先绑定银行卡，是否现在激活？</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
									<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="inactiveAccount()">确定</button>
								</div>
							</div>
						</div>
					</div>
					<div class="modal fade" id="renewalAccountModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">账户续费</h4>
								</div>
								<div class="modal-body">会员记录已暂停（一年内若未充值则停止账户）需要充值200元恢复会员资格，且从绑定银行卡扣款，如果未绑定银行卡请先绑定银行卡，是否现在恢复？</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
									<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="renewalAccount()">确定</button>
								</div>
							</div>
						</div>
					</div>
					<div class="modal fade" id="rechargeAccountModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">账户充值</h4>
								</div>
								<div class="modal-body">
									<input id="amountInput" type="text" class="form-control" placeholder="请输入充值金额">
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
									<button type="button" class="btn btn-primary" onclick="rechargeAccount()">确定</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="input-group">
					<span class="input-group-addon">账户积分</span>
					<input type="text" class="form-control" value="<%=user.getPoint()%>" readonly="readonly">
					<span class="input-group-btn">
						<button class="btn btn-primary" type="button" data-toggle="modal" data-target="#exchangePoint">兑换积分</button>
					</span>
				</div>
				<div class="modal fade" id="exchangePoint" tabindex="-1" role="dialog" aria-labelledby="pointTitle">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<form action="<%=basePath%>/user/exchangePoint.action">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="pointTitle">积分兑换</h4>
								</div>
								<div class="modal-body">
									<input type="number" class="form-control" name="point" value=<%=user.getPoint()%>>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
									<button type="submit" class="btn btn-primary">确定</button>
								</div>
							</form>
						</div>
					</div>
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

				<div>
					<%
						if (user.getState() != FinalValue.UserState.STOP) {
					%>
					<button id="cancleMemberBtn" class="btn btn-danger btn-block" type="button" data-toggle="modal" data-target="#cancleMembershipModel">取消会员资格</button>
					<%
						}
					%>
					<div class="modal fade" id="cancleMembershipModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">取消会员资格</h4>
								</div>
								<div class="modal-body">
									<label>取消会员资格即为将当前会员状态变为停止</label>
									<input id="cancleConfirmInput" type="password" class="form-control" placeholder="请输入密码">
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
									<button type="button" class="btn btn-primary" onclick="cancleMembership()">确定</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<s:include value="../common/footer.jsp"></s:include>
</body>
</html>