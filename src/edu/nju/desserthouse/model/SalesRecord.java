package edu.nju.desserthouse.model;

import java.io.Serializable;
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
 * 销售记录
 */
@Entity
@Table(name = "salesRecord")
public class SalesRecord implements Serializable {
	private static final long serialVersionUID = -5907724634405296176L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;// 主键
	private Timestamp createdTime;// 销售时间
	@ManyToOne
	@JoinColumn
	private Shop shop;// 店铺
	@ManyToOne
	@JoinColumn(name = "operator_id")
	private User operator;// 操作员
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private User customer;// 顾客

	@OneToMany(targetEntity = SalesGoodsItem.class, mappedBy = "salesRecord")
	private Set<SalesGoodsItem> goodsItemSet;// 销售商品集合

	private double rawMoney;// 商品总价

	private double realMoney;// 实际付款金额

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

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public Set<SalesGoodsItem> getGoodsItemSet() {
		return goodsItemSet;
	}

	public void setGoodsItemSet(Set<SalesGoodsItem> goodsItemSet) {
		this.goodsItemSet = goodsItemSet;
	}

	public double getRawMoney() {
		return rawMoney;
	}

	public void setRawMoney(double rawMoney) {
		this.rawMoney = rawMoney;
	}

	public double getRealMoney() {
		return realMoney;
	}

	public void setRealMoney(double realMoney) {
		this.realMoney = realMoney;
	}
}
