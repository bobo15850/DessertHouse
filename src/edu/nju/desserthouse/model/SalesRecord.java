package edu.nju.desserthouse.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
	@OneToMany(mappedBy = "salesRecord", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<SalesGoodsItem> goodsItemList;
	private double rawMoney;// 商品总价
	private double realMoney;// 实际付款金额
	private int payMode;// 支付方式

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

	public List<SalesGoodsItem> getGoodsItemList() {
		return goodsItemList;
	}

	public void setGoodsItemList(List<SalesGoodsItem> goodsItemList) {
		this.goodsItemList = goodsItemList;
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

	public int getPayMode() {
		return payMode;
	}

	public void setPayMode(int payMode) {
		this.payMode = payMode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdTime == null) ? 0 : createdTime.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((shop == null) ? 0 : shop.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		SalesRecord other = (SalesRecord) obj;
		if (createdTime == null) {
			if (other.createdTime != null) return false;
		}
		else if (!createdTime.equals(other.createdTime)) return false;
		if (customer == null) {
			if (other.customer != null) return false;
		}
		else if (!customer.equals(other.customer)) return false;
		if (shop == null) {
			if (other.shop != null) return false;
		}
		else if (!shop.equals(other.shop)) return false;
		return true;
	}

}
