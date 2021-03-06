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

public class ShopPreDayAction extends BaseAction {
	private static final long serialVersionUID = 5493083879201029390L;
	private int shopId;
	private String curDateStr;
	@Autowired
	private BookService bookService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private RegionService regionService;

	@Override
	@Action(value = "shopPreDay", results = { @Result(name = SUCCESS, location = "/page/book/bookGoods.jsp") })
	public String execute() throws Exception {
		Shop shop = shopService.getShopById(shopId);
		String regionStr = regionService.getCompleteRegionStr(shop.getRegion().getId());
		Date curDate = Date.valueOf(curDateStr);
		Date preDate = new Date(curDate.getTime() - 24 * 60 * 60 * 1000);
		List<Goods> goodsList = bookService.getTrgetDayGoods(shopId, preDate);
		request.setAttribute("shop", shop);
		request.setAttribute("shopRegionStr", regionStr);
		request.setAttribute("date", preDate);
		request.setAttribute("goodsList", goodsList);
		return SUCCESS;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public String getCurDateStr() {
		return curDateStr;
	}

	public void setCurDateStr(String curDateStr) {
		this.curDateStr = curDateStr;
	}
}
