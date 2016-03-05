package edu.nju.desserthouse.action.book;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.BookRecord;
import edu.nju.desserthouse.service.BookService;

public class ViewBookOrderAction extends BaseAction {
	private static final long serialVersionUID = 8127354674769434847L;
	private int orderId;
	@Autowired
	private BookService bookService;

	@Override
	@Action(value = "viewBookOrder", results = { @Result(name = SUCCESS, location = "/page/book/bookOrderDetail.jsp") })
	public String execute() throws Exception {
		BookRecord order = bookService.getBookOrderById(orderId);
		request.setAttribute("order", order);
		return SUCCESS;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

}
