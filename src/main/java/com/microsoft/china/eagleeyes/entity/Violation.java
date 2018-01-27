package com.microsoft.china.eagleeyes.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "violation")
public class Violation extends BaseEntity {

	private static final long serialVersionUID = -6125838083291508860L;
	
	@Column(name = "report_year")
	private String reportYear;
	
	@Column(name = "report_month")
	private String reportMonth;
	
	@Column(name = "po_number")
	private String poNumber;
	
	@Column(name = "po_submitter")
	private String poSubmitter;
	
	@Column(name = "po_owner")
	private String poOwner;
	
	@Column(name = "vendor_name")
	private String vendorName;
	
	@Column(name = "vendor_id")
	private String vendorId;
	
	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(columnDefinition = "TEXT")
	private String comments;
	
	@Column(name = "testing_result")
	private String testingResult;

	public String getReportYear() {
		return reportYear;
	}

	public void setReportYear(String reportYear) {
		this.reportYear = reportYear;
	}

	public String getReportMonth() {
		return reportMonth;
	}

	public void setReportMonth(String reportMonth) {
		this.reportMonth = reportMonth;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getPoSubmitter() {
		return poSubmitter;
	}

	public void setPoSubmitter(String poSubmitter) {
		this.poSubmitter = poSubmitter;
	}

	public String getPoOwner() {
		return poOwner;
	}

	public void setPoOwner(String poOwner) {
		this.poOwner = poOwner;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getTestingResult() {
		return testingResult;
	}

	public void setTestingResult(String testingResult) {
		this.testingResult = testingResult;
	}

	@Override
	public String toString() {
		return "Violation [reportYear=" + reportYear + ", reportMonth=" + reportMonth + ", poNumber=" + poNumber
				+ ", poSubmitter=" + poSubmitter + ", poOwner=" + poOwner + ", vendorName=" + vendorName + ", vendorId="
				+ vendorId + ", comments=" + comments + ", testingResult=" + testingResult + ", id=" + id
				+ ", createTime=" + createTime + ", lastModified=" + lastModified + "]";
	}
	
}
