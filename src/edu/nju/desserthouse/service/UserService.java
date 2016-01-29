package edu.nju.desserthouse.service;

import edu.nju.desserthouse.model.User;

public interface UserService {
	public String registerUser(User user);

	public User login(String key, String password);
}
