package edu.nju.desserthouse.action.book;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.service.BookService;
import edu.nju.desserthouse.util.FinalValue;
import edu.nju.desserthouse.util.ResultMessage;

public class PayBookOrderAction extends BaseAction {
	private static final long serialVersionUID = -1579843590294408448L;
	@Autowired
	private BookService bookService;
	private int orderId;
	private final int orderPay = FinalValue.BookState.PAY;
	private final int orderNotPay = FinalValue.BookState.NOT_PAY;

	@Override
	@Action(
			value = "payBookOrder",
			results = { @Result(
					name = SUCCESS,
					location = "/consumptionRecord.action?orderState=${orderPay}",
					type = "redirect"), @Result(name = "NOT_AFFORD", location = "/page/common/error.jsp") })
	public String execute() throws Exception {
		ResultMessage result = bookService.payBookOrder(orderId);
		if (result == ResultMessage.FAILURE) {
			return "NOT_AFFORD";
		}
		return SUCCESS;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrderPay() {
		return orderPay;
	}

	public int getOrderNotPay() {
		return orderNotPay;
	}

}
