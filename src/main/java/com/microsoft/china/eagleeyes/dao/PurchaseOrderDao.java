package com.microsoft.china.eagleeyes.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microsoft.china.eagleeyes.entity.PurchaseOrder;

public interface PurchaseOrderDao extends JpaRepository<PurchaseOrder, Integer> {

}
