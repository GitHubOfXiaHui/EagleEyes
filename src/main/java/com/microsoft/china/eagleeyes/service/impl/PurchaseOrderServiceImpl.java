package com.microsoft.china.eagleeyes.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.microsoft.china.eagleeyes.dao.CategoryDao;
import com.microsoft.china.eagleeyes.dao.ConstantDao;
import com.microsoft.china.eagleeyes.dao.PurchaseOrderDao;
import com.microsoft.china.eagleeyes.dao.ViolationDao;
import com.microsoft.china.eagleeyes.entity.Category;
import com.microsoft.china.eagleeyes.entity.Constant;
import com.microsoft.china.eagleeyes.entity.PurchaseOrder;
import com.microsoft.china.eagleeyes.service.PurchaseOrderService;
import com.microsoft.china.eagleeyes.util.ExcelUtil;
import com.microsoft.china.eagleeyes.vo.FiscalVo;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

	@Autowired
	private PurchaseOrderDao purchaseOrderDao;

	@Autowired
	private ConstantDao constantDao;

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private ViolationDao violationDao;

	private Map<String, Integer> constantMap;

	@Transactional(readOnly = true)
	@Override
	public List<PurchaseOrder> findAll() {
		return purchaseOrderDao.findAll();
	}

	@Override
	public int getProgress() {
		return purchaseOrderDao.getProgress();
	}

	@Transactional
	@Override
	public List<PurchaseOrder> upload(MultipartFile poFile) throws IOException {
		// 重置progress，防止前端读到上一次的进度
		purchaseOrderDao.resetProgress();

		try (Workbook wb = ExcelUtil.createWorkbook(poFile.getInputStream(), poFile.getOriginalFilename(), true)) {
			Sheet sheet = wb.getSheet("Sheet1");
			List<PurchaseOrder> body = getBody(sheet);
			return body;
		}
	}

	private List<PurchaseOrder> getBody(Sheet sheet) {
		Map<String, Integer> head = null;
		List<PurchaseOrder> body = new ArrayList<>();
		boolean first = true;
		for (Row row : sheet) {
			if (first) {
				head = ExcelUtil.getHead(row);
				first = false;
				continue;
			}
			PurchaseOrder po = new PurchaseOrder();
			po.setPoNumber(
					Optional.ofNullable(row.getCell(head.get("PO Number"))).map(Cell::getStringCellValue).orElse(null));
			// 删除订单编号为空的订单
			if (po.getPoNumber() == null) {
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
					.map(Double::intValue).map(String::valueOf).orElse(null));
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
					.map(Cell::getNumericCellValue).map(Double::intValue).map(String::valueOf).orElse(null));
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

		purchaseOrderDao.deleteAll();
		return purchaseOrderDao.saveBatch(body);
	}

	private TreeMap<String, Set<String>> ownerMap = new TreeMap<>();;
	private TreeMap<String, Set<String>> submitterMap = new TreeMap<>();;
	private TreeMap<String, Set<String>> vendorMap = new TreeMap<>();;
	private String min;
	private int poCreateDateThreshold;
	private int poPaymentDateThreshold;
	private int atf1Threshold;
	private int atf2Threshold;
	private int invoicePOAmountThreshold;
	private int sameVendorOwnerThreshold;

	private int poCreateDateRiskScore;
	private int poPaymentDateRiskScore;
	private int atf1RiskScore;
	private int atf2RiskScore;
	private int poOwnerFailRiskScore;
	private int poSubmitterFailRiskScore;
	private int vendorFailRiskScore;
	private int poOwnerNewRiskScore;
	private int poSubmitterNewRiskScore;
	private int vendorNewRiskScore;

	private Map<String, Double> categoryMap;

	private Set<String> owners = new HashSet<>();
	private Set<String> submitters = new HashSet<>();
	private Set<String> vendors = new HashSet<>();

	@Transactional
	@Override
	public List<PurchaseOrder> calculate() {
		// 重置progress，防止前端读到上一次的进度
		purchaseOrderDao.resetProgress();

		List<PurchaseOrder> pos = purchaseOrderDao.findAll();

		// 计算parse POCreate Date & InvoiceDateLast
		step1(pos);

		// 生成中间表
		getIndexMap(pos, ownerMap, submitterMap, vendorMap);
		min = ownerMap.firstKey();

		// 加载常量表
		constantMap = getConstantMap();
		poCreateDateThreshold = constantMap.get("POCreateDateThreshold");
		poPaymentDateThreshold = constantMap.get("POPaymentDateThreshold");
		atf1Threshold = constantMap.get("AfterTheFact_risk1Threshold");
		atf2Threshold = constantMap.get("AfterTheFact_risk2Threshold");
		invoicePOAmountThreshold = constantMap.get("InvoicePOAmountThreshold");
		sameVendorOwnerThreshold = constantMap.get("SameVendorOwnerThreshold") + 1;

		poCreateDateRiskScore = constantMap.get("POCreateDateRiskScore");
		poPaymentDateRiskScore = constantMap.get("POPaymentDateRiskScore");
		atf1RiskScore = constantMap.get("AfterTheFact_risk1RiskScore");
		atf2RiskScore = constantMap.get("AfterTheFact_risk2RiskScore");
		poOwnerFailRiskScore = constantMap.get("POOwner_FailRiskScore");
		poSubmitterFailRiskScore = constantMap.get("POSubmitter_FailRiskScore");
		vendorFailRiskScore = constantMap.get("Vendor_FailRiskScore");
		poOwnerNewRiskScore = constantMap.get("POOwner_NewRiskScore");
		poSubmitterNewRiskScore = constantMap.get("POSubmitter_NewRiskScore");
		vendorNewRiskScore = constantMap.get("Vendor_NewRiskScore");

		// 加载PO Categories
		categoryMap = getCategoryMap();

		// 加载Violation
		getViolationSet(owners, submitters, vendors);

		pos.parallelStream().forEach(po -> {
			// 标记订单是否异常
			step2(pos, po);
			// 计算POCategory Risk
			step3(po);
			// 计算PO Date Risk
			step4(po);
			// 计算PO Fail Risk
			step5(po);
			// 计算PO New Risk
			step6(po);
			// 求和
			step7(po);
		});

		return purchaseOrderDao.updateBatch(pos);
	}

	private void step1(List<PurchaseOrder> pos) {
		pos.parallelStream().forEach(po -> {
			FiscalVo fvo = parseDate(po.getApprovedDate());
			po.setPoFiscalYear(Optional.ofNullable(fvo).map(FiscalVo::getFiscalYear).orElse(null));
			po.setPoFiscalQuarter(Optional.ofNullable(fvo).map(FiscalVo::getFiscalQuarter).orElse(null));
			po.setPoMonth(Optional.ofNullable(fvo).map(FiscalVo::getMonth).orElse(null));
			po.setPoIndex(Optional.ofNullable(fvo).map(FiscalVo::getIndex).orElse(null));

			fvo = parseDate(po.getInvoiceDateLast());
			po.setInvFiscalYear(Optional.ofNullable(fvo).map(FiscalVo::getFiscalYear).orElse(null));
			po.setInvFiscalQuarter(Optional.ofNullable(fvo).map(FiscalVo::getFiscalQuarter).orElse(null));
			po.setInvMonth(Optional.ofNullable(fvo).map(FiscalVo::getMonth).orElse(null));
		});
	}

	private void step2(List<PurchaseOrder> pos, PurchaseOrder po) {
		Date approvedDate = po.getApprovedDate();
		Date startDate = po.getStartDate();
		Date invoiceDateFirst = po.getInvoiceDateFirst();
		po.setAtf1Flag(approvedDate != null && startDate != null && startDate.before(approvedDate));
		po.setAtf2Flag(startDate != null && invoiceDateFirst != null && invoiceDateFirst.before(startDate));

		Double amount = po.getAmount();
		Double invoiceAmount = po.getInvoiceAmount();
		po.setAmountFlag(amount != null && invoiceAmount != null && invoiceAmount - amount > invoicePOAmountThreshold);

		String owner = po.getOwnerAlias();
		String submitter = po.getSubmitterAlias();
		String vendor = po.getSupplierNumber();
		String approver = po.getApproverAlias();
		po.setPoSplitFlag(
				owner != null && submitter != null && vendor != null && approver != null && maySplitPo(pos, po));

		po.setRiskFlag(po.getAtf1Flag() || po.getAtf2Flag() || po.getAmountFlag() || po.getPoSplitFlag());
	}

	private void step3(PurchaseOrder po) {
		po.setCategoryRisk(categoryMap.getOrDefault(po.getCategory(), -1.0).intValue());
	}

	private void step4(PurchaseOrder po) {
		Date approvedDate = po.getApprovedDate();
		Date startDate = po.getStartDate();
		Date invoiceDateFirst = po.getInvoiceDateFirst();
		po.setCreateDateDiff(approvedDate != null ? dateDiff(approvedDate, getEndOfQuarter(approvedDate)) : null);
		po.setCreateDateRisk(po.getCreateDateDiff() != null && po.getCreateDateDiff() <= poCreateDateThreshold
				? poCreateDateRiskScore
				: 0);
		po.setPaymentDateDiff(
				approvedDate != null && invoiceDateFirst != null ? dateDiff(approvedDate, invoiceDateFirst) : null);
		po.setPaymentDateRisk(po.getPaymentDateDiff() != null && po.getPaymentDateDiff() <= poPaymentDateThreshold
				? poPaymentDateRiskScore
				: 0);
		po.setAtf1Diff(approvedDate != null && startDate != null ? dateDiff(approvedDate, startDate) : null);
		po.setAtf1Risk(po.getAtf1Diff() != null && po.getAtf1Diff() <= atf1Threshold ? atf1RiskScore : 0);
		po.setAtf2Diff(startDate != null && invoiceDateFirst != null ? dateDiff(startDate, invoiceDateFirst) : null);
		po.setAtf2Risk(po.getAtf2Diff() != null && po.getAtf2Diff() <= atf2Threshold ? atf2RiskScore : 0);
	}

	private void step5(PurchaseOrder po) {
		po.setOwnerFailRisk(
				po.getOwnerAlias() != null && owners.contains(po.getOwnerAlias()) ? poOwnerFailRiskScore : 0);
		po.setSubmitterFailRisk(
				po.getSubmitterAlias() != null && submitters.contains(po.getSubmitterAlias()) ? poSubmitterFailRiskScore
						: 0);
		po.setVendorFailRisk(
				po.getSupplierNumber() != null && vendors.contains(po.getSupplierNumber()) ? vendorFailRiskScore : 0);
	}

	private void step6(PurchaseOrder po) {
		String preIndex = getPreIndex(po.getApprovedDate());
		String index = po.getPoIndex();
		String owner = po.getOwnerAlias();
		String submitter = po.getSubmitterAlias();
		String vendor = po.getSupplierNumber();
		po.setOwnerNewRisk(index != null && owner != null && !index.equals(min)
				&& !ownerMap.getOrDefault(preIndex, new HashSet<>()).contains(owner) ? poOwnerNewRiskScore : 0);
		po.setSubmitterNewRisk(index != null && submitter != null && !index.equals(min)
				&& !submitterMap.getOrDefault(preIndex, new HashSet<>()).contains(submitter) ? poSubmitterNewRiskScore
						: 0);
		po.setVendorNewRisk(index != null && vendor != null && !index.equals(min)
				&& !vendorMap.getOrDefault(preIndex, new HashSet<>()).contains(vendor) ? vendorNewRiskScore : 0);
	}

	private void step7(PurchaseOrder po) {
		po.setRiskScore(po.getCategoryRisk() + po.getCreateDateRisk() + po.getPaymentDateRisk() + po.getAtf1Risk()
				+ po.getAtf2Risk() + po.getOwnerFailRisk() + po.getSubmitterFailRisk() + po.getVendorFailRisk()
				+ po.getOwnerNewRisk() + po.getSubmitterNewRisk() + po.getVendorNewRisk());
	}

	private void getIndexMap(List<PurchaseOrder> pos, Map<String, Set<String>> ownerMap,
			Map<String, Set<String>> submitterMap, Map<String, Set<String>> vendorMap) {
		pos.parallelStream().forEach(po -> {
			String index = po.getPoIndex();

			if (index != null) {
				// 订单拥有者
				String owner = po.getOwnerAlias();
				if (owner != null) {
					Set<String> owners = ownerMap.get(index);
					if (owners == null) {
						owners = new HashSet<>();
						ownerMap.put(index, owners);
					}
					owners.add(owner);
				}

				// 订单提交者
				String submitter = po.getSubmitterAlias();
				if (submitter != null) {
					Set<String> submitters = submitterMap.get(index);
					if (submitters == null) {
						submitters = new HashSet<>();
						submitterMap.put(index, submitters);
					}
					submitters.add(submitter);
				}

				// 订单供应商
				String vendor = po.getSupplierNumber();
				if (vendor != null) {
					Set<String> vendors = vendorMap.get(index);
					if (vendors == null) {
						vendors = new HashSet<>();
						vendorMap.put(index, vendors);
					}
					vendors.add(vendor);
				}
			}
		});
	}

	private Map<String, Integer> getConstantMap() {
		return constantDao.findAll().parallelStream().collect(Collectors.toMap(Constant::getName, Constant::getValue));
	}

	private Map<String, Double> getCategoryMap() {
		return categoryDao.findAll().parallelStream()
				.collect(Collectors.toMap(Category::getCategoryName, Category::getScore));
	}

	private void getViolationSet(Set<String> owners, Set<String> submitters, Set<String> vendors) {
		violationDao.findAll().parallelStream().forEach(vio -> {
			owners.add(vio.getPoOwner());
			submitters.add(vio.getPoSubmitter());
			vendors.add(vio.getVendorId());
		});
	}

	private FiscalVo parseDate(Date approvedDate) {
		if (approvedDate == null) {
			return null;
		}
		Calendar ca = Calendar.getInstance();
		ca.setTime(approvedDate);
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH);
		int quarter = month / 3;
		FiscalVo fvo = new FiscalVo();
		fvo.setFiscalYear("FY" + toFiscalYear(year, quarter));
		fvo.setFiscalQuarter("Q" + toFiscalQuarter(quarter));
		fvo.setMonth(ca.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH));
		return fvo;
	}

	private int toFiscalYear(int year, int quarter) {
		return (year % 100) + (quarter / 2);
	}

	private int toFiscalQuarter(int quarter) {
		return ((quarter + 2) % 4) + 1;
	}

	private boolean maySplitPo(List<PurchaseOrder> pos, PurchaseOrder po) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(po.getApprovedDate());
		calendar.add(Calendar.DATE, -sameVendorOwnerThreshold);
		Date before = calendar.getTime();
		calendar.add(Calendar.DATE, sameVendorOwnerThreshold << 1);
		Date after = calendar.getTime();
		return pos.parallelStream()
				.filter(p -> !p.getPoNumber().equals(po.getPoNumber()) && p.getOwnerAlias().equals(po.getOwnerAlias())
						&& p.getSubmitterAlias().equals(po.getSubmitterAlias())
						&& p.getSupplierNumber().equals(po.getSupplierNumber())
						&& p.getApproverAlias().equals(po.getApproverAlias()) && p.getApprovedDate().after(before)
						&& p.getApprovedDate().before(after))
				.count() > 0;
	}

	private Date getEndOfQuarter(Date approvedDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(approvedDate);
		int endOfQuarter = ((calendar.get(Calendar.MONTH) / 3) + 1) * 3;
		calendar.set(Calendar.MONTH, endOfQuarter);
		calendar.set(Calendar.DATE, 0);
		return calendar.getTime();
	}

	private int dateDiff(Date d1, Date d2) {
		long l1 = d1.getTime();
		long l2 = d2.getTime();
		return (int) ((l2 - l1) / (24 * 60 * 60 * 1000));
	}

	private String getPreIndex(Date approvedDate) {
		if (approvedDate == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(approvedDate);
		int year = calendar.get(Calendar.YEAR);
		int quarter = calendar.get(Calendar.MONTH) / 3;
		int FY = toFiscalYear(year, quarter);
		int Q = toFiscalQuarter(quarter);
		int preFY = Q == 1 ? FY - 1 : FY;
		int preQ = Q == 1 ? 4 : Q - 1;
		return "FY" + preFY + "Q" + preQ;
	}

}
