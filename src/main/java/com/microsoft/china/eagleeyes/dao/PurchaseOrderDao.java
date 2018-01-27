package com.microsoft.china.eagleeyes.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.microsoft.china.eagleeyes.entity.PurchaseOrder;

public interface PurchaseOrderDao extends JpaRepository<PurchaseOrder, Integer>, PurchaseOrderDaoPlus {
	
	@Transactional
	@Modifying
	@Query("delete from PurchaseOrder")
	void deleteAll();

}
