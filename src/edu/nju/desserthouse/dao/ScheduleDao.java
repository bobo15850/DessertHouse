package edu.nju.desserthouse.dao;

import java.sql.Date;

import edu.nju.desserthouse.model.Shop;

public interface ScheduleDao extends BaseDao {
	public Date getLastStartDate(Shop shop);
}
