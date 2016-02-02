package edu.nju.desserthouse.action.user;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.UserService;
import edu.nju.desserthouse.util.ResultMessage;
import edu.nju.desserthouse.util.UserBase;

@ParentPackage("json-default")
public class SetFieldAction extends BaseAction {
	private static final long serialVersionUID = 4849557797102900761L;
	private Map<String, String> map = new HashMap<String, String>();
	@Autowired
	private UserService userService;

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
		System.out.println(Date.valueOf(map.get("birthday")));
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

	@JSON
	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
}
