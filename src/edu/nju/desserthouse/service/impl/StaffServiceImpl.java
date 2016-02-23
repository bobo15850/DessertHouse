package edu.nju.desserthouse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nju.desserthouse.dao.ShopDao;
import edu.nju.desserthouse.dao.UserDao;
import edu.nju.desserthouse.model.Shop;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.StaffService;
import edu.nju.desserthouse.service.UserService;
import edu.nju.desserthouse.util.ResultMessage;

@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	private ShopDao shopDao;
	@Autowired
	private UserService userService;
	@Autowired
	private UserDao userDao;

	@Override
	public ResultMessage addStaff(User staff, int shopId) {
		Shop shop = shopDao.get(Shop.class, shopId);
		staff.setShop(shop);
		return userService.registerUser(staff);
	}

	@Override
	public List<User> getAllStaffs() {
		return userDao.getAllStaffs();
	}

	@Override
	public ResultMessage modifyStaff(User staff, int shopId) {
		Shop shop = shopDao.get(Shop.class, shopId);
		staff.setShop(shop);
		return userDao.update(staff);
	}

	@Override
	public ResultMessage deleteStaff(int staffId) {
		return userDao.delete(User.class, staffId);
	}
}
