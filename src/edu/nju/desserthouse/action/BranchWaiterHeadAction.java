package edu.nju.desserthouse.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.Goods;
import edu.nju.desserthouse.model.SalesRecord;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.SaleService;
import edu.nju.desserthouse.service.UserService;
import edu.nju.desserthouse.util.UserBase;

/*
 * 分店服务员网页头action
 */
public class BranchWaiterHeadAction extends BaseAction {
	private static final long serialVersionUID = 1556554054395903212L;
	@Autowired
	private UserService userService;
	@Autowired
	private SaleService saleService;

	@Action(
			value = "sale",
			results = { @Result(name = SUCCESS, location = "/page/sale/sale.jsp"),
					@Result(name = INPUT, location = "/page/user/login.jsp") })
	public String sale() {
		UserBase userBase = (UserBase) session.get("userBase");
		User staff = userService.getUserById(userBase.getId());
		request.setAttribute("staff", staff);
		List<Goods> goodsList = saleService.getTodayShopGoods(staff.getShop().getId());
		request.setAttribute("goodsList", goodsList);
		return SUCCESS;
	}// 销售

	@Action(
			value = "viewUserInfo",
			results = { @Result(name = SUCCESS, location = "/page/user/viewUserInfo.jsp"),
					@Result(name = INPUT, location = "/page/user/login.jsp") })
	public String viewUserInfo() {
		String inquireStr = request.getParameter("inquire-input");
		if (inquireStr == null || inquireStr.length() == 0) {
			List<User> customers = userService.getAllCustomers();// 得到所有的顾客
			request.setAttribute("customers", customers);
		}
		else {
			User user = userService.getUserByIdentity(inquireStr);
			request.setAttribute("inquireStr", inquireStr);
			request.setAttribute("user", user);
		} // 等于null
		return SUCCESS;
	}// 查看会员信息

	@Action(
			value = "viewUserRecharge",
			results = { @Result(name = SUCCESS, location = "/page/user/viewUserRecharge.jsp"),
					@Result(name = INPUT, location = "/page/user/login.jsp") })
	public String viewUserRecharge() {
		String inquireStr = request.getParameter("inquire-input");
		if (inquireStr != null) {
			User user = userService.getUserByIdentity(inquireStr);
			request.setAttribute("inquireStr", inquireStr);
			request.setAttribute("user", user);
		}
		return SUCCESS;
	}// 查看会员充值记录

	@Action(
			value = "viewUserConsumption",
			results = { @Result(name = SUCCESS, location = "/page/user/viewUserConsumption.jsp"),
					@Result(name = INPUT, location = "/page/user/login.jsp") })
	public String viewUserConsumption() {
		UserBase staff = (UserBase) session.get("userBase");
		String inquireStr = request.getParameter("inquire-input");
		if (inquireStr == null || inquireStr.length() == 0) {
			List<SalesRecord> orders = saleService.getOrdersByOperator(staff.getId());
			request.setAttribute("orders", orders);
			request.setAttribute("inquireStr", null);
		} // 显示流水记录
		else {
			User user = userService.getUserByIdentity(inquireStr);
			if (user == null) {
				request.setAttribute("orders", null);
			}
			else {
				List<SalesRecord> orders = saleService.getSalesRecordByUser(user.getId());
				request.setAttribute("orders", orders);
			}
			request.setAttribute("inquireStr", inquireStr);
		} // 显示查找到的
		return SUCCESS;
	}// 查看消费记录

}
