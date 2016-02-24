package edu.nju.desserthouse.dao.impl;

import java.sql.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import edu.nju.desserthouse.dao.ScheduleDao;
import edu.nju.desserthouse.model.Schedule;
import edu.nju.desserthouse.model.Shop;

@Repository
public class ScheduleDaoImpl extends BaseDaoImpl implements ScheduleDao {

	@Override
	public Date getLastStartDate(Shop shop) {
		Criteria criteria = super.getSession().createCriteria(Schedule.class);
		criteria.add(Restrictions.eq("shop", shop));
		criteria.setProjection(Projections.max("startDate"));
		@SuppressWarnings("unchecked")
		List<Date> dateList = criteria.list();
		return dateList.get(0);
	}
}
