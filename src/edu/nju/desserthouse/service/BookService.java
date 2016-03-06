package edu.nju.desserthouse.service;

import java.sql.Date;
import java.util.List;

import edu.nju.desserthouse.model.BookRecord;
import edu.nju.desserthouse.model.Goods;
import edu.nju.desserthouse.util.ResultMessage;

public interface BookService {
	public List<Goods> getTomorrowGoods(int shopId);

	public void addBookOrder(BookRecord order, List<Integer> goodsIdList, int shopId, int userId);

	public List<BookRecord> getTarStateBookRecordByUser(int userId, int state);

	public BookRecord getBookOrderById(int orderId);

	public ResultMessage payBookOrder(int orderId);

	public ResultMessage cancleBookOrder(int orderId);

	public ResultMessage confirmBook(int orderId);

	public List<Goods> getTrgetDayGoods(int shopId, Date targetDate);
}
