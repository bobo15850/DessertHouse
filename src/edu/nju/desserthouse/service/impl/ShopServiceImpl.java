package edu.nju.desserthouse.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nju.desserthouse.dao.RegionDao;
import edu.nju.desserthouse.dao.ShopDao;
import edu.nju.desserthouse.model.Region;
import edu.nju.desserthouse.model.Shop;
import edu.nju.desserthouse.service.ShopService;
import edu.nju.desserthouse.util.ResultMessage;

@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private RegionDao regionDao;

	@Override
	public ResultMessage addShop(Shop shop, int regionId) {
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		shop.setCreatedTime(stamp);
		Region region = regionDao.get(Region.class, regionId);
		shop.setRegion(region);
		return shopDao.save(shop);
	}

	@Override
	public List<Shop> getAllShops() {
		List<Shop> shops = shopDao.getAllList(Shop.class);
		return shops;
	}

	@Override
	public Shop getShopById(int id) {
		return shopDao.get(Shop.class, id);
	}

	@Override
	public ResultMessage modifyShop(Shop shop, int regionId) {
		Region region = regionDao.get(Region.class, regionId);
		shop.setRegion(region);
		return shopDao.update(shop);
	}

	@Override
	public ResultMessage deleteShop(int id) {
		return shopDao.delete(Shop.class, id);
	}

}
