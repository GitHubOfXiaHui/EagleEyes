package com.microsoft.china.eagleeyes.dao;

import java.util.List;

import com.microsoft.china.eagleeyes.entity.PurchaseOrder;

public interface PurchaseOrderDaoPlus {

	int getProgress();
	
	List<PurchaseOrder> saveBatch(List<PurchaseOrder> pos);
	
	List<PurchaseOrder> updateBatch(List<PurchaseOrder> pos);
	
}
