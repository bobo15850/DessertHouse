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
 * 商品
 */
@Entity
@Table(name = "goods")
public class Goods implements Serializable {
	private static final long serialVersionUID = -7976477553589855792L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;// 逻辑主键
	@ManyToOne
	@JoinColumn
	private Product product;// 产品
	@ManyToOne
	@JoinColumn
	private Shop shop;// 售卖店铺
	private Date effectiveDate;// 有效日期
	private double price;// 单价
	private Timestamp createdTime;// 创建时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

}
