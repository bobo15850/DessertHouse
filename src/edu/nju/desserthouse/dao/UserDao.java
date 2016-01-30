package edu.nju.desserthouse.dao;

import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.util.ResultMessage;

public interface UserDao {
	public ResultMessage save(User user);

	public User findUserByUsername(String username, String password);

	public User findUserByPhoneNumer(String phonenumber, String password);

	public User findUserByCardId(String cardId, String password);

	public User findUserByUsername(String username);
}
