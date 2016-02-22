package edu.nju.desserthouse.service;

import java.util.List;

import edu.nju.desserthouse.model.Shop;
import edu.nju.desserthouse.util.ResultMessage;

public interface ShopService {
	public ResultMessage addShop(Shop shop, int regionId);// 添加分店

	public List<Shop> getAllShops();// 得到所有分店

	public Shop getShopById(int id);// 根据id得到shop

	public ResultMessage modifyShop(Shop shop, int regionId);// 更新分店

	public ResultMessage deleteShop(int id);// 删除店铺

	public List<Shop> getShopesByCountyId(int countyId);// 通过县区的编号查找县区里的店铺
}
