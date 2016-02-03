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
import edu.nju.desserthouse.util.ResultMessage;
import edu.nju.desserthouse.util.UserBase;

@ParentPackage("json-default")
public class SetFieldAction extends BaseAction {
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
		User user = userService.getUserById(userBase.getId());
		Region region = regionService.getRegionById(id);
		user.setRegion(region);
		ResultMessage result = userService.updateUser(user);
		if (result == ResultMessage.SUCCESS) {
			map.put("result", SUCCESS);
		}
		else {
			map.put("result", ERROR);
		}
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
