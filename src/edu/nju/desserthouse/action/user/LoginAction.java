package edu.nju.desserthouse.action.user;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.UserService;
import edu.nju.desserthouse.util.FinalValue;
import edu.nju.desserthouse.util.UserBase;

public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private UserService userService;
	private String key;
	private String password;

	@Action(
			value = "login",
			results = { @Result(name = INPUT, location = "/page/user/login.jsp"),
					@Result(name = "common_member", location = "/page/user/account.jsp", type = "redirect"),
					@Result(name = "branch_waiter", location = "/page/sale/sale.jsp", type = "redirect"),
					@Result(name = "head_waiter", location = "/page/schedule/schedule.jsp", type = "redirect"),
					@Result(name = "manager", location = "/page/schedule/approval.jsp", type = "redirect"),
					@Result(name = "administrator", location = "/page/shop/shop.jsp", type = "redirect") })
	public String execute() throws Exception {
		User user = null;
		user = userService.login(key, password);
		if (user == null) {
			super.addFieldError("loginMessage", "用户名/手机号或密码错误");
			return INPUT;
		}
		UserBase userBase = new UserBase(user);
		super.session.put("userBase", userBase);
		switch (userBase.getCategory()) {
		case FinalValue.UserCategory.COMMON_MENBER:
			return "common_member";
		case FinalValue.UserCategory.BRANCH_WAITER:
			return "branch_waiter";
		case FinalValue.UserCategory.HEAD_WAITER:
			return "head_waiter";
		case FinalValue.UserCategory.MANAGER:
			return "manager";
		case FinalValue.UserCategory.ADMINISTRATOR:
			return "administrator";
		}
		return INPUT;
	}// 登录

	@Override
	public void validate() {
		if (key == null || key.length() > 11 || key.trim().length() == 0) {
			super.addFieldError("loginMessage", "用户名由字母数字和下划线组成且不超过10字符");
		}
	}// 验证登录键是否合法

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
