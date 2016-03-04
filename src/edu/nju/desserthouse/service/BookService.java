package edu.nju.desserthouse.service;

import java.util.List;

import edu.nju.desserthouse.model.BookRecord;
import edu.nju.desserthouse.model.Goods;

public interface BookService {
	public List<Goods> getTomorrowGoods(int shopId);

	public void addBookOrder(BookRecord order, List<Integer> goodsIdList, int shopId, int userId);
}
