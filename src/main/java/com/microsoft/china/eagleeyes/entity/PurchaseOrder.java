package com.microsoft.china.eagleeyes.entity;


import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "purchase_order")
public class PurchaseOrder extends BaseEntity {

	private static final long serialVersionUID = -7152265661681650925L;
	
	@Column(name = "po_number", unique = true, nullable = false)
	private String poNumber;
	
	@Column
	private Double amount;
	
	@Column(name = "invoice_amount")
	private Double invoiceAmount;
	
	@Column(name = "invoice_date_first")
	@Temporal(TemporalType.DATE)
	private Date invoiceDateFirst;
	
	@Column(name = "invoice_date_last")
	@Temporal(TemporalType.DATE)
	private Date invoiceDateLast;
	
	@Column
	private Double count;
	
	@Column
	private String category;
	
	@Column(name = "category_group")
	private String categoryGroup;
	
	@Column(name = "company_code")
	private String companyCode;
	
	@Column(name = "approved_date")
	@Temporal(TemporalType.DATE)
	// create date
	private Date approvedDate;

	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@Column(name = "owner_alias")
	private String ownerAlias;
	
	@Column(name = "owner_name")
	private String ownerName;
	
	@Column(name = "submitter_alias")
	private String submitterAlias;
	
	
	@Column(name = "submitter_name")
	private String submitterName;
	
	
	@Column(name = "supplier_number")
	private String supplierNumber;
	
	
	@Column(name = "supplier_name")
	private String supplierName;
	
	@Column(name = "start_date")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Column(name = "approver_alias")
	private String approverAlias;
	
	@Column(name = "approver_name")
	private String approverName;
	
	@Column(name = "approver_type")
	private String approverType;
	
	@Column
	private String status;
	
	@Column(name = "owner_region")
	private String ownerRegion;
	
	@Column(name = "owner_segment")
	private String ownerSegment;
	
	@Column(name = "owner_GSMO")
	private String ownerGSMO;
	
	/* 计算列 */
	@Column(name = "po_fiscal_year")
	private String poFiscalYear;
	
	@Column(name = "po_fiscal_quarter")
	private String poFiscalQuarter;
	
	@Column(name = "po_month")
	private String poMonth;
	
	@Column(name = "po_index")
	private String poIndex;
	
	@Column(name = "inv_fiscal_year")
	private String invFiscalYear;
	
	@Column(name = "inv_fiscal_quarter")
	private String invFiscalQuarter;
	
	@Column(name = "inv_month")
	private String invMonth;
	
	@Column(name = "atf1_flag")
	@Type(type = "yes_no")
	private Boolean atf1Flag;
	
	@Column(name = "atf2_flag")
	@Type(type = "yes_no")
	private Boolean atf2Flag;
	
	@Column(name = "amount_flag")
	@Type(type = "yes_no")
	private Boolean amountFlag;
	
	@Column(name = "po_split_flag")
	@Type(type = "yes_no")
	private Boolean poSplitFlag;
	
	@Column(name = "risk_flag")
	@Type(type = "yes_no")
	private Boolean riskFlag;
	
	@Column(name = "category_risk")
	private Integer categoryRisk;
	
	@Column(name = "create_date_diff")
	private Integer createDateDiff;
	
	@Column(name = "create_date_risk")
	private Integer createDateRisk;
	
	@Column(name = "payment_date_diff")
	private Integer paymentDateDiff;
	
	@Column(name = "payment_date_risk")
	private Integer paymentDateRisk;
	
	@Column(name = "atf1_diff")
	private Integer atf1Diff;
	
	@Column(name = "atf1_risk")
	private Integer atf1Risk;
	
	@Column(name = "atf2_diff")
	private Integer atf2Diff;
	
	@Column(name = "atf2_risk")
	private Integer atf2Risk;
	
	@Column(name = "owner_fail_risk")
	private Integer ownerFailRisk;
	
	@Column(name = "submitter_fail_risk")
	private Integer submitterFailRisk;
	
	@Column(name = "vendor_fail_risk")
	private Integer vendorFailRisk;
	
	@Column(name = "owner_new_risk")
	private Integer ownerNewRisk;
	
	@Column(name = "submitter_new_risk")
	private Integer submitterNewRisk;
	
	@Column(name = "vendor_new_risk")
	private Integer vendorNewRisk;
	
	@Column(name = "risk_score")
	private Integer riskScore;

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(Double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public Date getInvoiceDateFirst() {
		return invoiceDateFirst;
	}

	public void setInvoiceDateFirst(Date invoiceDateFirst) {
		this.invoiceDateFirst = invoiceDateFirst;
	}

	public Date getInvoiceDateLast() {
		return invoiceDateLast;
	}

	public void setInvoiceDateLast(Date invoiceDateLast) {
		this.invoiceDateLast = invoiceDateLast;
	}

	public Double getCount() {
		return count;
	}

	public void setCount(Double count) {
		this.count = count;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategoryGroup() {
		return categoryGroup;
	}

	public void setCategoryGroup(String categoryGroup) {
		this.categoryGroup = categoryGroup;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOwnerAlias() {
		return ownerAlias;
	}

	public void setOwnerAlias(String ownerAlias) {
		this.ownerAlias = ownerAlias;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getSubmitterAlias() {
		return submitterAlias;
	}

	public void setSubmitterAlias(String submitterAlias) {
		this.submitterAlias = submitterAlias;
	}

	public String getSubmitterName() {
		return submitterName;
	}

	public void setSubmitterName(String submitterName) {
		this.submitterName = submitterName;
	}

	public String getSupplierNumber() {
		return supplierNumber;
	}

	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getApproverAlias() {
		return approverAlias;
	}

	public void setApproverAlias(String approverAlias) {
		this.approverAlias = approverAlias;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	public String getApproverType() {
		return approverType;
	}

	public void setApproverType(String approverType) {
		this.approverType = approverType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOwnerRegion() {
		return ownerRegion;
	}

	public void setOwnerRegion(String ownerRegion) {
		this.ownerRegion = ownerRegion;
	}

	public String getOwnerSegment() {
		return ownerSegment;
	}

	public void setOwnerSegment(String ownerSegment) {
		this.ownerSegment = ownerSegment;
	}

	public String getOwnerGSMO() {
		return ownerGSMO;
	}

	public void setOwnerGSMO(String ownerGSMO) {
		this.ownerGSMO = ownerGSMO;
	}

	public String getPoFiscalYear() {
		return poFiscalYear;
	}

	public void setPoFiscalYear(String poFiscalYear) {
		this.poFiscalYear = poFiscalYear;
	}

	public String getPoFiscalQuarter() {
		return poFiscalQuarter;
	}

	public void setPoFiscalQuarter(String poFiscalQuarter) {
		this.poFiscalQuarter = poFiscalQuarter;
	}

	public String getPoMonth() {
		return poMonth;
	}

	public void setPoMonth(String poMonth) {
		this.poMonth = poMonth;
	}

	public String getPoIndex() {
		return poIndex;
	}

	public void setPoIndex(String poIndex) {
		this.poIndex = poIndex;
	}

	public String getInvFiscalYear() {
		return invFiscalYear;
	}

	public void setInvFiscalYear(String invFiscalYear) {
		this.invFiscalYear = invFiscalYear;
	}

	public String getInvFiscalQuarter() {
		return invFiscalQuarter;
	}

	public void setInvFiscalQuarter(String invFiscalQuarter) {
		this.invFiscalQuarter = invFiscalQuarter;
	}

	public String getInvMonth() {
		return invMonth;
	}

	public void setInvMonth(String invMonth) {
		this.invMonth = invMonth;
	}

	public Boolean getAtf1Flag() {
		return atf1Flag;
	}

	public void setAtf1Flag(Boolean atf1Flag) {
		this.atf1Flag = atf1Flag;
	}

	public Boolean getAtf2Flag() {
		return atf2Flag;
	}

	public void setAtf2Flag(Boolean atf2Flag) {
		this.atf2Flag = atf2Flag;
	}

	public Boolean getAmountFlag() {
		return amountFlag;
	}

	public void setAmountFlag(Boolean amountFlag) {
		this.amountFlag = amountFlag;
	}

	public Boolean getPoSplitFlag() {
		return poSplitFlag;
	}

	public void setPoSplitFlag(Boolean poSplitFlag) {
		this.poSplitFlag = poSplitFlag;
	}

	public Boolean getRiskFlag() {
		return riskFlag;
	}

	public void setRiskFlag(Boolean riskFlag) {
		this.riskFlag = riskFlag;
	}

	public Integer getCategoryRisk() {
		return categoryRisk;
	}

	public void setCategoryRisk(Integer categoryRisk) {
		this.categoryRisk = categoryRisk;
	}

	public Integer getCreateDateDiff() {
		return createDateDiff;
	}

	public void setCreateDateDiff(Integer createDateDiff) {
		this.createDateDiff = createDateDiff;
	}

	public Integer getCreateDateRisk() {
		return createDateRisk;
	}

	public void setCreateDateRisk(Integer createDateRisk) {
		this.createDateRisk = createDateRisk;
	}

	public Integer getPaymentDateDiff() {
		return paymentDateDiff;
	}

	public void setPaymentDateDiff(Integer paymentDateDiff) {
		this.paymentDateDiff = paymentDateDiff;
	}

	public Integer getPaymentDateRisk() {
		return paymentDateRisk;
	}

	public void setPaymentDateRisk(Integer paymentDateRisk) {
		this.paymentDateRisk = paymentDateRisk;
	}

	public Integer getAtf1Diff() {
		return atf1Diff;
	}

	public void setAtf1Diff(Integer atf1Diff) {
		this.atf1Diff = atf1Diff;
	}

	public Integer getAtf1Risk() {
		return atf1Risk;
	}

	public void setAtf1Risk(Integer atf1Risk) {
		this.atf1Risk = atf1Risk;
	}

	public Integer getAtf2Diff() {
		return atf2Diff;
	}

	public void setAtf2Diff(Integer atf2Diff) {
		this.atf2Diff = atf2Diff;
	}

	public Integer getAtf2Risk() {
		return atf2Risk;
	}

	public void setAtf2Risk(Integer atf2Risk) {
		this.atf2Risk = atf2Risk;
	}

	public Integer getOwnerFailRisk() {
		return ownerFailRisk;
	}

	public void setOwnerFailRisk(Integer ownerFailRisk) {
		this.ownerFailRisk = ownerFailRisk;
	}

	public Integer getSubmitterFailRisk() {
		return submitterFailRisk;
	}

	public void setSubmitterFailRisk(Integer submitterFailRisk) {
		this.submitterFailRisk = submitterFailRisk;
	}

	public Integer getVendorFailRisk() {
		return vendorFailRisk;
	}

	public void setVendorFailRisk(Integer vendorFailRisk) {
		this.vendorFailRisk = vendorFailRisk;
	}

	public Integer getOwnerNewRisk() {
		return ownerNewRisk;
	}

	public void setOwnerNewRisk(Integer ownerNewRisk) {
		this.ownerNewRisk = ownerNewRisk;
	}

	public Integer getSubmitterNewRisk() {
		return submitterNewRisk;
	}

	public void setSubmitterNewRisk(Integer submitterNewRisk) {
		this.submitterNewRisk = submitterNewRisk;
	}

	public Integer getVendorNewRisk() {
		return vendorNewRisk;
	}

	public void setVendorNewRisk(Integer vendorNewRisk) {
		this.vendorNewRisk = vendorNewRisk;
	}

	public Integer getRiskScore() {
		return riskScore;
	}

	public void setRiskScore(Integer riskScore) {
		this.riskScore = riskScore;
	}

	@Override
	public String toString() {
		return "PurchaseOrder [poNumber=" + poNumber + ", amount=" + amount + ", invoiceAmount=" + invoiceAmount
				+ ", invoiceDateFirst=" + invoiceDateFirst + ", invoiceDateLast=" + invoiceDateLast + ", count=" + count
				+ ", category=" + category + ", categoryGroup=" + categoryGroup + ", companyCode=" + companyCode
				+ ", approvedDate=" + approvedDate + ", description=" + description + ", ownerAlias=" + ownerAlias
				+ ", ownerName=" + ownerName + ", submitterAlias=" + submitterAlias + ", submitterName=" + submitterName
				+ ", supplierNumber=" + supplierNumber + ", supplierName=" + supplierName + ", startDate=" + startDate
				+ ", approverAlias=" + approverAlias + ", approverName=" + approverName + ", approverType="
				+ approverType + ", status=" + status + ", ownerRegion=" + ownerRegion + ", ownerSegment="
				+ ownerSegment + ", ownerGSMO=" + ownerGSMO + ", poFiscalYear=" + poFiscalYear + ", poFiscalQuarter="
				+ poFiscalQuarter + ", poMonth=" + poMonth + ", poIndex=" + poIndex + ", invFiscalYear=" + invFiscalYear
				+ ", invFiscalQuarter=" + invFiscalQuarter + ", invMonth=" + invMonth + ", atf1Flag=" + atf1Flag
				+ ", atf2Flag=" + atf2Flag + ", amountFlag=" + amountFlag + ", poSplitFlag=" + poSplitFlag
				+ ", riskFlag=" + riskFlag + ", categoryRisk=" + categoryRisk + ", createDateDiff=" + createDateDiff
				+ ", createDateRisk=" + createDateRisk + ", paymentDateDiff=" + paymentDateDiff + ", paymentDateRisk="
				+ paymentDateRisk + ", atf1Diff=" + atf1Diff + ", atf1Risk=" + atf1Risk + ", atf2Diff=" + atf2Diff
				+ ", atf2Risk=" + atf2Risk + ", ownerFailRisk=" + ownerFailRisk + ", submitterFailRisk="
				+ submitterFailRisk + ", vendorFailRisk=" + vendorFailRisk + ", ownerNewRisk=" + ownerNewRisk
				+ ", submitterNewRisk=" + submitterNewRisk + ", vendorNewRisk=" + vendorNewRisk + ", riskScore="
				+ riskScore + ", id=" + id + ", createTime=" + createTime + ", lastModified=" + lastModified + "]";
	}
	
}
