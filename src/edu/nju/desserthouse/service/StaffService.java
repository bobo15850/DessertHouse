package edu.nju.desserthouse.service;

import java.util.List;

import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.util.ResultMessage;

public interface StaffService {
	public ResultMessage addStaff(User staff, int shopId);

	public ResultMessage modifyStaff(User staff, int shopId);

	public List<User> getAllStaffs();

	public ResultMessage deleteStaff(int staffId);
}
