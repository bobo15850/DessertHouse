package edu.nju.desserthouse.action.statistics;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import edu.nju.desserthouse.action.BaseAction;

/*
 * 分店统计
 */
public class ShopStatisticsAction extends BaseAction {
	private static final long serialVersionUID = 6739335969999967209L;
	private int year;
	private int month;

	@Override
	@Action(
			value = "shopStatistics",
			results = { @Result(name = SUCCESS, location = "/page/statistics/shopStatistics.jsp"),
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
