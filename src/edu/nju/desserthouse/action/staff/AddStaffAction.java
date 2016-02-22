package edu.nju.desserthouse.action.staff;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.StaffService;

public class AddStaffAction extends BaseAction {
	private static final long serialVersionUID = -2061461006883022157L;
	private User staff;
	private int shopId;

	@Autowired
	private StaffService staffService;

	@Override
	@Action(value = "addStaff", results = { @Result(name = SUCCESS, location = "/staff.action", type = "redirect") })
	public String execute() throws Exception {
		staffService.addStaff(staff, shopId);
		return SUCCESS;
	}

	public User getStaff() {
		return staff;
	}

	public void setStaff(User staff) {
		this.staff = staff;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
}
