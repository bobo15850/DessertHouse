package edu.nju.desserthouse.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	@OneToMany(mappedBy = "shop")
	@Basic(fetch = FetchType.LAZY)
	private Set<Schedule> scheduleSet;// 产品计划集合

	@OneToMany(mappedBy = "shop")
	@Basic(fetch = FetchType.LAZY)
	private Set<User> staffSet;// 店铺员工集合

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

	public Set<Schedule> getScheduleSet() {
		return scheduleSet;
	}

	public void setScheduleSet(Set<Schedule> scheduleSet) {
		this.scheduleSet = scheduleSet;
	}

	public Set<User> getStaffSet() {
		return staffSet;
	}

	public void setStaffSet(Set<User> staffSet) {
		this.staffSet = staffSet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Shop other = (Shop) obj;
		if (id != other.id) return false;
		return true;
	}
}
