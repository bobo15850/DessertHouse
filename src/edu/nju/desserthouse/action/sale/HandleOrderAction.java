package edu.nju.desserthouse.action.sale;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.SalesRecord;
import edu.nju.desserthouse.service.SaleService;

public class HandleOrderAction extends BaseAction {
	private static final long serialVersionUID = 2976616305086669674L;
	@Autowired
	private SaleService saleService;

	@Action(
			value = "confirmPay",
			results = { @Result(name = SUCCESS, location = "/sale.action", type = "redirect"),
					@Result(name = ERROR, location = "/page/common/error.jsp") })
	public String confirmPay() {
		SalesRecord saleRecord = (SalesRecord) session.get("order");
		if (saleRecord != null) {
			saleService.addSaleRecord(saleRecord);
			session.remove("order");
			return SUCCESS;
		}
		else {
			return ERROR;
		}
	}

	@Action(value = "canclePay", results = { @Result(name = SUCCESS, location = "/sale.action", type = "redirect") })
	public String canclePay() {
		session.remove("order");
		return SUCCESS;
	}
}
