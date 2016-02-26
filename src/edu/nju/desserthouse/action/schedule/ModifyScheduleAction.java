package edu.nju.desserthouse.action.schedule;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.Schedule;
import edu.nju.desserthouse.service.ScheduleService;

public class ModifyScheduleAction extends BaseAction {
	private static final long serialVersionUID = 5719844897127162579L;
	private int scheduleId;
	@Autowired
	private ScheduleService scheduleService;

	@Override
	@Action(
			value = "modifySchedule",
			results = { @Result(name = SUCCESS, location = "/page/schedule/modifySchedule.jsp") })
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
