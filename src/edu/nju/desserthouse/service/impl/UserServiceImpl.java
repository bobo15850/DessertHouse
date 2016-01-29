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
	public User findUserByName(String username, String password) {
		return null;
	}

	@Override
	public User findUserByPhoneNumer(String phonenumber, String password) {
		return null;
	}

	@Override
	public User findUserByCardId(String cardId, String password) {
		return null;
	}

}
