package edu.nju.desserthouse.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.Region;
import edu.nju.desserthouse.model.Shop;
import edu.nju.desserthouse.service.RegionService;
import edu.nju.desserthouse.service.ShopService;

/*
 * 系统管理员网页头action
 */
public class AdminHeadAction extends BaseAction {
	private static final long serialVersionUID = -1215948076449805654L;
	@Autowired
	private RegionService regionService;
	@Autowired
	private ShopService shopService;

	@Action(
			value = "shop",
			results = { @Result(name = SUCCESS, location = "/page/shop/shop.jsp"),
					@Result(name = INPUT, location = "/page/user/login.jsp") })
	public String shop() {
		List<Region> provinces = regionService.getLowerRegions(1);
		List<Shop> shops = shopService.getAllShops();
		request.setAttribute("provinces", provinces);
		request.setAttribute("shops", shops);
		return SUCCESS;
	}// 店铺管理

	@Action(
			value = "staff",
			results = { @Result(name = SUCCESS, location = "/page/staff/staff.jsp"),
					@Result(name = INPUT, location = "/page/user/login.jsp") })
	public String staff() {
		List<Region> provinces = regionService.getLowerRegions(1);
		request.setAttribute("provinces", provinces);
		return SUCCESS;
	}// 店员管理
}
