package edu.nju.desserthouse.dao.impl;

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
		try {
			baseDao.save(user);
			System.out.println("ok");
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@Override
	public User findUserByName(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByPhoneNumer(String phonenumber, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByCardId(String cardId, String password) {
		// TODO Auto-generated method stub
		return null;
	}
}
