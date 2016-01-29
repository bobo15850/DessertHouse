package edu.nju.desserthouse.dao;

import edu.nju.desserthouse.model.User;

public interface UserDao {
	public void save(User user);

	public User findUserByName(String username, String password);

	public User findUserByPhoneNumer(String phonenumber, String password);

	public User findUserByCardId(String cardId, String password);
}
