package edu.nju.desserthouse.action.user;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.service.UserManageService;

public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private UserManageService userManageService;

	@Action(
			value = "login",
			results = { @Result(name = "success", location = "/page/user/myPage.jsp"),
					@Result(name = "error", location = "/page/error.jsp") })
	public String execute() throws Exception {
		userManageService.test();
		return "error";
	}

	public void validate() {
		super.validate();
	}

}
