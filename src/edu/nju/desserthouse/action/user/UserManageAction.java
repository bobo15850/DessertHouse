package edu.nju.desserthouse.action.user;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.Region;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.RegionService;
import edu.nju.desserthouse.service.UserService;
import edu.nju.desserthouse.util.FinalValue;
import edu.nju.desserthouse.util.ResultMessage;
import edu.nju.desserthouse.util.UserBase;

@ParentPackage("json-default")
public class UserManageAction extends BaseAction {
	private static final long serialVersionUID = 4849557797102900761L;
	private Map<String, String> map = new HashMap<String, String>();
	@Autowired
	private UserService userService;
	@Autowired
	private RegionService regionService;

	@Action(value = "uniqueStringField", results = { @Result(name = SUCCESS, type = "json") })
	public String SetUniqueField() {
		UserBase userBase = (UserBase) session.get("userBase");
		ResultMessage result = userService.setUniqueStringField(userBase.getId(), map.get("field"), map.get("value"));
		if (result == ResultMessage.SUCCESS) {
			if (map.get("field").equals("username")) {
				User user = userService.getUserById(userBase.getId());
				userBase = new UserBase(user);
				session.replace("userBase", userBase);
			} // 只有改变用户名的时候才需要修改session
			map.put("result", SUCCESS);
		}
		else {
			map.put("result", ERROR);
		}
		map.remove("field");
		map.remove("value");
		return SUCCESS;
	}

	@Action(value = "repeatStringField", results = { @Result(name = SUCCESS, type = "json") })
	public String setRepeatStringField() {
		UserBase userBase = (UserBase) session.get("userBase");
		ResultMessage result = userService.setRepeatStringField(userBase.getId(), map.get("field"), map.get("value"));
		if (result == ResultMessage.SUCCESS) {
			map.put("result", SUCCESS);
		}
		else {
			map.put("result", ERROR);
		}
		map.remove("field");
		map.remove("value");
		return SUCCESS;
	}

	@Action(value = "repeatIntField", results = { @Result(name = SUCCESS, type = "json") })
	public String setRepeatIntField() {
		UserBase userBase = (UserBase) session.get("userBase");
		ResultMessage result = userService.setRepeatIntField(userBase.getId(), map.get("field"),
				(int) Integer.parseInt(map.get("value")));
		if (result == ResultMessage.SUCCESS) {
			map.put("result", SUCCESS);
		}
		else {
			map.put("result", ERROR);
		}
		map.remove("field");
		map.remove("value");
		return SUCCESS;
	}

	@Action(value = "checkOldPassword", results = { @Result(name = SUCCESS, type = "json") })
	public String checkOldPassword() {
		UserBase userBase = (UserBase) session.get("userBase");
		User result = userService.login(userBase.getUsername(), map.get("oldPassword"));
		if (result == null) {
			map.put("result", "error");
		}
		else {
			map.put("result", "success");
		}
		map.remove("oldPassword");
		return SUCCESS;
	}// 判断旧密码是否正确

	@Action(value = "setBirthday", results = { @Result(name = SUCCESS, type = "json") })
	public String setBirthday() {
		UserBase userBase = (UserBase) session.get("userBase");
		System.out.println(map.get("birthday"));
		ResultMessage result = userService.setRepeatDate(userBase.getId(), "birthday",
				Date.valueOf(map.get("birthday")));
		if (result == ResultMessage.SUCCESS) {
			map.put("result", SUCCESS);
		}
		else {
			map.put("result", ERROR);
		}
		map.remove("birthday");
		return SUCCESS;
	}

	@Action(value = "setRegion", results = { @Result(name = SUCCESS, type = "json") })
	public String setRegion() {
		int id = Integer.parseInt(map.get("id"));
		UserBase userBase = (UserBase) session.get("userBase");
		ResultMessage result = userService.setUserRegion(userBase.getId(), id);
		if (result == ResultMessage.SUCCESS) {
			map.put("result", SUCCESS);
		}
		else {
			map.put("result", ERROR);
		}
		map.remove("id");
		return SUCCESS;
	}

	@Action(value = "lowerRegions", results = { @Result(name = SUCCESS, type = "json") })
	public String lowerRegions() {
		int id = Integer.parseInt(map.get("id"));
		List<Region> regions = regionService.getLowerRegions(id);
		StringBuilder idBuilder = new StringBuilder();
		StringBuilder nameBuilder = new StringBuilder();
		if (regions != null && regions.size() != 0) {
			for (int i = 0; i < regions.size(); i++) {
				idBuilder.append(regions.get(i).getId() + "-");
				nameBuilder.append(regions.get(i).getName() + "-");
			}
			String idsStr = idBuilder.toString();
			String namesStr = nameBuilder.toString();
			map.put("idsStr", idsStr.substring(0, idsStr.length() - 1));
			map.put("namesStr", namesStr.substring(0, namesStr.length() - 1));
		}
		map.remove("id");
		return SUCCESS;
	}

	@Action(value = "inactiveAccount", results = { @Result(name = SUCCESS, type = "json") })
	public String inactiveAccount() {
		UserBase userBase = (UserBase) session.get("userBase");
		User user = userService.inactive(userBase.getId());
		if (user != null) {
			map.put("result", SUCCESS);
			map.put("balance", String.valueOf(user.getBalance()));
		}
		else {
			map.put("result", ERROR);
		}
		return SUCCESS;
	}

	@Action(value = "renewalAccount", results = { @Result(name = SUCCESS, type = "json") })
	public String renewalAccount() {
		UserBase userBase = (UserBase) session.get("userBase");
		User user = userService.renewal(userBase.getId());
		if (user != null) {
			map.put("result", SUCCESS);
			map.put("balance", String.valueOf(user.getBalance()));
			map.put("level", FinalValue.UserLevel.getStrOfUserLevel(user.getLevel()));
		}
		else {
			map.put("result", ERROR);
		}
		return SUCCESS;
	}

	@Action(value = "rechargeAccount", results = { @Result(name = SUCCESS, type = "json") })
	public String rechargeAccount() {
		double amount = Double.parseDouble(map.get("amount"));// 充值金额
		UserBase userBase = (UserBase) session.get("userBase");
		User user = userService.recharge(userBase.getId(), amount);
		if (user != null) {
			map.put("result", SUCCESS);
			map.put("balance", String.valueOf(user.getBalance()));
			map.put("level", FinalValue.UserLevel.getStrOfUserLevel(user.getLevel()));
		}
		else {
			map.put("result", ERROR);
		}
		map.remove("amount");
		return SUCCESS;
	}

	@Action(value = "cancelMembership", results = { @Result(name = SUCCESS, type = "json") })
	public String cancelMembership() {
		UserBase userBase = (UserBase) session.get("userBase");
		User user = userService.login(userBase.getUsername(), map.get("password"));
		if (user != null) {
			ResultMessage result = userService.setRepeatIntField(userBase.getId(), "state", FinalValue.UserState.STOP);
			if (result == ResultMessage.SUCCESS) {
				map.put("result", SUCCESS);
			}
			else {
				map.put("result", ERROR);
			}
		}
		else {
			map.put("result", "passwordError");
		}
		map.remove("password");
		return SUCCESS;
	}

	@JSON
	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
}
