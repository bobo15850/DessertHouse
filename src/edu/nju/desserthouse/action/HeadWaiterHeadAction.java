package edu.nju.desserthouse.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.Product;
import edu.nju.desserthouse.model.Region;
import edu.nju.desserthouse.model.Shop;
import edu.nju.desserthouse.service.ProductService;
import edu.nju.desserthouse.service.RegionService;
import edu.nju.desserthouse.service.ScheduleService;
import edu.nju.desserthouse.service.ShopService;

/*
 * 总店管理员网页头链接action（只有两个分标签页）
 */
public class HeadWaiterHeadAction extends BaseAction {
	private static final long serialVersionUID = -8410891153722283062L;
	@Autowired
	private ProductService productService;
	@Autowired
	private RegionService regionService;
	@Autowired
	private ScheduleService scheduleService;
	@Autowired
	private ShopService shopService;

	@Action(
			value = "schedule",
			results = { @Result(name = SUCCESS, location = "/page/schedule/schedule.jsp"),
					@Result(name = INPUT, location = "/page/user/login.jsp") })
	public String schedule() {
		List<Region> provinces = regionService.getLowerRegions(1);
		request.setAttribute("provinces", provinces);
		List<Shop> shops = shopService.getAllShops();
		request.setAttribute("shops", shops);
		List<Date> lastScheduleDates = new ArrayList<Date>();
		for (Shop shop : shops) {
			Date date = scheduleService.getLastScheduleDate(shop.getId());
			System.out.println("产品计划最后时间是：" + date);
			lastScheduleDates.add(date);
		}
		request.setAttribute("lastScheduleDates", lastScheduleDates);// 每个店铺最后的产品计划时间
		return SUCCESS;
	}// 产品计划管理

	@Action(
			value = "product",
			results = { @Result(name = SUCCESS, location = "/page/product/product.jsp"),
					@Result(name = INPUT, location = "/page/user/login.jsp") })
	public String product() {
		List<Product> products = productService.getAllProducts();
		request.setAttribute("products", products);
		return SUCCESS;
	}// 产品管理
}
