package edu.nju.desserthouse.service;

import java.sql.Date;

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

	public ResultMessage setRepeatDate(int id, String fieldName, Date value);
}
