package edu.nju.desserthouse.action.shop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.Shop;
import edu.nju.desserthouse.service.ShopService;

@ParentPackage("json-default")
public class SelectShopAction extends BaseAction {
	private static final long serialVersionUID = -6196707578568280745L;
	private Map<String, String> map = new HashMap<String, String>();
	@Autowired
	private ShopService shopService;

	@Action(value = "selectShopByCountyId", results = { @Result(name = SUCCESS, type = "json") })
	public String selectShopByCountyId() {
		int countyId = Integer.parseInt(map.get("countyId"));
		List<Shop> shopes = shopService.getShopesByCountyId(countyId);
		StringBuilder idBuilder = new StringBuilder();
		StringBuilder nameBuilder = new StringBuilder();
		if (shopes != null && shopes.size() != 0) {
			for (int i = 0; i < shopes.size(); i++) {
				idBuilder.append(shopes.get(i).getId() + "---");
				nameBuilder.append(shopes.get(i).getShopname() + "（" + shopes.get(i).getLocation() + "）---");
			}
			String idsStr = idBuilder.toString();
			String namesStr = nameBuilder.toString();
			map.put("idsStr", idsStr.substring(0, idsStr.length() - 3));
			map.put("shopnamesStr", namesStr.substring(0, namesStr.length() - 3));
		}
		map.remove("countyId");
		return SUCCESS;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
}
