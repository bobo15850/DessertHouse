package edu.nju.desserthouse.action.shop;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.Shop;
import edu.nju.desserthouse.service.ShopService;
import edu.nju.desserthouse.util.ResultMessage;

public class AddShopAction extends BaseAction {
	private static final long serialVersionUID = 2388219418478057008L;
	@Autowired
	private ShopService shopService;
	private Shop shop;
	private int regionId;

	@Override
	@Action(
			value = "addShop",
			results = { @Result(name = SUCCESS, location = "/shop.action", type = "redirect"),
					@Result(name = INPUT, location = "/page/user/login.jsp") })
	public String execute() throws Exception {
		ResultMessage result = shopService.addShop(shop, regionId);
		if (result == ResultMessage.SUCCESS) {
			return SUCCESS;
		}
		return INPUT;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
}
