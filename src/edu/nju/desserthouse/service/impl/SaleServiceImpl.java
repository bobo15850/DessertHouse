package edu.nju.desserthouse.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nju.desserthouse.dao.GoodsDao;
import edu.nju.desserthouse.dao.ShopDao;
import edu.nju.desserthouse.model.Goods;
import edu.nju.desserthouse.model.Shop;
import edu.nju.desserthouse.service.SaleService;

@Service
public class SaleServiceImpl implements SaleService {
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private ShopDao shopDao;

	@Override
	public List<Goods> getTodayShopGoods(int shopId) {
		Date date = new Date(System.currentTimeMillis());
		Shop shop = shopDao.get(Shop.class, shopId);
		String[] columns = { "effectiveDate", "shop" };
		Object[] values = { date, shop };
		List<Goods> goodsList = goodsDao.findByColumns(Goods.class, columns, values);
		return goodsList;
	}

}
