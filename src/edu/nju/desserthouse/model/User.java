package edu.nju.desserthouse.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/*
 * 用户，包括会员和其他管理员等
 */
@Entity
@Table(name = "user")
public class User implements Serializable {
	private static final long serialVersionUID = -3205555105736031850L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;// 逻辑主键，自动生成
	@Column(nullable = true, unique = true)
	private String cardId;// 会员卡号
	@Column(nullable = true, unique = true)
	private String username;// 用户名
	private String password;// 密码
	@Column(unique = true)
	private String phonenumber;// 电话，可以不绑定
	@Column(unique = true)
	private String bankId;// 银行卡号，可以没有（即没有激活）
	private String location;// 地址
	private int gender;// 性別
	private int category;// 用户种类
	private int level;// 用户等级
	private int state;// 会员资格状态
	private double balance;// 余额
	private double consumption;// 已消费金额
	private double point;// 现有积分
	private Date birthday;// 出生日期
	private Timestamp createdTime;// 创建日期

	@ManyToOne
	@JoinColumn
	private Region region;// 区域

	@ManyToOne
	@JoinColumn
	private Shop shop;// 所属店铺

	@OneToMany(mappedBy = "user")
	@Cascade(value = { CascadeType.SAVE_UPDATE, CascadeType.DELETE })
	private Set<RechargeRecord> rechargeRecordSet;// 充值记录

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getConsumption() {
		return consumption;
	}

	public void setConsumption(double consumption) {
		this.consumption = consumption;
	}

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
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

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Set<RechargeRecord> getRechargeRecordSet() {
		return rechargeRecordSet;
	}

	public void setRechargeRecordSet(Set<RechargeRecord> rechargeRecordSet) {
		this.rechargeRecordSet = rechargeRecordSet;
	}

}
