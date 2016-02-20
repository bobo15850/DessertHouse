package edu.nju.desserthouse.action.shop;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.service.ShopService;

public class DeleteShopAction extends BaseAction {
	private static final long serialVersionUID = -6951498530140928048L;
	@Autowired
	private ShopService shopService;
	private int shopId;

	@Override
	@Action(value = "deleteShop", results = { @Result(name = SUCCESS, location = "/shop.action", type = "redirect") })
	public String execute() throws Exception {
		shopService.deleteShop(shopId);
		return SUCCESS;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

}
