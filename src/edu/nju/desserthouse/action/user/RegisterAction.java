package edu.nju.desserthouse.action.user;

import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.UserManageService;

public class RegisterAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired(required = true)
	private UserManageService userService;
	private User user;

	@Override
	public String execute() throws Exception {
		user.setId("abcdefg");
		userService.registerUser(user);
		return SUCCESS;
	}

	@Override
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
