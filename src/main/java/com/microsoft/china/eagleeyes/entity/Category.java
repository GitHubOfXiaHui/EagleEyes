package com.microsoft.china.eagleeyes.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category extends BaseEntity {

	private static final long serialVersionUID = -3320757503194099979L;

	@Column(name = "category_group")
	private String categoryGroup;
	
	@Column(name = "category_name", unique = true, nullable = false)
	private String categoryName;
	
	@Column(nullable = false)
	private Double score;
	
	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(columnDefinition = "TEXT")
	private String definition;

	public String getCategoryGroup() {
		return categoryGroup;
	}

	public void setCategoryGroup(String categoryGroup) {
		this.categoryGroup = categoryGroup;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	@Override
	public String toString() {
		return "Category [categoryGroup=" + categoryGroup + ", categoryName=" + categoryName + ", score=" + score
				+ ", definition=" + definition + ", id=" + id + ", createTime=" + createTime + ", lastModified="
				+ lastModified + "]";
	}

}
