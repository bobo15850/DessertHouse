package edu.nju.desserthouse.action.schedule;

import java.sql.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.Product;
import edu.nju.desserthouse.model.Shop;
import edu.nju.desserthouse.service.ProductService;
import edu.nju.desserthouse.service.ScheduleService;
import edu.nju.desserthouse.service.ShopService;

public class DraftScheduleAction extends BaseAction {
	private static final long serialVersionUID = -1256870368392620697L;
	private int shopId;
	@Autowired
	private ShopService shopService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ScheduleService scheduleService;

	@Override
	@Action(
			value = "draftSchedule",
			results = { @Result(name = SUCCESS, location = "/page/schedule/draftSchedule.jsp") })
	public String execute() throws Exception {
		Shop shop = shopService.getShopById(shopId);
		List<Product> products = productService.getAllProducts();
		List<Date> dates = scheduleService.getNextScheduleDates(shopId);
		request.setAttribute("shop", shop);
		request.setAttribute("products", products);
		request.setAttribute("dates", dates);
		return SUCCESS;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
}
