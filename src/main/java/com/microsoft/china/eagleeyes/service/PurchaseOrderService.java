package com.microsoft.china.eagleeyes.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.microsoft.china.eagleeyes.entity.PurchaseOrder;

public interface PurchaseOrderService {

	List<PurchaseOrder> upload(MultipartFile poFile) throws IOException;

	List<PurchaseOrder> findAll();

	void share();

}
