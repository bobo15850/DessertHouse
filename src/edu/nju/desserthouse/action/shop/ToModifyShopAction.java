package edu.nju.desserthouse.action.shop;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.Region;
import edu.nju.desserthouse.model.Shop;
import edu.nju.desserthouse.service.RegionService;
import edu.nju.desserthouse.service.ShopService;

public class ToModifyShopAction extends BaseAction {
	private static final long serialVersionUID = 6596241940041207187L;
	@Autowired
	private ShopService shopService;
	@Autowired
	private RegionService regionService;

	@Override
	@Action(value = "toModifyShop", results = { @Result(name = SUCCESS, location = "/page/shop/modifyShop.jsp") })
	public String execute() throws Exception {
		int shopId = Integer.parseInt(request.getParameter("shopId"));
		Shop shop = shopService.getShopById(shopId);
		request.setAttribute("shop", shop);
		Region targetCounty = regionService.getRegionById(shop.getRegion().getId());
		Region targetCity = regionService.getRegionById(targetCounty.getParentId());
		Region targetProvince = regionService.getRegionById(targetCity.getParentId());
		request.setAttribute("targetCounty", targetCounty);
		request.setAttribute("targetCity", targetCity);
		request.setAttribute("targetProvince", targetProvince);
		// 取得所有的地区
		List<Region> provinces = regionService.getLowerRegions(1);
		List<Region> cities = regionService.getLowerRegions(targetProvince.getId());
		List<Region> counties = regionService.getLowerRegions(targetCity.getId());
		request.setAttribute("provinces", provinces);
		request.setAttribute("cities", cities);
		request.setAttribute("counties", counties);
		return SUCCESS;
	}

}
