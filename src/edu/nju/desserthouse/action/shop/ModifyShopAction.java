package edu.nju.desserthouse.action.shop;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import edu.nju.desserthouse.action.BaseAction;

public class ModifyShopAction extends BaseAction {
	private static final long serialVersionUID = -4589640473990271461L;
	private String shopIdStr = "";

	@Override
	@Action(
			value = "modifyShop",
			results = { @Result(
					name = SUCCESS,
					location = "toModifyShop.action",
					type = "redirect",
					params = { "shopId", "${shopIdStr}" }) })
	public String execute() throws Exception {
		this.setShopIdStr("3");
		return SUCCESS;
	}

	public String getShopIdStr() {
		return shopIdStr;
	}

	public void setShopIdStr(String shopIdStr) {
		this.shopIdStr = shopIdStr;
	}

}
