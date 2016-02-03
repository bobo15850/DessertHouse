package edu.nju.desserthouse.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

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
		String[] columns = { "username" };
		Object[] values = { username };
		List<User> list = userDao.findByColumns(User.class, columns, values);
		if (list == null || list.size() != 1) {
			return false;
		}
		return true;
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
			String[] columns = { "phonenumber", "password" };
			Object[] values = { key, password };
			List<User> list = userDao.findByColumns(User.class, columns, values);
			if (list == null || list.size() == 1) {
				user = list.get(0);
			}
		} // 手机号
		else {
			String[] columns = { "username", "password" };
			Object[] values = { key, password };
			List<User> list = userDao.findByColumns(User.class, columns, values);
			if (list == null || list.size() == 1) {
				user = list.get(0);
			}
		} // 用户名
		return user;
	}

	@Override
	public User getUserById(int id) {
		User user = userDao.get(User.class, id);
		return user;
	}

	@Override
	public ResultMessage setUniqueStringField(int id, String fieldName, String value) {
		List<User> list = userDao.findByColumns(User.class, new String[] { fieldName }, new Object[] { value });
		if (list == null || list.size() == 0) {
			User user = userDao.get(User.class, id);
			try {
				Method method = User.class.getMethod(
						"set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), String.class);
				method.invoke(user, value);
				return userDao.update(user);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return ResultMessage.FAILURE;
	}// 设置某一个域的值（该域的每一个值是不重复的）

	@Override
	public ResultMessage setRepeatStringField(int id, String fieldName, String value) {
		User user = userDao.get(User.class, id);
		try {
			Method method = User.class
					.getMethod("set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), String.class);
			method.invoke(user, value);
			return userDao.update(user);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAILURE;
	}

	@Override
	public ResultMessage setRepeatIntField(int id, String fieldName, int value) {
		User user = userDao.get(User.class, id);
		try {
			Method method = User.class
					.getMethod("set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), int.class);
			method.invoke(user, value);
			return userDao.update(user);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAILURE;
	}

	@Override
	public ResultMessage setRepeatDate(int id, String fieldName, Date value) {
		User user = userDao.get(User.class, id);
		try {
			Method method = User.class
					.getMethod("set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), Date.class);
			method.invoke(user, value);
			return userDao.update(user);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAILURE;
	}

	@Override
	public ResultMessage updateUser(User user) {
		return userDao.update(user);
	}
}
