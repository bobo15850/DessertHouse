package edu.nju.desserthouse.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import edu.nju.desserthouse.dao.UserDao;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.util.FinalValue;

@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	@Override
	public List<User> getAllStaffs() {
		Criteria criteria = super.getSession().createCriteria(User.class);
		criteria.add(Restrictions.or(Restrictions.eq("category", FinalValue.UserCategory.MANAGER),
				Restrictions.eq("category", FinalValue.UserCategory.HEAD_WAITER),
				Restrictions.eq("category", FinalValue.UserCategory.BRANCH_WAITER)));
		@SuppressWarnings("unchecked")
		List<User> staffs = criteria.list();
		return staffs;
	}

	@Override
	public void autoInactiveUser() {
		Timestamp stamp = new Timestamp(System.currentTimeMillis() - 365 * 24 * 60 * 60 * 1000);// 去年此时的时间戳
		Criteria criteria = super.getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("state", FinalValue.UserState.NORMAL)).add(Restrictions.le("createdTime", stamp));
		@SuppressWarnings("unchecked")
		List<User> userList = criteria.list();
		for (int i = 0; i < userList.size(); i++) {
			User user = userList.get(i);
			if (user.getBalance() <= 10) {
				user.setState(FinalValue.UserState.SUSPEND);
				this.getSession().update(user);
			}
		}
	}

	public void autoStopUser() {
		Timestamp stamp = new Timestamp(System.currentTimeMillis() - 365 * 24 * 60 * 60 * 1000);// 去年此时的时间戳
		Criteria criteria = super.getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("state", FinalValue.UserState.NORMAL)).add(Restrictions.le("createdTime", stamp));
		@SuppressWarnings("unchecked")
		List<User> userList = criteria.list();
		for (int i = 0; i < userList.size(); i++) {
			User user = userList.get(i);
			if (user.getBalance() <= 10) {
				user.setState(FinalValue.UserState.STOP);
				this.getSession().update(user);
			}
		}
	}
}
