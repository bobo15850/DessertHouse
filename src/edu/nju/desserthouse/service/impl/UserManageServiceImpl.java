package edu.nju.desserthouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nju.desserthouse.dao.UserDao;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.UserManageService;

@Service
public class UserManageServiceImpl implements UserManageService {

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
	public String test() {
		System.out.println("测试service");
		return null;
	}
}
