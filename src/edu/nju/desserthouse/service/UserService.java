package edu.nju.desserthouse.service;

import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.util.ResultMessage;

public interface UserService {
	public boolean isUserNameExist(String username);

	public ResultMessage registerUser(User user);

	public User login(String key, String password);
}
