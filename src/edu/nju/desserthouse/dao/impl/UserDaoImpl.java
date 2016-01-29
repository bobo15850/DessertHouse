package edu.nju.desserthouse.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.nju.desserthouse.dao.BaseDao;
import edu.nju.desserthouse.dao.UserDao;
import edu.nju.desserthouse.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private BaseDao baseDao;

	public void save(User user) {
		baseDao.save(user);
	}

	@Override
	public User findUserByUsername(String username, String password) {
		String[] columns = { "username", "password" };
		Object[] values = { username, password };
		List<User> list = baseDao.findByColumns(User.class, columns, values);
		if (list == null || list.size() != 1) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public User findUserByPhoneNumer(String phonenumber, String password) {
		String[] columns = { "phonenumber", "password" };
		Object[] values = { phonenumber, password };
		List<User> list = baseDao.findByColumns(User.class, columns, values);
		if (list == null || list.size() != 1) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public User findUserByCardId(String cardId, String password) {
		String[] columns = { "cardId", "password" };
		Object[] values = { cardId, password };
		List<User> list = baseDao.findByColumns(User.class, columns, values);
		if (list == null || list.size() != 1) {
			return null;
		}
		return list.get(0);
	}
}
