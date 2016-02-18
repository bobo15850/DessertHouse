package edu.nju.desserthouse.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import edu.nju.desserthouse.action.BaseAction;

/*
 * 总经理网页头不action
 */
public class ManagerHeadAction extends BaseAction {
	private static final long serialVersionUID = 6394820601304388343L;

	@Action(
			value = "approval",
			results = { @Result(name = SUCCESS, location = "/page/schedule/approval.jsp"),
					@Result(name = INPUT, location = "/page/user/login.jsp") })
	public String approval() {
		return SUCCESS;
	}// 产品计划审批

	@Action(
			value = "statistics",
			results = { @Result(name = SUCCESS, location = "/page/statistics/statistics.jsp"),
					@Result(name = INPUT, location = "/page/user/login.jsp") })
	public String statistics() {
		return SUCCESS;
	}// 产品计划审批
}
