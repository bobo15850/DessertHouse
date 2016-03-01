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
	private Goods goods;// 商品
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

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((goods == null) ? 0 : goods.hashCode());
		result = prime * result + ((salesRecord == null) ? 0 : salesRecord.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		SalesGoodsItem other = (SalesGoodsItem) obj;
		if (goods == null) {
			if (other.goods != null) return false;
		}
		else if (!goods.equals(other.goods)) return false;
		if (salesRecord == null) {
			if (other.salesRecord != null) return false;
		}
		else if (!salesRecord.equals(other.salesRecord)) return false;
		return true;
	}

}
