package edu.nju.desserthouse.action.schedule;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.Schedule;
import edu.nju.desserthouse.service.ScheduleService;
import edu.nju.desserthouse.util.UserBase;

public class SubmitModifyAction extends BaseAction {
	private static final long serialVersionUID = -7195183268749282934L;
	private int shopId;
	private int scheduleId;
	private Schedule schedule;
	@Autowired
	private ScheduleService scheduleService;

	@Override
	@Action(
			value = "submitModify",
			results = { @Result(
					name = SUCCESS,
					location = "/schedule/targetShopSchedule.action?shopId=${shopId}&scheduleState=0",
					type = "redirect") })
	public String execute() throws Exception {
		UserBase userBase = (UserBase) session.get("userBase");
		scheduleService.modifySchedule(schedule, scheduleId, userBase.getId());
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

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}
}
