package edu.nju.desserthouse.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * 充值记录
 */
@Entity
@Table(name = "rechargeRecord")
public class RechargeRecord implements Serializable {
	private static final long serialVersionUID = -1935427140292344408L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;// 主键
	private double money;// 充值金额
	private Timestamp createdTime;// 充值时间
	@ManyToOne
	@JoinColumn
	private User user;// 会员

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdTime == null) ? 0 : createdTime.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		RechargeRecord other = (RechargeRecord) obj;
		if (createdTime == null) {
			if (other.createdTime != null) return false;
		}
		else if (!createdTime.equals(other.createdTime)) return false;
		if (user == null) {
			if (other.user != null) return false;
		}
		else if (!user.equals(other.user)) return false;
		return true;
	}

}
