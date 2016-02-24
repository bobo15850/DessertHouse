package edu.nju.desserthouse.action.schedule;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.Region;
import edu.nju.desserthouse.model.Schedule;
import edu.nju.desserthouse.model.Shop;
import edu.nju.desserthouse.service.RegionService;
import edu.nju.desserthouse.service.ScheduleService;
import edu.nju.desserthouse.service.ShopService;

public class TargetShopScheduleAction extends BaseAction {
	private static final long serialVersionUID = -7854081232835954230L;
	private int shopId;// 店铺id
	private int scheduleState;// 产品计划状态
	@Autowired
	private RegionService regionService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private ScheduleService scheduleService;

	@Override
	@Action(
			value = "targetShopSchedule",
			results = { @Result(name = SUCCESS, location = "/page/schedule/targetShopSchedule.jsp") })
	public String execute() throws Exception {
		List<Region> provinces = regionService.getLowerRegions(1);
		request.setAttribute("provinces", provinces);
		Shop shop = shopService.getShopById(shopId);
		request.setAttribute("shop", shop);
		request.setAttribute("scheduleState", scheduleState);
		List<Schedule> schedules = scheduleService.getShopSchedules(shopId, scheduleState);
		request.setAttribute("schedules", schedules);
		Region county = shop.getRegion();
		Region city = regionService.getRegionById(county.getParentId());
		Region province = regionService.getRegionById(city.getParentId());
		String shopRegionStr = province.getName() + "省" + city.getName() + "市" + county.getName();
		request.setAttribute("shopRegionStr", shopRegionStr);
		return SUCCESS;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public int getScheduleState() {
		return scheduleState;
	}

	public void setScheduleState(int scheduleState) {
		this.scheduleState = scheduleState;
	}

}
