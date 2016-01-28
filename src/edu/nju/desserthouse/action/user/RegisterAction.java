package edu.nju.desserthouse.action.user;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.UserService;

public class RegisterAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired(required = true)
	private UserService userService;
	private User user;

	@Action(
			value = "register",
			results = { @Result(name = "success", location = "/page/user/login.jsp"),
					@Result(name = "error", location = "/page/error.jsp") })
	public String execute() throws Exception {
		userService.registerUser(user);
		return SUCCESS;
	}

	public void validate() {
		super.validate();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
