package edu.nju.desserthouse.model;

import java.io.Serializable;
import java.sql.Date;
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

@Entity
@Table(name = "bookRecord")
public class BookRecord implements Serializable {
	private static final long serialVersionUID = -5104044133348382506L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;// 主键
	private Date targetDate;// 预定的商品日期
	private Timestamp createdTime;// 预定时间
	@ManyToOne
	@JoinColumn
	private Shop shop;// 店铺
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private User customer;// 顾客
	@OneToMany(mappedBy = "bookRecord", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<BookGoodsItem> goodsItemList;
	private double rawMoney;// 商品总价
	private double realMoney;// 实际付款金额
	private int state;// 预定订单状态

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
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

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public List<BookGoodsItem> getGoodsItemList() {
		return goodsItemList;
	}

	public void setGoodsItemList(List<BookGoodsItem> goodsItemList) {
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
