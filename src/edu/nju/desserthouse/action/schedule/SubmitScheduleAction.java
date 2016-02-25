package edu.nju.desserthouse.action.schedule;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.Schedule;

public class SubmitScheduleAction extends BaseAction {
	private static final long serialVersionUID = 5079889182337131165L;
	private Schedule schedule;
	private int shopId;
	private List<List<String>> productIdList;

	@Override
	@Action(
			value = "submitSchedule",
			results = { @Result(
					name = SUCCESS,
					location = "/schedule/targetShopSchedule.action?shopId=${shopId}&scheduleState=0",
					type = "redirect") })
	public String execute() throws Exception {
		for (int i = 0; i < productIdList.size(); i++) {
			for (int j = 0; j < productIdList.get(i).size(); j++) {
				System.out.print(productIdList.get(i).get(j) + "---");
			}
			System.out.println();
		}
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

	public List<List<String>> getProductIdList() {
		return productIdList;
	}

	public void setProductIdList(List<List<String>> productIdList) {
		this.productIdList = productIdList;
	}
}
