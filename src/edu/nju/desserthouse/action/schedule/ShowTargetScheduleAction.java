package edu.nju.desserthouse.action.schedule;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.Schedule;
import edu.nju.desserthouse.service.ScheduleService;

public class ShowTargetScheduleAction extends BaseAction {
	private static final long serialVersionUID = 8379488847664830267L;
	private int scheduleId;
	@Autowired
	private ScheduleService scheduleService;

	@Override
	@Action(
			value = "showTargetSchedule",
			results = { @Result(name = SUCCESS, location = "/page/schedule/showTargetSchedule.jsp") })
	public String execute() throws Exception {
		Schedule schedule = scheduleService.getScheduleById(scheduleId);
		request.setAttribute("schedule", schedule);
		return SUCCESS;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

}
