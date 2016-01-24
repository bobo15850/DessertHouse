package edu.nju.desserthouse.action;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.UserManageService;

@Repository
public class RegisterAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private UserManageService userService;

	private User user;

	public String execute() throws ServletException, IOException {
		if (userService == null)
			System.out.println("user service null");
		// String test=userService.test();

		String message = "";
		int year = 0;
		int month = 0;
		int day = 0;
		try {
			year = Integer.valueOf(request.getParameter("year")).intValue();
			month = Integer.valueOf(request.getParameter("month")).intValue() - 1;
			day = Integer.valueOf(request.getParameter("day")).intValue();
		} catch (Exception e) {
			message += "Birthday must be a Integer!<br>";
			// userService.sentMessage(message,request);
			request.setAttribute("mess", message);
			return INPUT;
		}
		if (request.getParameter("passwordOne").equals(request.getParameter("passwordTwo"))) {
			user.setPassword(request.getParameter("passwordOne"));
		}
		else {
			message += "Password not match!<br>";
			// userService.sentMessage(message,request);
			return INPUT;
		}
		System.out.println(user.getUserid() + " user execute");
		// User user=new User();
		user.setIdByDate();
		user.setAccount(500000);
		// user.setBankid(this.request().getParameter("bankid"));
		// user.setEmail(this.request().getParameter("email"));
		// user.setName(this.request().getParameter("name"));
		// System.out.println(req.getParameter("name"));
		// user.setPasswordOne(this.request().getParameter("passwordOne"));
		// user.setPasswordTwo(this.request().getParameter("passwordTwo"));
		// user.setPhone(this.request().getParameter("phone"));
		// user.setUserid(this.request().getParameter("userid"));
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		Date date = new Date(calendar.getTimeInMillis());
		user.setBirthday(date);

		userService.registerUser(user);
		/*
		 * if((message=userService.registerUser(user))!= null){
		 * userService.sentErrorMessage(message, request); return INPUT; } else{
		 */
		session.put("user", user);
		return SUCCESS;
		// }

	}

	/*
	 * public void setUserService(UserManageService userService) {
	 * this.userService = userService; if (userService==null
	 * )System.out.println("user service null"); } public UserManageService
	 * getUserService() { return userService; }
	 */

	public void setUser(User user) {
		this.user = user;
		System.out.println(user.getUserid() + "user");
	}

	public User getUser() {
		if (user == null)
			System.out.println("user null\n");
		return user;
	}

}
