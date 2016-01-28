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
 * 销售记录的商品列表
 */
@Entity
@Table(name = "salesGoodsItem")
public class SalesGoodsItem implements Serializable {
	private static final long serialVersionUID = 3467858336065014773L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;// 主键
	@ManyToOne
	@JoinColumn
	private Goods good;// 商品
	private int quantity;// 数量
	private double money;// 总价
	@ManyToOne
	@JoinColumn
	private SalesRecord salesRecord;// 对应的销售记录

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Goods getGood() {
		return good;
	}

	public void setGood(Goods good) {
		this.good = good;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public SalesRecord getSalesRecord() {
		return salesRecord;
	}

	public void setSalesRecord(SalesRecord salesRecord) {
		this.salesRecord = salesRecord;
	}
}
