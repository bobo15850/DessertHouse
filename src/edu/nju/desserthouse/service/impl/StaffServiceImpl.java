package edu.nju.desserthouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nju.desserthouse.dao.ShopDao;
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

	@Override
	public ResultMessage addStaff(User staff, int shopId) {
		Shop shop = shopDao.get(Shop.class, shopId);
		staff.setShop(shop);
		return userService.registerUser(staff);
	}
}
