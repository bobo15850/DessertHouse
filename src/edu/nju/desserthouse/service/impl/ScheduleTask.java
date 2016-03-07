package edu.nju.desserthouse.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import edu.nju.desserthouse.dao.BookDao;
import edu.nju.desserthouse.dao.GoodsDao;
import edu.nju.desserthouse.dao.UserDao;
import edu.nju.desserthouse.model.BookGoodsItem;
import edu.nju.desserthouse.model.BookRecord;
import edu.nju.desserthouse.model.Goods;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.util.FinalValue;

//定时计划任务
@Component
public class ScheduleTask {
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private BookDao bookDao;
	@Autowired
	private UserDao userDao;

	@Scheduled(cron = "59 59 23 * * ?") // 每天最后一秒实现
	public void dailyInventory() {
		Date today = new Date(System.currentTimeMillis());
		this.autoConfirmBook(today);
		Date tomorrow = new Date(today.getTime() + 12 * 60 * 60 * 1000);
		this.autoCancleBook(tomorrow);
	}// 每日盘点

	public void autoConfirmBook(Date date) {
		String[] columns = { "targetDate", "state" };
		Object[] values = { date, FinalValue.BookState.PAY };
		List<BookRecord> bookRecords = bookDao.findByColumns(BookRecord.class, columns, values);
		for (int i = 0; i < bookRecords.size(); i++) {
			BookRecord bookRecord = bookRecords.get(i);
			User user = bookRecord.getCustomer();
			int point = (int) (bookRecord.getRealMoney() / 10);
			user.setPoint(point + user.getPoint());
			userDao.update(user);
			bookRecord.setState(FinalValue.BookState.FINISH);
			bookDao.update(bookRecord);
		}
	}// 将当天收获的预定订单确认收货

	public void autoCancleBook(Date date) {
		String[] columns = { "targetDate", "state" };
		Object[] values = { date, FinalValue.BookState.NOT_PAY };
		List<BookRecord> bookRecords = bookDao.findByColumns(BookRecord.class, columns, values);
		for (int i = 0; i < bookRecords.size(); i++) {
			BookRecord bookRecord = bookRecords.get(i);
			for (int j = 0; j < bookRecord.getGoodsItemList().size(); j++) {
				BookGoodsItem goodsItem = bookRecord.getGoodsItemList().get(j);
				Goods goods = goodsItem.getGoods();
				goods.setQuantity(goods.getQuantity() + goodsItem.getQuantity());
				goodsDao.update(goods);
			}
			bookRecord.setState(FinalValue.BookState.CANCLE);
			bookDao.update(bookRecord);
		}
	}// 将取货日期到来前未付款的预定订单全部取消
}
