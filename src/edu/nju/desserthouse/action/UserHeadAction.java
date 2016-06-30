package edu.nju.desserthouse.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.BookRecord;
import edu.nju.desserthouse.model.Region;
import edu.nju.desserthouse.model.SalesRecord;
import edu.nju.desserthouse.model.Shop;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.BookService;
import edu.nju.desserthouse.service.RegionService;
import edu.nju.desserthouse.service.SaleService;
import edu.nju.desserthouse.service.ShopService;
import edu.nju.desserthouse.service.UserService;
import edu.nju.desserthouse.util.FinalValue;
import edu.nju.desserthouse.util.UserBase;

public class UserHeadAction extends BaseAction {
	private static final long serialVersionUID = 1775354160544472810L;
	@Autowired
	private UserService userService;
	@Autowired
	private RegionService regionService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private SaleService saleSerivce;
	@Autowired
	private BookService bookService;

	@Action(
			value = "myAccount",
			results = { @Result(name = SUCCESS, location = "/page/user/myAccount.jsp"),
					@Result(name = INPUT, location = "/page/user/login.jsp") })
	public String myAccount() {
		UserBase userBase = (UserBase) session.get("userBase");
		User user = userService.getUserById(userBase.getId());
		request.setAttribute("user", user);
		List<Region> provinces = regionService.getLowerRegions(1);
		request.setAttribute("provinces", provinces);
		if (user.getRegion() == null) {
			request.setAttribute("province", "鏈缃�");
			request.setAttribute("city", "鏈缃�");
			request.setAttribute("county", "鏈缃�");
		}
		else {
			Region county = user.getRegion();
			Region city = regionService.getRegionById(county.getParentId());
			Region province = regionService.getRegionById(city.getParentId());
			request.setAttribute("province", province.getName());
			request.setAttribute("city", city.getName());
			request.setAttribute("county", county.getName());
			List<Region> citys = regionService.getLowerRegions(province.getId());
			List<Region> countys = regionService.getLowerRegions(city.getId());
			request.setAttribute("citys", citys);
			request.setAttribute("countys", countys);
		}
		return SUCCESS;
	}// 鍒版垜鐨勮处鎴烽〉闈�

	@Action(
			value = "consumptionRecord",
			results = { @Result(name = SUCCESS, location = "/page/user/consumptionRecord.jsp"),
					@Result(name = INPUT, location = "/page/user/login.jsp") })
	public String ConsumptionRecord() {
		UserBase userBase = (UserBase) session.get("userBase");
		int orderState = 0;
		String orderStateStr = request.getParameter("orderState");
		if (orderStateStr != null) {
			orderState = Integer.parseInt(orderStateStr);
		}
		if (orderState == FinalValue.SALES_RECORD) {
			List<SalesRecord> orders = saleSerivce.getSalesRecordByUser(userBase.getId());
			request.setAttribute("orders", orders.size() == 0 ? null : orders);
		}
		else {
			List<BookRecord> orders = bookService.getTarStateBookRecordByUser(userBase.getId(), orderState);
			request.setAttribute("orders", orders.size() == 0 ? null : orders);
		}
		request.setAttribute("orderState", orderState);
		return SUCCESS;
	}// 鍒版秷璐硅褰曢〉闈�

	@Action(
			value = "shopSelectOfBook",
			results = { @Result(name = SUCCESS, location = "/page/book/shopSelectOfBook.jsp"),
					@Result(name = INPUT, location = "/page/user/login.jsp") })
	public String shopSelectOfBook() {
		List<Shop> shops = shopService.getAllShops();
		request.setAttribute("shops", shops);
		UserBase userBase = (UserBase) session.get("userBase");
		User user = userService.getUserById(userBase.getId());
		request.setAttribute("user", user);
		return SUCCESS;
	}// 鍒伴瀹氬晢鍝佸睍绀洪〉闈�
	
	@Action(
			value = "shopSelectOfBreakfast",
			results = { @Result(name = SUCCESS, location = "/page/book/shopSelectOfBreakfast.jsp"),
					@Result(name = INPUT, location = "/page/user/login.jsp") })
	public String shopSelectOfBreakfast() {
		List<Shop> shops = shopService.getAllShops();
		request.setAttribute("shops", shops);
		UserBase userBase = (UserBase) session.get("userBase");
		User user = userService.getUserById(userBase.getId());
		request.setAttribute("user", user);
		return SUCCESS;
	}// 到预定早餐展示页面

	@Action(
			value = "rechargeRecord",
			results = { @Result(name = SUCCESS, location = "/page/user/rechargeRecord.jsp"),
					@Result(name = INPUT, location = "/page/user/login.jsp") })
	public String rechargeRecord() {
		UserBase userase = (UserBase) session.get("userBase");
		User user = userService.getUserById(userase.getId());
		request.setAttribute("rechargeRecordList", user.getRechargeRecordList());
		return SUCCESS;
	}// 鍒板厖鍊艰褰曟煡鐪嬮〉闈�
}
