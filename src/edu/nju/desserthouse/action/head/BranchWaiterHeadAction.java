package edu.nju.desserthouse.action.head;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import edu.nju.desserthouse.action.BaseAction;

/*
 * 分店服务员网页头action
 */
public class BranchWaiterHeadAction extends BaseAction {
	private static final long serialVersionUID = 1556554054395903212L;

	@Action(
			value = "sale",
			results = { @Result(name = SUCCESS, location = "/page/sale/sale.jsp"),
					@Result(name = INPUT, location = "/page/user/login.jsp") })
	public String sale() {
		return SUCCESS;
	}// 销售

	@Action(
			value = "viewUserInfo",
			results = { @Result(name = SUCCESS, location = "/page/user/viewUserInfo.jsp"),
					@Result(name = INPUT, location = "/page/user/login.jsp") })
	public String viewUserInfo() {
		return SUCCESS;
	}// 查看会员信息

	@Action(
			value = "viewUserConsumption",
			results = { @Result(name = SUCCESS, location = "/page/user/viewUserConsumption.jsp"),
					@Result(name = INPUT, location = "/page/user/login.jsp") })
	public String viewUserConsumption() {
		return SUCCESS;
	}// 查看消费记录

	@Action(
			value = "viewUserRecharge",
			results = { @Result(name = SUCCESS, location = "/page/user/viewUserRecharge.jsp"),
					@Result(name = INPUT, location = "/page/user/login.jsp") })
	public String viewUserRecharge() {
		return SUCCESS;
	}// 查看会员充值记录
}
