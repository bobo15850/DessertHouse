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
 * 产品销售计划管理一个店铺一天中的一个商品
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
	private ScheduleItem scheduleItem;// 所属的产品计划
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

	public ScheduleItem getScheduleItem() {
		return scheduleItem;
	}

	public void setScheduleItem(ScheduleItem scheduleItem) {
		this.scheduleItem = scheduleItem;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((scheduleItem == null) ? 0 : scheduleItem.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		ScheduleGoodsItem other = (ScheduleGoodsItem) obj;
		if (product == null) {
			if (other.product != null) return false;
		}
		else if (!product.equals(other.product)) return false;
		if (scheduleItem == null) {
			if (other.scheduleItem != null) return false;
		}
		else if (!scheduleItem.equals(other.scheduleItem)) return false;
		return true;
	}
}
