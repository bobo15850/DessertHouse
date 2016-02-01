package edu.nju.desserthouse.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;

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
		char[] stand = new char[60];
		for (int i = 0; i < 26; i++) {
			stand[i] = (char) ('A' + i);
			stand[i + 26] = (char) ('a' + i);
		}
		for (int i = 52; i < 60; i++) {
			stand[i] = (char) (i - 52 + '0');
		}
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		char[] cardIdArr = { '@', stand[year - 2016], stand[month - 1], stand[day - 1], stand[hour], stand[minute],
				stand[second] };
		String cardId = new String(cardIdArr);
		Timestamp stamp = new Timestamp(calendar.getTimeInMillis());
		user.setCreatedTime(stamp);
		user.setCardId(cardId);
		return userDao.save(user);
	}// 需要得到卡号和时间

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

	@Override
	public ResultMessage renameUser(int id, String newName) {
		User user = userDao.findUserById(id);
		user.setUsername(newName);
		return userDao.save(user);
	}

	@Override
	public User getUserById(int id) {
		return userDao.findUserById(id);
	}

	@Override
	public ResultMessage resetPhone(int id, String phonenumber) {
		User user = userDao.findUserById(id);
		user.setPhonenumber(phonenumber);
		return userDao.save(user);
	}
}
