package edu.nju.desserthouse.action.book;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.service.BookService;
import edu.nju.desserthouse.util.FinalValue;
import edu.nju.desserthouse.util.ResultMessage;

public class ConfirmBookAction extends BaseAction {
	private static final long serialVersionUID = 1287295411859658316L;
	@Autowired
	private BookService bookService;
	private final int orderState = FinalValue.BookState.FINISH;
	private int orderId;

	@Override
	@Action(
			value = "confirmBook",
			results = { @Result(
					name = SUCCESS,
					location = "/consumptionRecord.action?orderState=${orderState}",
					type = "redirect"), @Result(name = "CANNOT_CONFIRM", location = "/page/common/error.jsp") })
	public String execute() throws Exception {
		ResultMessage result = bookService.confirmBook(orderId);
		if (result == ResultMessage.FAILURE) {
			return "CANNOT_CONFIRM";
		}
		return SUCCESS;
	}

	public int getOrderState() {
		return orderState;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

}
