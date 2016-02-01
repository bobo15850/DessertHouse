package edu.nju.desserthouse.action.user;

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
public class RenameAction extends BaseAction {
	private static final long serialVersionUID = 4849557797102900761L;
	private Map<String, String> map = new HashMap<String, String>();
	@Autowired
	private UserService userService;

	@Action(value = "rename", results = { @Result(name = SUCCESS, type = "json") })
	public String execute() {
		UserBase userBase = (UserBase) session.get("userBase");
		ResultMessage result = userService.renameUser(userBase.getId(), map.get("newName"));
		if (result == ResultMessage.SUCCESS) {
			User user = userService.getUserById(userBase.getId());
			userBase = new UserBase(user);
			session.replace("userBase", userBase);
			map.put("result", SUCCESS);
			map.remove("newName");
		}
		else {
			map.put("result", ERROR);
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
