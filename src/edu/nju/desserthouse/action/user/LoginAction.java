package edu.nju.desserthouse.action.user;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.UserService;
import util.UserBase;

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
		// 验证登陆者
		User user = null;
		if (key.length() <= 10 && key.length() > 0 && key.charAt(0) != '@') {
			user = userService.findUserByName(key, password);
			if (user == null) {
				request.setAttribute("errorMessage", "用户名或密码错误");
				return INPUT;
			}
		} // 用户名(@排除会员卡号)
		else if (key.length() == 11) {
			user = userService.findUserByPhoneNumer(key, password);
			if (user == null) {
				request.setAttribute("errorMessage", "手机号或密码错误");
				return INPUT;
			}
		} // 手机号
		else {
			request.setAttribute("errorMessage", "用户名由字母数字和下划线组成且不超过10字符");
			return INPUT;
		} // 用户名格式错误
		UserBase userBase = new UserBase(user);
		super.session.put("userBase", userBase);
		return SUCCESS;
	}

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
