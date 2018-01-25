package com.microsoft.china.eagleeyes.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "constant")
public class Constant extends BaseEntity {

	private static final long serialVersionUID = 8142756743152725365L;

	@Column(nullable = false)
	private String rule;
	
	@Column(unique = true, nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Integer value;
	
	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(columnDefinition = "TEXT")
	private String remarks;

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "Constant [rule=" + rule + ", name=" + name + ", value=" + value + ", remarks=" + remarks + ", id=" + id
				+ ", createTime=" + createTime + ", lastModified=" + lastModified + "]";
	}
	
}
