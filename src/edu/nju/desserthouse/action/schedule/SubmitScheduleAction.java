package edu.nju.desserthouse.action.schedule;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.Schedule;
import edu.nju.desserthouse.model.ScheduleItem;

public class SubmitScheduleAction extends BaseAction {
	private static final long serialVersionUID = 5079889182337131165L;
	private Schedule schedule;
	private int shopId;
	private ScheduleItem scheduleItem_0;
	private ScheduleItem scheduleItem_1;
	private ScheduleItem scheduleItem_2;
	private ScheduleItem scheduleItem_3;
	private ScheduleItem scheduleItem_4;
	private ScheduleItem scheduleItem_5;
	private ScheduleItem scheduleItem_6;

	@Override
	@Action(
			value = "submitSchedule",
			results = { @Result(
					name = SUCCESS,
					location = "/schedule/targetShopSchedule.action?shopId=${shopId}&scheduleState=0",
					type = "redirect") })
	public String execute() throws Exception {
		return SUCCESS;
	}

	public ScheduleItem getScheduleItem_0() {
		return scheduleItem_0;
	}

	public void setScheduleItem_0(ScheduleItem scheduleItem_0) {
		this.scheduleItem_0 = scheduleItem_0;
	}

	public ScheduleItem getScheduleItem_1() {
		return scheduleItem_1;
	}

	public void setScheduleItem_1(ScheduleItem scheduleItem_1) {
		this.scheduleItem_1 = scheduleItem_1;
	}

	public ScheduleItem getScheduleItem_2() {
		return scheduleItem_2;
	}

	public void setScheduleItem_2(ScheduleItem scheduleItem_2) {
		this.scheduleItem_2 = scheduleItem_2;
	}

	public ScheduleItem getScheduleItem_3() {
		return scheduleItem_3;
	}

	public void setScheduleItem_3(ScheduleItem scheduleItem_3) {
		this.scheduleItem_3 = scheduleItem_3;
	}

	public ScheduleItem getScheduleItem_4() {
		return scheduleItem_4;
	}

	public void setScheduleItem_4(ScheduleItem scheduleItem_4) {
		this.scheduleItem_4 = scheduleItem_4;
	}

	public ScheduleItem getScheduleItem_5() {
		return scheduleItem_5;
	}

	public void setScheduleItem_5(ScheduleItem scheduleItem_5) {
		this.scheduleItem_5 = scheduleItem_5;
	}

	public ScheduleItem getScheduleItem_6() {
		return scheduleItem_6;
	}

	public void setScheduleItem_6(ScheduleItem scheduleItem_6) {
		this.scheduleItem_6 = scheduleItem_6;
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
}
