package com.microsoft.china.eagleeyes.controller;

import java.io.IOException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BaseControllerAdvice {

	@ExceptionHandler(BindException.class)
	public String inputError(BindException e) {
		System.out.println(e.getMessage());
		return INPUT_ERROR;
	}
	
	@ExceptionHandler(IOException.class)
	public String fileError(IOException e) {
		System.out.println(e.getMessage());
		return FILE_ERROR;
	}
	
	@ExceptionHandler({IllegalStateException.class, DataIntegrityViolationException.class})
	public String formatError(RuntimeException e) {
		System.out.println(e.getMessage());
		return FORMAT_ERROR;
	}

	private static final String INPUT_ERROR = "/error/input";
	private static final String FILE_ERROR = "/error/file";
	private static final String FORMAT_ERROR = "/error/format";
}
