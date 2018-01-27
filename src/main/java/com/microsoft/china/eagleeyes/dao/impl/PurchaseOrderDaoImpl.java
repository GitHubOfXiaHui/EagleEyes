package com.microsoft.china.eagleeyes.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.microsoft.china.eagleeyes.dao.PurchaseOrderDaoPlus;
import com.microsoft.china.eagleeyes.entity.PurchaseOrder;

public class PurchaseOrderDaoImpl implements PurchaseOrderDaoPlus {
	
	@PersistenceContext
	private EntityManager em;
	
	private int progress = 0;
	private int total = 1;

	@Override
	public int getProgress() {
		return (progress * 100) / total;
	}

	@Transactional
	@Override
	public List<PurchaseOrder> saveBatch(List<PurchaseOrder> pos) {
		List<PurchaseOrder> result = new ArrayList<>();
		
		if (pos == null) {
			return result;
		}
		
		progress = 0;
		total = pos.size();
		for (PurchaseOrder po : pos) {
			em.persist(po);
			if (progress % BATCH == 0) {
				em.flush();
				em.clear();
			}
			progress++;
		}
			
		return result;
	}

	@Transactional
	@Override
	public List<PurchaseOrder> updateBatch(List<PurchaseOrder> pos) {
		List<PurchaseOrder> result = new ArrayList<>();
		
		if (pos == null) {
			return result;
		}
		
		progress = 0;
		total = pos.size();
		for (PurchaseOrder po : pos) {
			em.merge(po);
			if (progress % BATCH == 0) {
				em.flush();
				em.clear();
			}
			progress++;
		}
		
		return result;
	}
	
	private static final int BATCH = 10;

}
