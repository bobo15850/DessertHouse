package edu.nju.desserthouse.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * 产品销售计划管理
 */
@Entity
@Table(name = "scheduleGoodsItem")
public class ScheduleGoodsItem implements Serializable {
	private static final long serialVersionUID = 275293963457462745L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;// 逻辑主键
	@ManyToOne
	@JoinColumn
	private ScheduleRecord scheduleRecord;// 所属的产品计划
	@ManyToOne
	@JoinColumn
	private Product product;// 产品
	private int quantity;// 数量
	private double price;// 单价

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ScheduleRecord getScheduleRecord() {
		return scheduleRecord;
	}

	public void setScheduleRecord(ScheduleRecord scheduleRecord) {
		this.scheduleRecord = scheduleRecord;
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

}
