package edu.nju.desserthouse.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * 店铺
 */
@Entity
@Table(name = "shop")
public class Shop implements Serializable {
	private static final long serialVersionUID = 156054524536528245L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;// 逻辑主键
	@Column(unique = true, nullable = true)
	private String shopname;// 店名
	private String phonenumber;// 电话
	private String location;// 详细地址
	private Timestamp createdTime;// 创建时间
	@ManyToOne
	@JoinColumn
	private Region region;// 地区

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}
}
