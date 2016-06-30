package edu.nju.desserthouse.action.sale;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.dao.UserDao;
import edu.nju.desserthouse.model.SalesRecord;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.SaleService;
import edu.nju.desserthouse.util.FinalValue;

@ParentPackage("json-default")
public class ValidateIdentityAction extends BaseAction{
	@Autowired
	private SaleService saleService;
	@Autowired
	private UserDao userDao;
	private Map<String,String> result;
	private String identity;

	@Action(
			value = "/validateIdentity",
			results = { @Result(name = SUCCESS, type = "json",params={"root","result"})})
	public String execute() {
		User user = null;
		if (identity != null) {
			List<User> userList = null;
			result=new HashMap<String,String>();
			if (identity.length() == 7) {
				userList = userDao.findByColumns(User.class, new String[] { "cardId" }, new Object[] { identity });
			}
			else if (identity.length() == 11) {
				userList = userDao.findByColumns(User.class, new String[] { "phonenumber" }, new Object[] { identity });
			}
			if (userList != null && userList.size() != 0) {
				user=userList.get(0);
				result.put("level", FinalValue.UserLevel.getStrOfUserLevel(user.getLevel()));
				result.put("discount", String.valueOf(FinalValue.UserLevel.getDiscount(user.getLevel())));
			}
			
		}
		return SUCCESS;
	}
	
	public Map<String,String> getResult(){
		return result;
	}
	
	public void setIdentity(String identity){
		this.identity=identity;
	}
	public String getIdentity(){
		return identity;
	}
}
