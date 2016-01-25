package edu.nju.desserthouse.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.UserManageService;
import ognl.Ognl;
import ognl.OgnlException;

public class UserManageAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Autowired
	private UserManageService userManageService;

	public String login() {
		return SUCCESS;
	}

	public String logining() throws Exception {
		userManageService.test();
		ValueStack stack = ActionContext.getContext().getValueStack();
		User user = new User();
		user.setName("zhangsan");
		stack.push(user);
		Map<String, Object> context = new HashMap<String, Object>();
		User user1 = new User();
		user1.setName("key1name");
		context.put("key1", user1);
		context.put("key2", "2");
		stack.push(context);
		return SUCCESS;
	}

	public void validate() {
		addFieldError("username", "出现错误");
	}
}
