package edu.nju.desserthouse.service;

import java.sql.Date;
import java.util.List;

import edu.nju.desserthouse.model.Schedule;

public interface ScheduleService {
	public Date getLastScheduleDate(int shopId);// 得到某个店铺最后的产品计划日期

	public Date getNextScheduleStartDate(int shopId);// 得到下一个产品计划开始日期

	public List<Schedule> getShopSchedules(int shopId, int scheduleState);// 得到某个店铺某种种状态的产品计划
}