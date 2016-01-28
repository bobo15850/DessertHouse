package edu.nju.desserthouse.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * 地区，用来表示地址，精确到县区，采取树状结构
 */
@Entity
@Table(name = "region")
public class Region implements Serializable {
	private static final long serialVersionUID = 1393699847608665019L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;// 主键
	@Column(name = "parent_id")
	private int parentId;// 上一级的Id
	private String name;// 名称
	private int type;// 类型

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
