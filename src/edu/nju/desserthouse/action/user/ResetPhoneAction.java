package edu.nju.desserthouse.action.user;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.service.UserService;
import edu.nju.desserthouse.util.ResultMessage;
import edu.nju.desserthouse.util.UserBase;

@ParentPackage("json-default")
public class ResetPhoneAction extends BaseAction {
	private static final long serialVersionUID = 6357499821590201607L;
	private Map<String, String> map = new HashMap<String, String>();
	@Autowired
	private UserService userService;

	@Action(value = "resetPhone", results = { @Result(name = SUCCESS, type = "json") })
	public String execute() {
		UserBase userBase = (UserBase) session.get("userBase");
		ResultMessage result = userService.resetPhone(userBase.getId(), map.get("newPhone"));
		if (result == ResultMessage.SUCCESS) {
			map.put("result", SUCCESS);
			map.remove("newPhone");
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
