package edu.nju.desserthouse.action.book;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.service.BookService;
import edu.nju.desserthouse.util.FinalValue;
import edu.nju.desserthouse.util.ResultMessage;

public class CancleBookOrderAction extends BaseAction {
	private static final long serialVersionUID = 3671181293583036320L;
	@Autowired
	private BookService bookService;
	private int orderId;
	private final int orderState = FinalValue.BookState.CANCLE;

	@Override
	@Action(
			value = "cancleBookOrder",
			results = { @Result(
					name = SUCCESS,
					location = "/consumptionRecord.action?orderState=${orderState}",
					type = "redirect"), @Result(name = "CANNOT_CANCLE", location = "/page/common/error.jsp") })
	public String execute() throws Exception {
		ResultMessage result = bookService.cancleBookOrder(orderId);
		if (result == ResultMessage.FAILURE) {
			return "CANNOT_CANCLE";
		}
		return SUCCESS;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrderState() {
		return orderState;
	}
}
