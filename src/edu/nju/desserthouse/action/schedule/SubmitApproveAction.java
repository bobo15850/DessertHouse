package edu.nju.desserthouse.action.schedule;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.service.ScheduleService;

public class SubmitApproveAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private int scheduleId;
	private int approveResult;
	@Autowired
	private ScheduleService scheduleService;

	@Override
	@Action(
			value = "submitApprove",
			results = { @Result(name = SUCCESS, location = "/approval.action", type = "redirect") })
	public String execute() throws Exception {
		scheduleService.approveSchedule(scheduleId, approveResult);
		return SUCCESS;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public int getApproveResult() {
		return approveResult;
	}

	public void setApproveResult(int approveResult) {
		this.approveResult = approveResult;
	}

}
