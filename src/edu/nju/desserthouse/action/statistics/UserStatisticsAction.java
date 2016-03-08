package edu.nju.desserthouse.action.statistics;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import edu.nju.desserthouse.action.BaseAction;

/*
 * 会员统计
 */
public class UserStatisticsAction extends BaseAction {
	private static final long serialVersionUID = -5914920868166360197L;
	private int year;
	private int month;

	@Override
	@Action(
			value = "userStatistics",
			results = { @Result(name = SUCCESS, location = "/page/statistics/userStatistics.jsp"),
					@Result(name = INPUT, location = "/page/common/error.jsp") })
	public String execute() throws Exception {
		request.setAttribute("year", year == 0 ? 2016 : year);
		request.setAttribute("month", month == 0 ? 3 : month);
		return SUCCESS;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

}
