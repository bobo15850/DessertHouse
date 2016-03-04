package edu.nju.desserthouse.service;

import java.util.List;

import edu.nju.desserthouse.model.Goods;
import edu.nju.desserthouse.model.SalesRecord;
import edu.nju.desserthouse.util.ResultMessage;

public interface SaleService {
	public List<Goods> getTodayShopGoods(int shopId);

	public ResultMessage fillOrder(SalesRecord order, List<Integer> goodsIdList, int shopId, int operatorId,
			String identity);

	public ResultMessage addSaleRecord(SalesRecord saleRecord);

	public List<SalesRecord> getSalesRecordByUser(int userId);
}
