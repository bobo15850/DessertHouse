package edu.nju.desserthouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nju.desserthouse.dao.UserDao;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public String registerUser(User user) {
		String message = null;
		if (userDao == null) {
			System.out.println(" userDao is null");
		}
		userDao.save(user);
		return message;
	}

	@Override
	public User login(String key, String password) {
		User user = null;
		if (key.length() == 11) {
			user = userDao.findUserByPhoneNumer(key, password);
		} // 手机号
		else {
			user = userDao.findUserByUsername(key, password);
		} // 用户名
		return user;
	}
}
