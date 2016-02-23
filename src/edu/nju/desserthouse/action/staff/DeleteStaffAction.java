package edu.nju.desserthouse.action.staff;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.service.StaffService;

public class DeleteStaffAction extends BaseAction {
	private static final long serialVersionUID = 8989534170296170645L;
	@Autowired
	private StaffService staffService;
	private int staffId;

	@Override
	@Action(value = "deleteStaff", results = { @Result(name = SUCCESS, location = "/staff.action", type = "redirect") })
	public String execute() throws Exception {
		staffService.deleteStaff(staffId);
		return SUCCESS;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

}
