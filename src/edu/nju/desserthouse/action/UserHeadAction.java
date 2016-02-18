package edu.nju.desserthouse.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.Region;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.RegionService;
import edu.nju.desserthouse.service.UserService;
import edu.nju.desserthouse.util.UserBase;

public class UserHeadAction extends BaseAction {
	private static final long serialVersionUID = 1775354160544472810L;
	@Autowired
	private UserService userService;
	@Autowired
	private RegionService regionService;

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
			request.setAttribute("province", "未设置");
			request.setAttribute("city", "未设置");
			request.setAttribute("county", "未设置");
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
	}// 到我的账户页面

	@Action(
			value = "consumptionRecord",
			results = { @Result(name = SUCCESS, location = "/page/user/consumptionRecord.jsp"),
					@Result(name = INPUT, location = "/page/user/login.jsp") })
	public String ConsumptionRecord() {
		return SUCCESS;
	}// 到消费记录页面

	@Action(
			value = "bookGoods",
			results = { @Result(name = SUCCESS, location = "/page/book/bookGoods.jsp"),
					@Result(name = INPUT, location = "/page/user/login.jsp") })
	public String bookGoods() {
		return SUCCESS;
	}// 到预定商品展示页面

	@Action(
			value = "rechargeRecord",
			results = { @Result(name = SUCCESS, location = "/page/user/rechargeRecord.jsp"),
					@Result(name = INPUT, location = "/page/user/login.jsp") })
	public String rechargeRecord() {
		return SUCCESS;
	}// 到充值记录查看页面
}
