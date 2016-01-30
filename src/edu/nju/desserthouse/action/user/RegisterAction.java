package edu.nju.desserthouse.action.user;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.UserService;
import edu.nju.desserthouse.util.ResultMessage;

public class RegisterAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired(required = true)
	private UserService userService;
	private User user;

	@Action(
			value = "register",
			results = { @Result(name = SUCCESS, location = "/page/user/login.jsp", type = "redirect"),
					@Result(name = INPUT, location = "/page/user/register.jsp") })
	public String execute() throws Exception {
		ResultMessage result = userService.registerUser(user);
		if (result == ResultMessage.SUCCESS) {
			return SUCCESS;
		}
		else {
			addFieldError("registerMessage", "注册失败，请重新尝试");
			return INPUT;
		}
	}

	public void validate() {
		String username = user.getUsername();
		if (username != null && username.length() > 0 && username.length() <= 10) {
			if (userService.isUserNameExist(username)) {
				addFieldError("registerMessage", "该用户名已经被注册");
			}
		}
	}// 验证用户名是否已经被注册过了

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
