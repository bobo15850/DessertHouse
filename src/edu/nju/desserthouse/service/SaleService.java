package edu.nju.desserthouse.service;

import java.util.List;

import edu.nju.desserthouse.model.Goods;

public interface SaleService {
	public List<Goods> getTodayShopGoods(int shopId);
}
