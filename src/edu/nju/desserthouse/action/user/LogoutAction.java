package edu.nju.desserthouse.action.user;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import edu.nju.desserthouse.action.BaseAction;

public class LogoutAction extends BaseAction {

	private static final long serialVersionUID = 3815047021635419354L;

	@Action(
			value = "logout",
			results = { @Result(name = SUCCESS, location = "/page/user/login.jsp", type = "redirect") })
	public String execute() throws Exception {
		session.remove("userBase");
		return SUCCESS;
	}
}
