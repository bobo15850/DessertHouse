package edu.nju.desserthouse.action.product;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.Product;
import edu.nju.desserthouse.service.ProductService;

public class SelectProductAction extends BaseAction {
	private static final long serialVersionUID = 2829725653541058667L;
	@Autowired
	private ProductService productService;
	private String selectInfo;

	@Override
	@Action(
			value = "selectProduct",
			results = { @Result(name = SUCCESS, location = "/page/product/product.jsp"),
					@Result(name = INPUT, location = "/page/user/login.jsp") })
	public String execute() throws Exception {
		System.out.println(selectInfo);
		List<Product> products = productService.vagueSelect(selectInfo);
		request.setAttribute("products", products);
		return SUCCESS;
	}

	public String getSelectInfo() {
		return selectInfo;
	}

	public void setSelectInfo(String selectInfo) {
		this.selectInfo = selectInfo;
	}

}
