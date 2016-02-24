package edu.nju.desserthouse.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 * 产品计划，一个店铺，一个星期
 */
@Entity
@Table(name = "schedule")
public class Schedule implements Serializable {
	private static final long serialVersionUID = 1056227945001119468L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Timestamp createdTime;// 创建时间
	@ManyToOne
	@JoinColumn
	private Shop shop;// 店铺
	private Date startDate;// 开始日期
	@ManyToOne
	@JoinColumn
	private User operator;// 创建者
	private int state;// 计划状态
	@OneToMany(mappedBy = "schedule")
	private Set<ScheduleItem> scheduleItemSet;
	@ManyToOne
	@JoinColumn
	private User approver;// 审批人

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public User getOperator() {
		return operator;
	}

	public void setOperator(User operator) {
		this.operator = operator;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Set<ScheduleItem> getScheduleItemSet() {
		return scheduleItemSet;
	}

	public void setScheduleItemSet(Set<ScheduleItem> scheduleItemSet) {
		this.scheduleItemSet = scheduleItemSet;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}
