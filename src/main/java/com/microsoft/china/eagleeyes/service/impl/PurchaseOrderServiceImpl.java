package com.microsoft.china.eagleeyes.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.microsoft.china.eagleeyes.dao.PurchaseOrderDao;
import com.microsoft.china.eagleeyes.entity.PurchaseOrder;
import com.microsoft.china.eagleeyes.service.PurchaseOrderService;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
	
	@Autowired
	private PurchaseOrderDao purchaseOrderDao;

	@Override
	public List<PurchaseOrder> upload(MultipartFile po) throws IOException {
		Workbook wb = createWorkbook(po);
		Sheet sheet = wb.getSheet("Sheet1");
		Map<String, Integer> head = getHead(sheet);
		List<PurchaseOrder> body = getBody(sheet, head);
		return body;
	}

	private List<PurchaseOrder> getBody(Sheet sheet, Map<String, Integer> head) {
		int first = sheet.getFirstRowNum();
		int last = sheet.getLastRowNum();
		List<PurchaseOrder> body = new ArrayList<>(last - first - 1);
		for (int i = first + 1; i <= last; i++) {
			Row row = sheet.getRow(i);
			PurchaseOrder po = new PurchaseOrder();
			po.setNumber(Optional.ofNullable(row.getCell(head.get("PO Number"))).map(Cell::getStringCellValue).orElse(null));
			// 删除订单编号为空的订单
			if (po.getNumber() == null) {
				continue;
			}
			po.setAmount(row.getCell(head.get("POAmountTotal RMB")).getNumericCellValue());
			po.setInvoiceAmount(row.getCell(head.get("InvoiceAmountTotal RMB")).getNumericCellValue());
			po.setInvoiceDateFirst(row.getCell(head.get("InvoiceDateFirst")).getDateCellValue());
			po.setInvoiceDateLast(row.getCell(head.get("InvoiceDateLast")).getDateCellValue());
			po.setCount(row.getCell(head.get("PO.Count")).getNumericCellValue());
			po.setCategory(row.getCell(head.get("PO.Category")).getStringCellValue());
			po.setCategoryGroup(row.getCell(head.get("PO.Category Group")).getStringCellValue());
			po.setCompanyCode(row.getCell(head.get("Company Code")).getNumericCellValue());
			po.setApprovedDate(row.getCell(head.get("PO.Created Date_ApprovedDate")).getDateCellValue());
			po.setDescription(row.getCell(head.get("PO.Description")).getStringCellValue());
			po.setOwnerAlias(row.getCell(head.get("PO.Owner Alias")).getStringCellValue());
			po.setOwnerName(row.getCell(head.get("PO.Owner Name")).getStringCellValue());
			po.setSubmitterAlias(row.getCell(head.get("PO.Submitted By Alias")).getStringCellValue());
			po.setSubmitterName(row.getCell(head.get("PO.Submitted By Name")).getStringCellValue());
			po.setSupplierNumber(row.getCell(head.get("PO.Supplier Number")).getNumericCellValue());
			po.setSupplierName(row.getCell(head.get("PO.Supplier Name")).getStringCellValue());
			po.setStartDate(row.getCell(head.get("PO.Start Date")).getDateCellValue());
			po.setApproverAlias(row.getCell(head.get("PO.Approver.Alias")).getStringCellValue());
			po.setApproverName(row.getCell(head.get("PO.Appover Name")).getStringCellValue());
			po.setApproverType(row.getCell(head.get("PO.Approver Type")).getStringCellValue());
			po.setStatus(row.getCell(head.get("SAPPOStatus")).getStringCellValue());
			po.setOwnerRegion(row.getCell(head.get("PO Owner.Region")).getStringCellValue());
			po.setOwnerSegment(row.getCell(head.get("PO Owner.Segment")).getStringCellValue());
			po.setOwnerGSMO(row.getCell(head.get("PO Owner.GSMO")).getStringCellValue());
			body.add(po);
		}
		return purchaseOrderDao.save(body);
	}

	private Map<String, Integer> getHead(Sheet sheet) {
		Map<String, Integer> head = new LinkedHashMap<>();
		Row row = sheet.getRow(sheet.getFirstRowNum());
		int first = row.getFirstCellNum(), last = row.getLastCellNum();
		for (int i = first; i < last; i++) {
			Cell cell = row.getCell(i);
			head.put(cell.getStringCellValue(), i);
		}
		return head;
	}

	private Workbook createWorkbook(MultipartFile po) throws IOException {
		String filename = po.getOriginalFilename();
		if (filename.endsWith("xls")) {
			return new HSSFWorkbook(po.getInputStream());
		} else if (filename.endsWith("xlsx")) {
			return new XSSFWorkbook(po.getInputStream());
		} else {
			throw new IOException();
		}
	}

}
