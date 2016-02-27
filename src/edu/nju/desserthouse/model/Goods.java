package edu.nju.desserthouse.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * 商品表<br>总经理审批完成之后，商品计划自动加入商品表，只有商品表中的商品可以进行销售和预定
 */
@Entity
@Table(name = "goods")
public class Goods implements Serializable {
	private static final long serialVersionUID = 7186333837346188729L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;// 逻辑主键
	@ManyToOne
	@JoinColumn
	private Shop shop;// 售卖店铺
	private Date effectiveDate;// 有效时间
	@ManyToOne
	@JoinColumn
	private Product product;// 产品
	private int quantity;// 剩余数量
	private double price;// 单价
	@ManyToOne
	@JoinColumn
	private User operator;// 审批人，审批人审批之后才会添加一条goods
	private Timestamp createdTime;// 审批通过时间
	@ManyToOne
	@JoinColumn
	private Schedule schedule;// 通过审批的产品计划，从而创建的goods

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public User getOperator() {
		return operator;
	}

	public void setOperator(User operator) {
		this.operator = operator;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((effectiveDate == null) ? 0 : effectiveDate.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((shop == null) ? 0 : shop.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Goods other = (Goods) obj;
		if (effectiveDate == null) {
			if (other.effectiveDate != null) return false;
		}
		else if (!effectiveDate.equals(other.effectiveDate)) return false;
		if (product == null) {
			if (other.product != null) return false;
		}
		else if (!product.equals(other.product)) return false;
		if (shop == null) {
			if (other.shop != null) return false;
		}
		else if (!shop.equals(other.shop)) return false;
		return true;
	}

}
