package edu.nju.desserthouse.action.book;

import java.sql.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.Goods;
import edu.nju.desserthouse.model.Region;
import edu.nju.desserthouse.model.Shop;
import edu.nju.desserthouse.service.BookService;
import edu.nju.desserthouse.service.RegionService;
import edu.nju.desserthouse.service.ShopService;

public class ShopGoodsBookAction extends BaseAction {
	private static final long serialVersionUID = -8081706159488684208L;

	@Autowired
	private ShopService shopService;
	@Autowired
	private RegionService regionService;

	@Autowired
	private BookService bookService;
	private int shopId;

	@Override
	@Action(value = "shopGoodsBook", results = { @Result(name = SUCCESS, location = "/page/book/bookGoods.jsp") })
	public String execute() throws Exception {
		Shop shop = shopService.getShopById(shopId);
		request.setAttribute("shop", shop);
		Region county = shop.getRegion();
		Region city = regionService.getRegionById(county.getParentId());
		Region province = regionService.getRegionById(city.getParentId());
		String shopRegionStr = province.getName() + "省" + city.getName() + "市" + county.getName();
		request.setAttribute("shopRegionStr", shopRegionStr);
		List<Goods> goodsList = bookService.getTomorrowGoods(shopId);
		Date date = new Date(System.currentTimeMillis() + 1000 * 24 * 60 * 60);
		request.setAttribute("date", date);
		request.setAttribute("goodsList", goodsList);
		return SUCCESS;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
}
