package edu.nju.desserthouse.action.schedule;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.Schedule;
import edu.nju.desserthouse.service.ScheduleService;
import edu.nju.desserthouse.util.UserBase;

public class SubmitScheduleAction extends BaseAction {
	private static final long serialVersionUID = 5079889182337131165L;
	private Schedule schedule;
	private int shopId;
	private List<Integer> productIdList;
	@Autowired
	private ScheduleService scheduleService;

	@Override
	@Action(
			value = "submitSchedule",
			results = { @Result(
					name = SUCCESS,
					location = "/schedule/targetShopSchedule.action?shopId=${shopId}&scheduleState=0",
					type = "redirect") })
	public String execute() throws Exception {
		UserBase userBase = (UserBase) session.get("userBase");
		int operatorId = userBase.getId();
		scheduleService.addSchedule(schedule, productIdList, shopId, operatorId);
		return SUCCESS;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public List<Integer> getProductIdList() {
		return productIdList;
	}

	public void setProductIdList(List<Integer> productIdList) {
		this.productIdList = productIdList;
	}
}
