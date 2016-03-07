package edu.nju.desserthouse.service;

import java.sql.Date;
import java.util.List;

import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.util.ResultMessage;

public interface UserService {
	public boolean isUserNameExist(String username);

	public ResultMessage registerUser(User user);

	public User login(String key, String password);

	public User getUserById(int id);

	public ResultMessage setUniqueStringField(int id, String fieldName, String value);

	public ResultMessage setRepeatStringField(int id, String fieldName, String value);

	public ResultMessage setRepeatIntField(int id, String fieldName, int value);

	public ResultMessage setRepeatDate(int id, String fieldName, Date value);// 设置日期

	public ResultMessage setUserRegion(int userId, int regionId);// 设置用户地区

	public User inactive(int id);// 激活

	public User renewal(int id);// 续费

	public User recharge(int id, double amount);// 充值

	public User getUserByIdentity(String identity);// 通过用户名或者电话或者会员卡号查找用户

	public List<User> getAllCustomers();// 得到所有的顾客

	public ResultMessage exchangePoint(int userId, int point);//

}
