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

import com.microsoft.china.eagleeyes.dao.CategoryDao;
import com.microsoft.china.eagleeyes.entity.Category;
import com.microsoft.china.eagleeyes.service.CategoryService;
import com.microsoft.china.eagleeyes.util.ExcelUtil;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Transactional
	@Override
	public List<Category> upload(MultipartFile categoryFile) throws IOException {
		try (Workbook wb = ExcelUtil.createWorkbook(categoryFile.getInputStream(),
				categoryFile.getOriginalFilename())) {
			Sheet sheet = wb.getSheet("PO Categories");
			Map<String, Integer> head = ExcelUtil.getHead(sheet.getRow(sheet.getFirstRowNum()));
			List<Category> body = getBody(sheet, head);
			return body;
		}
	}

	private List<Category> getBody(Sheet sheet, Map<String, Integer> head) {
		int first = sheet.getFirstRowNum();
		int last = sheet.getLastRowNum();
		List<Category> body = new ArrayList<>(last - first);
		for (int i = first + 1; i <= last; i++) {
			Row row = sheet.getRow(i);
			Category category = new Category();
			category.setCategoryGroup(Optional.ofNullable(row.getCell(head.get("Standard Group Name")))
					.map(Cell::getStringCellValue).orElse(null));
			category.setCategoryName(Optional.ofNullable(row.getCell(head.get("Standard Category Name")))
					.map(Cell::getStringCellValue).orElse(null));
			if (category.getCategoryName() == null) {
				continue;
			}
			category.setScore(
					Optional.ofNullable(row.getCell(head.get("Score"))).map(Cell::getNumericCellValue).orElse(null));
			category.setDefinition(Optional.ofNullable(row.getCell(head.get("Standard Category Definition")))
					.map(Cell::getStringCellValue).orElse(null));
			body.add(category);
		}
		categoryDao.deleteAll();
		return categoryDao.save(body);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Category> find(String categoryGroup, String categoryName) {
		categoryGroup = "%" + categoryGroup + "%";
		categoryName = "%" + categoryName + "%";
		return categoryDao.findByCategoryGroupLikeAndCategoryNameLike(categoryGroup, categoryName);
	}

	@Transactional
	@Override
	public void update(Category category) {
		categoryDao.save(category);
	}

}
