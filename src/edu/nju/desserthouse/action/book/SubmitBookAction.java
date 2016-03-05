package edu.nju.desserthouse.action.book;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.BookRecord;
import edu.nju.desserthouse.service.BookService;
import edu.nju.desserthouse.util.UserBase;

public class SubmitBookAction extends BaseAction {
	private static final long serialVersionUID = 7531535031035145858L;
	private BookRecord order;
	private int shopId;
	private List<Integer> goodsIdList;
	@Autowired
	private BookService bookService;

	@Override
	@Action(
			value = "submitBook",
			results = { @Result(
					name = SUCCESS,
					location = "/book/viewBookOrder.action?orderId=${order.id}",
					type = "redirect") })
	public String execute() throws Exception {
		UserBase userBase = (UserBase) session.get("userBase");
		bookService.addBookOrder(order, goodsIdList, shopId, userBase.getId());
		return SUCCESS;
	}

	public BookRecord getOrder() {
		return order;
	}

	public void setOrder(BookRecord order) {
		this.order = order;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public List<Integer> getGoodsIdList() {
		return goodsIdList;
	}

	public void setGoodsIdList(List<Integer> goodsIdList) {
		this.goodsIdList = goodsIdList;
	}

}
