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
import edu.nju.desserthouse.util.ResultMessage;

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

	@Override
	public List<BookRecord> getTarStateBookRecordByUser(int userId, int state) {
		User user = userDao.get(User.class, userId);
		String[] columns = new String[] { "customer", "state" };
		Object[] values = new Object[] { user, state };
		List<BookRecord> bookRecords = bookDao.findByColumns(BookRecord.class, columns, values);
		return bookRecords;
	}

	@Override
	public BookRecord getBookOrderById(int orderId) {
		return bookDao.get(BookRecord.class, orderId);
	}

	@Override
	public ResultMessage payBookOrder(int orderId) {
		BookRecord order = bookDao.get(BookRecord.class, orderId);
		User user = order.getCustomer();
		if (order.getRealMoney() <= user.getBalance()) {
			user.setBalance(user.getBalance() - order.getRealMoney());
			user.setConsumption(user.getConsumption() + order.getRealMoney());
			order.setState(FinalValue.BookState.PAY);
			userDao.update(user);
			bookDao.update(order);
			return ResultMessage.SUCCESS;
		} // 判断余额是否够用
		else {
			return ResultMessage.FAILURE;
		}
	}// 支付订单

	@Override
	public ResultMessage cancleBookOrder(int orderId) {

		BookRecord order = bookDao.get(BookRecord.class, orderId);
		Date today = new Date(System.currentTimeMillis());
		Date targetDate = order.getTargetDate();
		if (today.toString().equals(targetDate.toString())) {
			return ResultMessage.FAILURE;// 预定的取货当天无法退货
		}
		if (order.getState() == FinalValue.BookState.PAY) {
			User user = order.getCustomer();
			user.setBalance(user.getBalance() + order.getRealMoney());
			user.setConsumption(user.getConsumption() - order.getRealMoney());
			userDao.update(user);
		}
		for (int i = 0; i < order.getGoodsItemList().size(); i++) {
			BookGoodsItem goodsItem = order.getGoodsItemList().get(i);
			Goods goods = goodsItem.getGoods();
			goods.setQuantity(goods.getQuantity() + goodsItem.getQuantity());// 添加库存
			goodsDao.update(goods);
		}
		order.setState(FinalValue.BookState.CANCLE);
		return ResultMessage.SUCCESS;// 退货成功
	}// 退货 需要修改商品的库存量,以及判断是否已付过款

	@Override
	public ResultMessage confirmBook(int orderId) {
		BookRecord order = bookDao.get(BookRecord.class, orderId);
		Date today = new Date(System.currentTimeMillis());
		if (today.toString().equals(order.getTargetDate().toString())) {
			order.setState(FinalValue.BookState.FINISH);
			return ResultMessage.SUCCESS;
		} // 只能在取货日期当天确认收货，若未确认收货系统自动在改天最后时刻确认收货
		else {
			return ResultMessage.FAILURE;
		}
	}// 完成订单

	@Override
	public List<Goods> getTrgetDayGoods(int shopId, Date targetDate) {
		Shop shop = shopDao.get(Shop.class, shopId);
		String[] columns = { "effectiveDate", "shop" };
		Object[] values = { targetDate, shop };
		List<Goods> goodsList = goodsDao.findByColumns(Goods.class, columns, values);
		return goodsList;
	}// 得到特定日期的商品
}
