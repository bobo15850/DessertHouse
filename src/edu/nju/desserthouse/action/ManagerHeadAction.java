package edu.nju.desserthouse.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.Schedule;
import edu.nju.desserthouse.service.ScheduleService;

/*
 * 总经理网页头不action
 */
public class ManagerHeadAction extends BaseAction {
	private static final long serialVersionUID = 6394820601304388343L;
	@Autowired
	private ScheduleService scheduleService;

	@Action(
			value = "approval",
			results = { @Result(name = SUCCESS, location = "/page/schedule/approval.jsp"),
					@Result(name = INPUT, location = "/page/user/login.jsp") })
	public String approval() {
		String scheduleStateStr = request.getParameter("scheduleState");
		int scheduleState = scheduleStateStr == null ? 0 : Integer.parseInt(scheduleStateStr);
		List<Schedule> schedules = scheduleService.getScheduleByState(scheduleState);
		request.setAttribute("schedules", schedules);
		request.setAttribute("scheduleState", scheduleState);
		return SUCCESS;
	}// 产品计划审批

	@Action(
			value = "statistics",
			results = { @Result(name = SUCCESS, location = "/statistics/userStatistics.action", type = "redirect"),
					@Result(name = INPUT, location = "/page/user/login.jsp") })
	public String statistics() {
		return SUCCESS;
	}// 产品计划审批
}
