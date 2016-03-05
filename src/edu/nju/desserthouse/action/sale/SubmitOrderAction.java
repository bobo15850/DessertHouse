package edu.nju.desserthouse.action.sale;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.SalesRecord;
import edu.nju.desserthouse.service.SaleService;

public class SubmitOrderAction extends BaseAction {
	@Autowired
	private SaleService saleService;
	private SalesRecord order;
	private List<Integer> goodsIdList;
	private int shopId;
	private int operatorId;
	private String identity;

	private static final long serialVersionUID = -2971228990923894865L;

	@Override
	@Action(value = "submitOrder", results = { @Result(name = SUCCESS, location = "/page/sale/detailOrder.jsp") })
	public String execute() throws Exception {
		saleService.fillOrder(order, goodsIdList, shopId, operatorId, identity);
		session.put("order", order);
		return SUCCESS;
	}

	public SalesRecord getOrder() {
		return order;
	}

	public void setOrder(SalesRecord order) {
		this.order = order;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public int getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public List<Integer> getGoodsIdList() {
		return goodsIdList;
	}

	public void setGoodsIdList(List<Integer> goodsIdList) {
		this.goodsIdList = goodsIdList;
	}

}
