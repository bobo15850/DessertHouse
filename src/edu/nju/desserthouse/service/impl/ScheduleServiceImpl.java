package edu.nju.desserthouse.service.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nju.desserthouse.dao.ProductDao;
import edu.nju.desserthouse.dao.ScheduleDao;
import edu.nju.desserthouse.dao.ShopDao;
import edu.nju.desserthouse.dao.UserDao;
import edu.nju.desserthouse.model.Goods;
import edu.nju.desserthouse.model.Product;
import edu.nju.desserthouse.model.Schedule;
import edu.nju.desserthouse.model.ScheduleGoodsItem;
import edu.nju.desserthouse.model.ScheduleItem;
import edu.nju.desserthouse.model.Shop;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.ScheduleService;
import edu.nju.desserthouse.util.FinalValue;
import edu.nju.desserthouse.util.ResultMessage;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	@Autowired
	private ScheduleDao scheduleDao;
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private UserDao userDao;

	@Override
	public Date getLastScheduleDate(int shopId) {
		Shop shop = shopDao.get(Shop.class, shopId);
		final Date startDate = scheduleDao.getLastStartDate(shop);
		if (startDate != null) {
			long timeLong = startDate.getTime();
			timeLong = ((timeLong / 1000) + 6 * 24 * 60 * 60) * 1000;// 最后一个产品计划开始日期，加上六天的时间，即为产品计划的最后一天
			return new Date(timeLong);
		}
		else {
			return new Date(System.currentTimeMillis());
		}
	}

	@Override
	public List<Schedule> getShopSchedules(int shopId, int scheduleState) {
		Shop shop = shopDao.get(Shop.class, shopId);
		String[] columns = { "shop", "state" };
		Object[] values = { shop, new Integer(scheduleState) };
		return scheduleDao.findByColumns(Schedule.class, columns, values);
	}

	@Override
	public Date getNextScheduleStartDate(int shopId) {
		final Date lastScheduleDate = this.getLastScheduleDate(shopId);
		long timeLong = 1000 * (lastScheduleDate.getTime() / 1000 + 24 * 60 * 60);
		return new Date(timeLong);
	}

	@Override
	public List<Date> getNextScheduleDates(int shopId) {
		Date startDate = this.getNextScheduleStartDate(shopId);
		long timeLong = startDate.getTime();
		List<Date> dates = new ArrayList<Date>();
		dates.add(startDate);
		for (int i = 0; i < 6; i++) {
			timeLong += 1000 * 24 * 60 * 60;
			Date tempDate = new Date(timeLong);
			dates.add(tempDate);
		}
		return dates;
	}

	@Override
	public ResultMessage addSchedule(Schedule schedule, List<Integer> productIdList, int shopId, int operatorId) {
		List<Product> productList = new ArrayList<Product>();// 产品列表
		for (int id : productIdList) {
			productList.add(productDao.get(Product.class, id));
		}
		List<Date> dateList = this.getNextScheduleDates(shopId);
		for (int dayNum = 0; dayNum < schedule.getScheduleItemList().size(); dayNum++) {
			ScheduleItem scheduleItem = schedule.getScheduleItemList().get(dayNum);
			scheduleItem.setEffectiveDate(dateList.get(dayNum));
			scheduleItem.setSchedule(schedule);
			for (int productNum = 0; productNum < scheduleItem.getGoodsItemList().size(); productNum++) {
				ScheduleGoodsItem goodsItem = scheduleItem.getGoodsItemList().get(productNum);
				goodsItem.setProduct(productList.get(productNum));
				goodsItem.setScheduleItem(scheduleItem);
			}
		} // 填充每一天的产品计划
		User operatot = userDao.get(User.class, operatorId);
		schedule.setOperator(operatot);
		Shop shop = shopDao.get(Shop.class, shopId);
		schedule.setShop(shop);
		schedule.setStartDate(dateList.get(0));
		schedule.setState(FinalValue.ScheduleState.APPROVING);
		schedule.setCreatedTime(new Timestamp(System.currentTimeMillis()));
		return scheduleDao.save(schedule);
	}

	@Override
	public Schedule getScheduleById(int scheduleId) {
		return scheduleDao.get(Schedule.class, scheduleId);
	}

	@Override
	public ResultMessage modifySchedule(Schedule schedule, int rawScheduleId, int opreatorId) {
		Schedule rawSchedule = scheduleDao.get(Schedule.class, rawScheduleId);
		User operator = userDao.get(User.class, opreatorId);
		rawSchedule.setOperator(operator);
		rawSchedule.setState(FinalValue.ScheduleState.APPROVING);
		for (int dayNum = 0; dayNum < rawSchedule.getScheduleItemList().size(); dayNum++) {
			ScheduleItem scheduleItem = rawSchedule.getScheduleItemList().get(dayNum);
			for (int productNum = 0; productNum < scheduleItem.getGoodsItemList().size(); productNum++) {
				ScheduleGoodsItem goodsItem = scheduleItem.getGoodsItemList().get(productNum);
				goodsItem.setPrice(
						schedule.getScheduleItemList().get(dayNum).getGoodsItemList().get(productNum).getPrice());
				goodsItem.setQuantity(
						schedule.getScheduleItemList().get(dayNum).getGoodsItemList().get(productNum).getQuantity());
			}
		} // 填充每一天的产品计划
		return scheduleDao.update(rawSchedule);
	}

	@Override
	public List<Schedule> getScheduleByState(int scheduleState) {
		String[] columns = { "state" };
		Object[] values = { new Integer(scheduleState) };
		return scheduleDao.findByColumns(Schedule.class, columns, values);
	}

	@Override
	public ResultMessage approveSchedule(int scheduleId, int approveResult, int approverId) {
		Schedule schedule = scheduleDao.get(Schedule.class, scheduleId);
		schedule.setState(approveResult);
		User approver = userDao.get(User.class, approverId);
		schedule.setApprover(approver);
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		if (FinalValue.ScheduleState.APPROVE_SUCCEED == approveResult) {
			scheduleDao.update(schedule);
			for (ScheduleItem scheduleItem : schedule.getScheduleItemList()) {
				for (ScheduleGoodsItem goodsItem : scheduleItem.getGoodsItemList()) {
					Goods goods = new Goods();
					goods.setCreatedTime(stamp);
					goods.setEffectiveDate(scheduleItem.getEffectiveDate());
					goods.setOperator(approver);
					goods.setPrice(goodsItem.getPrice());
					goods.setProduct(goodsItem.getProduct());
					goods.setQuantity(goodsItem.getQuantity());
					goods.setSchedule(schedule);
					goods.setShop(schedule.getShop());
					scheduleDao.save(goods);
				}
			}
		} // 审批通过
		else if (FinalValue.ScheduleState.APPROVE_FAILED == approveResult) {
			scheduleDao.update(schedule);
		} // 审批不通过
		return ResultMessage.SUCCESS;
	}
}
