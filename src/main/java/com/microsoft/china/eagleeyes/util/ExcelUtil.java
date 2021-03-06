package com.microsoft.china.eagleeyes.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.monitorjbl.xlsx.StreamingReader;

public abstract class ExcelUtil {

	public static Workbook createWorkbook(InputStream is, String filename) throws IOException {
		if (filename.endsWith(EXCEL2003)) {
			return new HSSFWorkbook(is);
		} else if (filename.endsWith(EXCEL2007)) {
			return new XSSFWorkbook(is);
		} else {
			throw new IOException();
		}
	}

	public static Workbook createWorkbook(InputStream is, String filename, boolean useless) throws IOException {
		if (filename.endsWith(EXCEL2007)) {
			return StreamingReader.builder().rowCacheSize(100).bufferSize(4096).open(is);
		} else {
			throw new IOException();
		}
	}

	public static Map<String, Integer> getHead(Row firstRow) {
		Map<String, Integer> head = new LinkedHashMap<>();
		int first = firstRow.getFirstCellNum(), last = firstRow.getLastCellNum();
		for (int i = first; i < last; i++) {
			Cell cell = firstRow.getCell(i);
			head.put(cell.getStringCellValue(), i);
		}
		return head;
	}

	private static final String EXCEL2003 = "xls";
	private static final String EXCEL2007 = "xlsx";
}
