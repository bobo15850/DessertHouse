package edu.nju.desserthouse.action.book;

import java.sql.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.Goods;
import edu.nju.desserthouse.model.Shop;
import edu.nju.desserthouse.service.BookService;
import edu.nju.desserthouse.service.RegionService;
import edu.nju.desserthouse.service.ShopService;

public class ShopTarDayAction extends BaseAction {
	private static final long serialVersionUID = 6330123354408415142L;
	private int shopId;
	private String targetDateStr;
	@Autowired
	private BookService bookService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private RegionService regionService;

	@Override
	@Action(value = "shopTarDay", results = { @Result(name = SUCCESS, location = "/page/book/bookGoods.jsp") })
	public String execute() throws Exception {
		Shop shop = shopService.getShopById(shopId);
		String regionStr = regionService.getCompleteRegionStr(shop.getRegion().getId());
		Date targetDate = Date.valueOf(targetDateStr);
		List<Goods> goodsList = bookService.getTrgetDayGoods(shopId, targetDate);
		request.setAttribute("shop", shop);
		request.setAttribute("shopRegionStr", regionStr);
		request.setAttribute("date", targetDate);
		request.setAttribute("goodsList", goodsList);
		return SUCCESS;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public String getTargetDateStr() {
		return targetDateStr;
	}

	public void setTargetDateStr(String targetDateStr) {
		this.targetDateStr = targetDateStr;
	}

}
