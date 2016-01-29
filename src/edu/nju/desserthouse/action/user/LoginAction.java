package edu.nju.desserthouse.action.user;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.UserService;
import edu.nju.desserthouse.util.UserBase;

public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private UserService userService;
	private String key;
	private String password;

	@Action(
			value = "login",
			results = { @Result(name = "success", location = "/page/user/myPage.jsp"),
					@Result(name = "input", location = "/page/user/login.jsp") })
	public String execute() throws Exception {
		User user = null;
		user = userService.login(key, password);
		if (user == null) {
			super.addFieldError("loginMessage", "用户名/手机号或密码错误");
			return INPUT;
		}
		UserBase userBase = new UserBase(user);
		super.session.put("userBase", userBase);
		return SUCCESS;
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
