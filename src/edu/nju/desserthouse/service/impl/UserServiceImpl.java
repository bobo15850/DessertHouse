package edu.nju.desserthouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nju.desserthouse.dao.UserDao;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.UserService;
import edu.nju.desserthouse.util.ResultMessage;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public boolean isUserNameExist(String username) {
		User user = userDao.findUserByUsername(username);
		return user == null ? false : true;
	}// 判断用户名是否存在

	public ResultMessage registerUser(User user) {
		return userDao.save(user);
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
