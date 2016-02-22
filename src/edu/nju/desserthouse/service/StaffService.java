package edu.nju.desserthouse.service;

import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.util.ResultMessage;

public interface StaffService {
	public ResultMessage addStaff(User staff, int shopId);
}
