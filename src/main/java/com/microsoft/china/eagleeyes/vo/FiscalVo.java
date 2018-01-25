package com.microsoft.china.eagleeyes.vo;

public class FiscalVo {

	private String fiscalYear;
	
	private String fiscalQuarter;
	
	private String month;
	
	public String getIndex() {
		return fiscalYear + fiscalQuarter;
	}

	public String getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public String getFiscalQuarter() {
		return fiscalQuarter;
	}

	public void setFiscalQuarter(String fiscalQuarter) {
		this.fiscalQuarter = fiscalQuarter;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	@Override
	public String toString() {
		return "FiscalVo [fiscalYear=" + fiscalYear + ", fiscalQuarter=" + fiscalQuarter + ", month=" + month + "]";
	}
	
}
