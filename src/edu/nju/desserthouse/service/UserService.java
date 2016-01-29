package edu.nju.desserthouse.service;

import edu.nju.desserthouse.model.User;

public interface UserService {
	public String registerUser(User user);

	public User findUserByName(String username, String password);

	public User findUserByPhoneNumer(String phonenumber, String password);

	public User findUserByCardId(String cardId, String password);
}
