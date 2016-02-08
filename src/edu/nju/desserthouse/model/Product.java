package edu.nju.desserthouse.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * 产品，区别于商品，产品是稳定的，商品是不固定的，每天变化的
 */
@Entity
@Table(name = "product")
public class Product implements Serializable {
	private static final long serialVersionUID = -8439995517403163226L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;// 逻辑主键
	private String goodsname;// 产品名称
	private String info;// 附加信息
	private String picture;// 图片地址
	private Timestamp createdTime;// 创建时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

}
