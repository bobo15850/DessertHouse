package edu.nju.desserthouse.dao.impl;

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

}
