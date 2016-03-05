package edu.nju.desserthouse.action.sale;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.SalesRecord;
import edu.nju.desserthouse.service.SaleService;

public class ViewOrderAction extends BaseAction {
	private static final long serialVersionUID = 8331793426590003163L;
	private int orderId;
	@Autowired
	private SaleService saleService;

	@Override
	@Action(value = "viewOrder", results = { @Result(name = SUCCESS, location = "/page/sale/detailOrder.jsp") })
	public String execute() throws Exception {
		SalesRecord order = saleService.getOrderById(orderId);
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
