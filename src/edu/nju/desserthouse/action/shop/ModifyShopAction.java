package edu.nju.desserthouse.action.shop;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.Shop;
import edu.nju.desserthouse.service.ShopService;

public class ModifyShopAction extends BaseAction {
	private static final long serialVersionUID = -4589640473990271461L;
	@Autowired
	private ShopService shopService;
	private int shopId;
	private String shopname;
	private String phonenumber;
	private String location;
	private int regionId;

	@Override
	@Action(
			value = "modifyShop",
			results = { @Result(
					name = SUCCESS,
					location = "/shop/toModifyShop.action?shopId=${shopId}",
					type = "redirect") })
	public String execute() throws Exception {
		Shop shop = shopService.getShopById(shopId);
		shop.setShopname(shopname);
		shop.setPhonenumber(phonenumber);
		shop.setLocation(location);
		shopService.modifyShop(shop, regionId);
		return SUCCESS;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
}
