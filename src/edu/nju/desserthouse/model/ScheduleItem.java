package edu.nju.desserthouse.model;

import java.io.Serializable;
import java.sql.Date;
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

/*
 * 计划记录，一天
 */
@Entity
@Table(name = "scheduleItem")
public class ScheduleItem implements Serializable, Comparable<ScheduleItem> {
	private static final long serialVersionUID = 9024583029590825696L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;// 逻辑主键
	@ManyToOne
	@JoinColumn
	private Schedule schedule;
	private Date effectiveDate;// 生效日期

	@OneToMany(mappedBy = "scheduleItem", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<ScheduleGoodsItem> goodsItemList;

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

	public List<ScheduleGoodsItem> getGoodsItemList() {
		return goodsItemList;
	}

	public void setGoodsItemList(List<ScheduleGoodsItem> goodsItemList) {
		this.goodsItemList = goodsItemList;
	}

	@Override
	public int compareTo(ScheduleItem o) {
		return this.effectiveDate.compareTo(o.effectiveDate);
	}// 时间在前面的产品计划项小

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((effectiveDate == null) ? 0 : effectiveDate.hashCode());
		result = prime * result + ((schedule == null) ? 0 : schedule.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		ScheduleItem other = (ScheduleItem) obj;
		if (effectiveDate == null) {
			if (other.effectiveDate != null) return false;
		}
		else if (!effectiveDate.equals(other.effectiveDate)) return false;
		if (schedule == null) {
			if (other.schedule != null) return false;
		}
		else if (!schedule.equals(other.schedule)) return false;
		return true;
	}

}
