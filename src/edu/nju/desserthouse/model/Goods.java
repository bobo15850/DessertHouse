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
	private ScheduleRecord scheduleRecord;// 所属的计划
	@ManyToOne
	@JoinColumn
	private User operator;// 审批人
	private Timestamp createdTime;// 审批通过时间

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

	public ScheduleRecord getScheduleRecord() {
		return scheduleRecord;
	}

	public void setScheduleRecord(ScheduleRecord scheduleRecord) {
		this.scheduleRecord = scheduleRecord;
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

}
