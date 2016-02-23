package edu.nju.desserthouse.action.staff;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.StaffService;
import edu.nju.desserthouse.service.UserService;

public class ModifyStaffAction extends BaseAction {
	private static final long serialVersionUID = 4334772427535605578L;
	@Autowired
	private StaffService staffService;
	@Autowired
	private UserService userService;
	private int staffId;
	private String staffname;
	private String phonenumber;
	private int category;
	private int shopId;

	@Override
	@Action(
			value = "modifyStaff",
			results = { @Result(
					name = SUCCESS,
					location = "/staff/toModifyStaff.action?staffId=${staffId}",
					type = "redirect") })
	public String execute() throws Exception {
		User staff = userService.getUserById(staffId);
		staff.setUsername(staffname);
		staff.setPhonenumber(phonenumber);
		staff.setCategory(category);
		staffService.modifyStaff(staff, shopId);
		return SUCCESS;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public String getStaffname() {
		return staffname;
	}

	public void setStaffname(String staffname) {
		this.staffname = staffname;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

}
