package edu.nju.desserthouse.service.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nju.desserthouse.dao.GoodsDao;
import edu.nju.desserthouse.dao.SaleDao;
import edu.nju.desserthouse.dao.ShopDao;
import edu.nju.desserthouse.dao.UserDao;
import edu.nju.desserthouse.model.Goods;
import edu.nju.desserthouse.model.SalesRecord;
import edu.nju.desserthouse.model.Shop;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.SaleService;
import edu.nju.desserthouse.util.FinalValue;
import edu.nju.desserthouse.util.ResultMessage;

@Service
public class SaleServiceImpl implements SaleService {
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private SaleDao saleDao;

	@Override
	public List<Goods> getTodayShopGoods(int shopId) {
		Date date = new Date(System.currentTimeMillis());
		Shop shop = shopDao.get(Shop.class, shopId);
		String[] columns = { "effectiveDate", "shop" };
		Object[] values = { date, shop };
		List<Goods> goodsList = goodsDao.findByColumns(Goods.class, columns, values);
		return goodsList;
	}

	@Override
	public ResultMessage fillOrder(SalesRecord order, List<Integer> goodsIdList, int shopId, int operatorId,
			String identity) {
		for (int i = 0; i < goodsIdList.size(); i++) {
			int goodsId = goodsIdList.get(i);
			final Goods goods = goodsDao.get(Goods.class, goodsId);
			order.getGoodsItemList().get(i).setGoods(goods);
			order.getGoodsItemList().get(i).setSalesRecord(order);
		}
		Shop shop = shopDao.get(Shop.class, shopId);
		User operator = userDao.get(User.class, operatorId);
		order.setShop(shop);
		order.setOperator(operator);
		order.setCreatedTime(new Timestamp(System.currentTimeMillis()));
		if (identity != null) {
			List<User> userList = null;
			if (identity.length() == 7) {
				userList = userDao.findByColumns(User.class, new String[] { "cardId" }, new Object[] { identity });
			}
			else if (identity.length() == 11) {
				userList = userDao.findByColumns(User.class, new String[] { "phonenumber" }, new Object[] { identity });
			}
			if (userList != null && userList.size() != 0) {
				order.setCustomer(userList.get(0));
			}
		}
		double rawMoney = 0;
		for (int i = 0; i < order.getGoodsItemList().size(); i++) {
			rawMoney += order.getGoodsItemList().get(i).getMoney();
		}
		order.setRawMoney(rawMoney);
		if (order.getCustomer() == null) {
			order.setRealMoney(rawMoney);
		}
		else {
			User customer = order.getCustomer();
			double discount = FinalValue.UserLevel.getDiscount(customer.getLevel());
			order.setRealMoney(rawMoney * discount);
			if (customer.getBalance() < order.getRealMoney()) {
				return ResultMessage.CANNOT_AFFORD;
			}
		}
		return ResultMessage.SUCCESS;
	}// 将订单填写完整

	@Override
	public ResultMessage addSaleRecord(SalesRecord saleRecord) {
		if (saleRecord.getCustomer() != null) {
			saleRecord.getCustomer()
					.setConsumption(saleRecord.getRealMoney() + saleRecord.getCustomer().getConsumption());// 修改消费
			if (saleRecord.getRealMoney() < saleRecord.getCustomer().getBalance()) {
				saleRecord.getCustomer().setBalance(saleRecord.getCustomer().getBalance() - saleRecord.getRealMoney());
			} // 余额够，要修改用户余额
			double tot = saleRecord.getCustomer().getBalance() + saleRecord.getCustomer().getConsumption();
			double[] levelBase = { FinalValue.UserLevel.getBaseLine(FinalValue.UserLevel.BASIC_MEMBER),
					FinalValue.UserLevel.getBaseLine(FinalValue.UserLevel.ADVANCED_MENBER),
					FinalValue.UserLevel.getBaseLine(FinalValue.UserLevel.VIP) };
			for (int i = levelBase.length - 1; i >= 0; i--) {
				if (tot >= levelBase[i]) {
					saleRecord.getCustomer().setLevel(i);
					break;
				}
			} // 修改用户等级
			userDao.update(saleRecord.getCustomer());
		} // 有用户，要修改用户消费，判断和修改用户等级
		saleDao.save(saleRecord);
		for (int i = 0; i < saleRecord.getGoodsItemList().size(); i++) {
			Goods goods = saleRecord.getGoodsItemList().get(i).getGoods();
			goods.setQuantity(goods.getQuantity() - saleRecord.getGoodsItemList().get(i).getQuantity());
			goodsDao.update(saleRecord.getGoodsItemList().get(i).getGoods());
		} // 改变库存
		return ResultMessage.SUCCESS;
	}// 添加销售记录，需要减少库存,设置会员信息
}
