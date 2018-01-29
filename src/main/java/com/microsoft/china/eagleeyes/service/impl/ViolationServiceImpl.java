package com.microsoft.china.eagleeyes.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.microsoft.china.eagleeyes.dao.ViolationDao;
import com.microsoft.china.eagleeyes.entity.Violation;
import com.microsoft.china.eagleeyes.service.ViolationService;
import com.microsoft.china.eagleeyes.util.ExcelUtil;

@Service
public class ViolationServiceImpl implements ViolationService {

	@Autowired
	private ViolationDao violationDao;

	@Transactional
	@Override
	public List<Violation> upload(MultipartFile violationFile) throws IOException {
		try (Workbook wb = ExcelUtil.createWorkbook(violationFile.getInputStream(),
				violationFile.getOriginalFilename())) {
			Sheet sheet = wb.getSheet("POViolationList");
			Map<String, Integer> head = ExcelUtil.getHead(sheet.getRow(sheet.getFirstRowNum()));
			List<Violation> body = getBody(sheet, head);
			return body;
		}
	}

	private List<Violation> getBody(Sheet sheet, Map<String, Integer> head) {
		int first = sheet.getFirstRowNum();
		int last = sheet.getLastRowNum();
		List<Violation> body = new ArrayList<>(last - first);
		for (int i = first + 1; i <= last; i++) {
			Row row = sheet.getRow(i);
			Violation violation = new Violation();
			violation.setReportYear(Optional.ofNullable(row.getCell(head.get("Report Year")))
					.map(Cell::getStringCellValue).orElse(null));
			violation.setReportMonth(Optional.ofNullable(row.getCell(head.get("Report Month")))
					.map(Cell::getStringCellValue).orElse(null));
			violation.setPoNumber(
					Optional.ofNullable(row.getCell(head.get("PO Number"))).map(Cell::getStringCellValue).orElse(null));
			violation.setPoSubmitter(Optional.ofNullable(row.getCell(head.get("PO Submitter")))
					.map(Cell::getStringCellValue).orElse(null));
			violation.setPoOwner(Optional.ofNullable(row.getCell(head.get("Sample Owner")))
					.map(Cell::getStringCellValue).orElse(null));
			violation.setVendorName(Optional.ofNullable(row.getCell(head.get("Vendor Name")))
					.map(Cell::getStringCellValue).orElse(null));
			violation.setVendorId(Optional.ofNullable(row.getCell(head.get("Vendor ID"))).map(Cell::getNumericCellValue)
					.map(Double::intValue).map(String::valueOf).orElse(null));
			violation.setComments(Optional.ofNullable(row.getCell(head.get("C&C Comments")))
					.map(Cell::getStringCellValue).orElse(null));
			violation.setTestingResult(Optional.ofNullable(row.getCell(head.get("Testing Result")))
					.map(Cell::getStringCellValue).orElse(null));
			body.add(violation);
		}
		violationDao.deleteAll();
		return violationDao.save(body);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Violation> findAll() {
		return violationDao.findAll();
	}

}
