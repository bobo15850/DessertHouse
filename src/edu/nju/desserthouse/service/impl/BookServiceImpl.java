package edu.nju.desserthouse.service.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nju.desserthouse.dao.BookDao;
import edu.nju.desserthouse.dao.GoodsDao;
import edu.nju.desserthouse.dao.ShopDao;
import edu.nju.desserthouse.dao.UserDao;
import edu.nju.desserthouse.model.BookGoodsItem;
import edu.nju.desserthouse.model.BookRecord;
import edu.nju.desserthouse.model.Goods;
import edu.nju.desserthouse.model.Shop;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.BookService;
import edu.nju.desserthouse.util.FinalValue;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private BookDao bookDao;

	@Override
	public List<Goods> getTomorrowGoods(int shopId) {
		Date date = new Date(System.currentTimeMillis() + 1000 * 24 * 60 * 60);
		Shop shop = shopDao.get(Shop.class, shopId);
		String[] columns = { "effectiveDate", "shop" };
		Object[] values = { date, shop };
		List<Goods> goodsList = goodsDao.findByColumns(Goods.class, columns, values);
		return goodsList;
	}

	@Override
	public void addBookOrder(BookRecord order, List<Integer> goodsIdList, int shopId, int userId) {
		for (int i = 0; i < order.getGoodsItemList().size(); i++) {
			BookGoodsItem goodsItem = order.getGoodsItemList().get(i);
			goodsItem.setBookRecord(order);
			Goods goods = goodsDao.get(Goods.class, goodsIdList.get(i));
			goodsItem.setGoods(goods);
			goods.setQuantity(goods.getQuantity() - goodsItem.getQuantity());
		}
		Shop shop = shopDao.get(Shop.class, shopId);
		order.setShop(shop);
		User user = userDao.get(User.class, userId);
		order.setCustomer(user);
		order.setTargetDate(order.getGoodsItemList().get(0).getGoods().getEffectiveDate());
		order.setCreatedTime(new Timestamp(System.currentTimeMillis()));
		order.setState(FinalValue.BookState.NOT_PAY);
		double rawMoney = 0;
		for (int i = 0; i < order.getGoodsItemList().size(); i++) {
			rawMoney += order.getGoodsItemList().get(i).getMoney();
		}
		order.setRawMoney(rawMoney);
		double discount = FinalValue.UserLevel.getDiscount(user.getLevel());
		order.setRealMoney(rawMoney * discount);
		bookDao.save(order);
	}
}
