package edu.nju.desserthouse.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.Product;
import edu.nju.desserthouse.service.ProductService;

/*
 * 总店管理员网页头链接action（只有两个分标签页）
 */
public class HeadWaiterHeadAction extends BaseAction {
	private static final long serialVersionUID = -8410891153722283062L;
	@Autowired
	private ProductService productService;

	@Action(
			value = "schedule",
			results = { @Result(name = SUCCESS, location = "/page/schedule/schedule.jsp"),
					@Result(name = INPUT, location = "/page/user/login.jsp") })
	public String schedule() {
		return SUCCESS;
	}// 产品计划管理

	@Action(
			value = "product",
			results = { @Result(name = SUCCESS, location = "/page/product/product.jsp"),
					@Result(name = INPUT, location = "/page/user/login.jsp") })
	public String product() {
		List<Product> products = productService.getAllProducts();
		request.setAttribute("products", products);
		return SUCCESS;
	}// 产品管理
}
