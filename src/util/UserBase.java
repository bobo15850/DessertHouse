package util;

import java.io.Serializable;

import edu.nju.desserthouse.model.User;

/*
 * session中保存的用户信息对象，是一个不可变类
 */
public final class UserBase implements Serializable {
	private static final long serialVersionUID = -9100999085225933158L;
	private final int id;// 用户id
	private final String username;// 用户名
	private final int category;// 用户种类

	public UserBase(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.category = user.getCategory();
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public int getCategory() {
		return category;
	}

}
