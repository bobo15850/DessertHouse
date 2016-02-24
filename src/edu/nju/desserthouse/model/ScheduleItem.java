package edu.nju.desserthouse.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 * 计划记录，一天
 */
@Entity
@Table(name = "scheduleItem")
public class ScheduleItem implements Serializable {
	private static final long serialVersionUID = 9024583029590825696L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;// 逻辑主键
	@ManyToOne
	@JoinColumn
	private Schedule schedule;
	private Date effectiveDate;// 生效日期
	@OneToMany(mappedBy = "scheduleItem")
	private Set<ScheduleGoodsItem> goodsItemSet;// 计划商品集合

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Set<ScheduleGoodsItem> getGoodsItemSet() {
		return goodsItemSet;
	}

	public void setGoodsItemSet(Set<ScheduleGoodsItem> goodsItemSet) {
		this.goodsItemSet = goodsItemSet;
	}
}
