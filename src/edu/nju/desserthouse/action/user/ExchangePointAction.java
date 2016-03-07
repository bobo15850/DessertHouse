package edu.nju.desserthouse.action.user;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.service.UserService;
import edu.nju.desserthouse.util.UserBase;

public class ExchangePointAction extends BaseAction {
	private static final long serialVersionUID = -2745088918457208128L;
	private int point;
	@Autowired
	private UserService userService;

	@Override
	@Action(
			value = "exchangePoint",
			results = { @Result(name = SUCCESS, location = "/myAccount.action", type = "redirect"),
					@Result(name = INPUT, location = "/page/common/error.jsp") })
	public String execute() throws Exception {
		UserBase userBase = (UserBase) session.get("userBase");
		userService.exchangePoint(userBase.getId(), point);
		return SUCCESS;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

}
