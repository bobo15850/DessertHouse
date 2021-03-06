package edu.nju.desserthouse.service;

import java.sql.Date;
import java.util.List;

import edu.nju.desserthouse.model.Schedule;
import edu.nju.desserthouse.util.ResultMessage;

public interface ScheduleService {
	public Date getLastScheduleDate(int shopId);// 得到某个店铺最后的产品计划日期

	public Date getNextScheduleStartDate(int shopId);// 得到下一个产品计划开始日期

	public List<Date> getNextScheduleDates(int shopId);// 得到下一个产品计划所有日期

	public List<Schedule> getShopSchedules(int shopId, int scheduleState);// 得到某个店铺某种种状态的产品计划

	public ResultMessage addSchedule(Schedule schedule, List<Integer> productIdList, int shopId, int operatorId);// 添加一个计划

	public ResultMessage modifySchedule(Schedule schedule, int rawScheduleId, int opreatorId);// 修改产品计划，参数中的schedule只有单价和数量，其他的内容需要从数据库取得

	public Schedule getScheduleById(int scheduleId);// 根据产品计划id得到产品计划

	public List<Schedule> getScheduleByState(int scheduleState);// 得到特定状态的产品计划

	public ResultMessage approveSchedule(int scheduleId, int approveResult, int approverId);// 审批产品计划
}