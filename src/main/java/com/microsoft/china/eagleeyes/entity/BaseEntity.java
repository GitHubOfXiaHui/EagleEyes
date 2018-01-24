package com.microsoft.china.eagleeyes.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = -7498519619906190950L;
	
	@Id @GeneratedValue
	protected Integer id;
	
	@Column(name = "create_time")
	@Temporal(TemporalType.TIMESTAMP)
	protected Timestamp createTime;

	@Column(name = "last_modified")
	@Temporal(TemporalType.TIMESTAMP)
	protected Timestamp lastModified;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getLastModified() {
		return lastModified;
	}

	public void setLastModified(Timestamp lastModified) {
		this.lastModified = lastModified;
	}

}
