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
 * 计划记录
 */
@Entity
@Table(name = "scheduleRecord")
public class ScheduleRecord implements Serializable {
	private static final long serialVersionUID = 9024583029590825696L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;// 逻辑主键
	private Timestamp createdTime;// 创建时间
	@ManyToOne
	@JoinColumn
	private Shop shop;// 店铺
	private Date effectiveDate;// 生效日期
	@ManyToOne
	@JoinColumn
	private User operator;// 创建者
	private int state;// 计划状态

	@OneToMany(mappedBy = "scheduleRecord")
	private Set<ScheduleGoodsItem> goodsItemSet;// 计划商品集合

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

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
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

	public Set<ScheduleGoodsItem> getGoodsItemSet() {
		return goodsItemSet;
	}

	public void setGoodsItemSet(Set<ScheduleGoodsItem> goodsItemSet) {
		this.goodsItemSet = goodsItemSet;
	}
}
