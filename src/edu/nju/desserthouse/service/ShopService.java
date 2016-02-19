package edu.nju.desserthouse.service;

import java.util.List;

import edu.nju.desserthouse.model.Shop;
import edu.nju.desserthouse.util.ResultMessage;

public interface ShopService {
	public ResultMessage addShop(Shop shop, String regionId);// 添加分店

	public List<Shop> getAllShops();// 得到所有分店

	public Shop getShopById(int id);// 根据id得到shop
}
