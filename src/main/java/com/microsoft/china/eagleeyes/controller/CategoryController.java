package com.microsoft.china.eagleeyes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CategoryController {

	@RequestMapping(path = "category", method = RequestMethod.GET)
	public String category() {
		return CATEGORY;
	}
	
	private static final String CATEGORY = "/category";
}
