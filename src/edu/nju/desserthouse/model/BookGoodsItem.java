package edu.nju.desserthouse.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bookGoodsItem")
public class BookGoodsItem implements Serializable {
	private static final long serialVersionUID = 7287578530193228876L;
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
	private BookRecord bookRecord;// 对应的预定记录

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

	public BookRecord getBookRecord() {
		return bookRecord;
	}

	public void setBookRecord(BookRecord bookRecord) {
		this.bookRecord = bookRecord;
	}

}
