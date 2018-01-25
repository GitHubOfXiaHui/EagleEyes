package com.microsoft.china.eagleeyes.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.microsoft.china.eagleeyes.dao.ConstantDao;
import com.microsoft.china.eagleeyes.dao.PurchaseOrderDao;
import com.microsoft.china.eagleeyes.entity.Constant;
import com.microsoft.china.eagleeyes.entity.PurchaseOrder;
import com.microsoft.china.eagleeyes.service.PurchaseOrderService;
import com.microsoft.china.eagleeyes.util.ExcelUtil;
import com.microsoft.china.eagleeyes.vo.FiscalVo;
import com.microsoft.china.eagleeyes.vo.OwnerVo;
import com.microsoft.china.eagleeyes.vo.SubmitterVo;
import com.microsoft.china.eagleeyes.vo.VendorVo;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

	@Autowired
	private PurchaseOrderDao purchaseOrderDao;

	@Autowired
	private ConstantDao constantDao;

	@Transactional
	@Override
	public List<PurchaseOrder> upload(MultipartFile poFile) throws IOException {
		Workbook wb = ExcelUtil.createWorkbook(poFile.getInputStream(), poFile.getOriginalFilename());
		Sheet sheet = wb.getSheet("Sheet1");
		Map<String, Integer> head = ExcelUtil.getHead(sheet);
		List<PurchaseOrder> body = getBody(sheet, head);
		return body;
	}

	private List<PurchaseOrder> getBody(Sheet sheet, Map<String, Integer> head) {
		int first = sheet.getFirstRowNum();
		int last = sheet.getLastRowNum();
		List<PurchaseOrder> body = new ArrayList<>(last - first);
		for (int i = first + 1; i <= last; i++) {
			Row row = sheet.getRow(i);
			PurchaseOrder po = new PurchaseOrder();
			po.setNumber(
					Optional.ofNullable(row.getCell(head.get("PO Number"))).map(Cell::getStringCellValue).orElse(null));
			// 删除订单编号为空的订单
			if (po.getNumber() == null) {
				continue;
			}
			po.setAmount(Optional.ofNullable(row.getCell(head.get("POAmountTotal RMB"))).map(Cell::getNumericCellValue)
					.orElse(null));
			po.setInvoiceAmount(Optional.ofNullable(row.getCell(head.get("InvoiceAmountTotal RMB")))
					.map(Cell::getNumericCellValue).orElse(null));
			po.setInvoiceDateFirst(Optional.ofNullable(row.getCell(head.get("InvoiceDateFirst")))
					.map(Cell::getDateCellValue).orElse(null));
			po.setInvoiceDateLast(Optional.ofNullable(row.getCell(head.get("InvoiceDateLast")))
					.map(Cell::getDateCellValue).orElse(null));
			po.setCount(
					Optional.ofNullable(row.getCell(head.get("PO.Count"))).map(Cell::getNumericCellValue).orElse(null));
			po.setCategory(Optional.ofNullable(row.getCell(head.get("PO.Category"))).map(Cell::getStringCellValue)
					.orElse(null));
			po.setCategoryGroup(Optional.ofNullable(row.getCell(head.get("PO.Category Group")))
					.map(Cell::getStringCellValue).orElse(null));
			po.setCompanyCode(Optional.ofNullable(row.getCell(head.get("Company Code"))).map(Cell::getNumericCellValue)
					.orElse(null));
			po.setApprovedDate(Optional.ofNullable(row.getCell(head.get("PO.Created Date_ApprovedDate")))
					.map(Cell::getDateCellValue).orElse(null));
			po.setDescription(Optional.ofNullable(row.getCell(head.get("PO.Description"))).map(Cell::getStringCellValue)
					.orElse(null));
			po.setOwnerAlias(Optional.ofNullable(row.getCell(head.get("PO.Owner Alias"))).map(Cell::getStringCellValue)
					.orElse(null));
			po.setOwnerName(Optional.ofNullable(row.getCell(head.get("PO.Owner Name"))).map(Cell::getStringCellValue)
					.orElse(null));
			po.setSubmitterAlias(Optional.ofNullable(row.getCell(head.get("PO.Submitted By Alias")))
					.map(Cell::getStringCellValue).orElse(null));
			po.setSubmitterName(Optional.ofNullable(row.getCell(head.get("PO.Submitted By Name")))
					.map(Cell::getStringCellValue).orElse(null));
			po.setSupplierNumber(Optional.ofNullable(row.getCell(head.get("PO.Supplier Number")))
					.map(Cell::getNumericCellValue).orElse(null));
			po.setSupplierName(Optional.ofNullable(row.getCell(head.get("PO.Supplier Name")))
					.map(Cell::getStringCellValue).orElse(null));
			po.setStartDate(Optional.ofNullable(row.getCell(head.get("PO.Start Date"))).map(Cell::getDateCellValue)
					.orElse(null));
			po.setApproverAlias(Optional.ofNullable(row.getCell(head.get("PO.Approver.Alias")))
					.map(Cell::getStringCellValue).orElse(null));
			po.setApproverName(Optional.ofNullable(row.getCell(head.get("PO.Appover Name")))
					.map(Cell::getStringCellValue).orElse(null));
			po.setApproverType(Optional.ofNullable(row.getCell(head.get("PO.Approver Type")))
					.map(Cell::getStringCellValue).orElse(null));
			po.setStatus(Optional.ofNullable(row.getCell(head.get("SAPPOStatus"))).map(Cell::getStringCellValue)
					.orElse(null));
			po.setOwnerRegion(Optional.ofNullable(row.getCell(head.get("PO Owner.Region")))
					.map(Cell::getStringCellValue).orElse(null));
			po.setOwnerSegment(Optional.ofNullable(row.getCell(head.get("PO Owner.Segment")))
					.map(Cell::getStringCellValue).orElse(null));
			po.setOwnerGSMO(Optional.ofNullable(row.getCell(head.get("PO Owner.GSMO"))).map(Cell::getStringCellValue)
					.orElse(null));
			body.add(po);
		}
		purchaseOrderDao.truncate();
		return purchaseOrderDao.save(body);
	}

	@Override
	public List<PurchaseOrder> findAll() {
		return purchaseOrderDao.findAll();
	}

	@Override
	public void share() {
		// 计算订单标记和风险值
		List<PurchaseOrder> pos = calculate();

		// 分享到Power BI
	}

	private List<PurchaseOrder> calculate() {
		List<PurchaseOrder> pos = purchaseOrderDao.findAll();
		// 计算财年POCreate Date
		step1(pos);
		// 生成中间表
		Map<String, Set<OwnerVo>> ownerMap = new HashMap<>();
		Map<String, Set<SubmitterVo>> submitterMap = new HashMap<>();
		Map<String, Set<VendorVo>> vendorMap = new HashMap<>();
		step2(pos, ownerMap, submitterMap, vendorMap);
		// 计算财年InvoiceDateLast
		step3(pos);
		// 标记订单是否异常
		Map<String, Integer> constantMap = getConstants();
		step4(pos, constantMap);

		purchaseOrderDao.save(pos);
		return pos;
	}

	private Map<String, Integer> getConstants() {
		return constantDao.findAll().parallelStream().collect(Collectors.toMap(Constant::getName, Constant::getValue));
	}

	private void step4(List<PurchaseOrder> pos, Map<String, Integer> constantMap) {
		pos.parallelStream().forEach(po -> {
			Date approvalDate = po.getApprovedDate();
			Date startDate = po.getStartDate();
			Date invoiceDateFirst = po.getInvoiceDateFirst();
			po.setAtf1Flag(approvalDate != null && startDate != null && approvalDate.before(startDate));
			po.setAtf2Flag(startDate != null && invoiceDateFirst != null && startDate.before(invoiceDateFirst));

		});
	}

	private void step3(List<PurchaseOrder> pos) {
		pos.parallelStream().forEach(po -> {
			FiscalVo fvo = parseDate(po.getInvoiceDateLast());
			po.setInvFiscalYear(fvo.getFiscalYear());
			po.setInvFiscalQuarter(fvo.getFiscalQuarter());
			po.setInvMonth(fvo.getMonth());
		});
	}

	private void step1(List<PurchaseOrder> pos) {
		pos.parallelStream().forEach(po -> {
			FiscalVo fvo = parseDate(po.getApprovedDate());
			po.setPoFiscalYear(fvo.getFiscalYear());
			po.setPoFiscalQuarter(fvo.getFiscalQuarter());
			po.setPoMonth(fvo.getMonth());
			po.setPoIndex(fvo.getIndex());
		});
	}

	private void step2(List<PurchaseOrder> pos, Map<String, Set<OwnerVo>> ownerMap,
			Map<String, Set<SubmitterVo>> submitterMap, Map<String, Set<VendorVo>> vendorMap) {
		pos.parallelStream().forEach(po -> {
			String index = po.getPoIndex();

			// 订单拥有者
			Set<OwnerVo> owners = ownerMap.get(index);
			if (owners == null) {
				owners = new HashSet<>();
				ownerMap.put(index, owners);
			}
			owners.add(new OwnerVo(po.getOwnerAlias(), index));

			// 订单提交者
			Set<SubmitterVo> submitters = submitterMap.get(index);
			if (submitters == null) {
				submitters = new HashSet<>();
				submitterMap.put(index, submitters);
			}
			submitters.add(new SubmitterVo(po.getSubmitterAlias(), index));

			// 订单供应商
			Set<VendorVo> vendors = vendorMap.get(index);
			if (vendors == null) {
				vendors = new HashSet<>();
				vendorMap.put(index, vendors);
			}
			vendors.add(new VendorVo(po.getSupplierNumber(), index));
		});
	}

	private FiscalVo parseDate(Date approvedDate) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(approvedDate);
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH);
		int quarter = month / 3;
		FiscalVo fvo = new FiscalVo();
		fvo.setFiscalYear("FY" + (year % 100));
		fvo.setFiscalQuarter("Q" + (quarter + 1));
		fvo.setMonth(ca.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH));
		return fvo;
	}

}
