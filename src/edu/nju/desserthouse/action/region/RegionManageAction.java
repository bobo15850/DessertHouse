package edu.nju.desserthouse.action.region;

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
import edu.nju.desserthouse.service.RegionService;

@ParentPackage("json-default")
public class RegionManageAction extends BaseAction {
	private static final long serialVersionUID = -7585948336086813570L;
	private Map<String, String> map = new HashMap<String, String>();

	@Autowired
	private RegionService regionService;

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

	@JSON
	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
}
