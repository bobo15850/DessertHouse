package edu.nju.desserthouse.action.head;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import edu.nju.desserthouse.action.BaseAction;

/*
 * 系统管理员网页头action
 */
public class AdminHeadAction extends BaseAction {
	private static final long serialVersionUID = -1215948076449805654L;

	@Action(
			value = "shop",
			results = { @Result(name = SUCCESS, location = "/page/shop/shop.jsp"),
					@Result(name = INPUT, location = "/page/user/login.jsp") })
	public String shop() {
		return SUCCESS;
	}// 店铺管理

	@Action(
			value = "staff",
			results = { @Result(name = SUCCESS, location = "/page/staff/staff.jsp"),
					@Result(name = INPUT, location = "/page/user/login.jsp") })
	public String staff() {
		return SUCCESS;
	}// 店员管理
}
