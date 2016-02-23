package edu.nju.desserthouse.dao;

import java.util.List;

import edu.nju.desserthouse.model.User;

public interface UserDao extends BaseDao {
	public List<User> getAllStaffs();
}
