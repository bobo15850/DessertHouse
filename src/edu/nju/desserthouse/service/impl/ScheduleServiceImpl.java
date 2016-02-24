package edu.nju.desserthouse.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nju.desserthouse.dao.ScheduleDao;
import edu.nju.desserthouse.dao.ShopDao;
import edu.nju.desserthouse.model.Schedule;
import edu.nju.desserthouse.model.Shop;
import edu.nju.desserthouse.service.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	@Autowired
	private ScheduleDao scheduleDao;
	@Autowired
	private ShopDao shopDao;

	@Override
	public Date getLastScheduleDate(int shopId) {
		Shop shop = shopDao.get(Shop.class, shopId);
		Date startDate = scheduleDao.getLastStartDate(shop);
		if (startDate != null) {
			long timeLong = startDate.getTime();
			timeLong = ((timeLong / 1000) + 6 * 24 * 60 * 60) * 1000;// 最后一个产品计划开始日期，加上六天的时间，即为产品计划的最后一天
			startDate.setTime(timeLong);
			return startDate;
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
		Date lastScheduleDate = this.getLastScheduleDate(shopId);
		long timeLong = 1000 * (lastScheduleDate.getTime() / 1000 + 24 * 60 * 60);
		return new Date(timeLong);
	}
}
