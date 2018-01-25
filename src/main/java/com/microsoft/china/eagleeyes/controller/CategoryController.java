package com.microsoft.china.eagleeyes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public String list() {
		return CATEGORY;
	}
	
	private static final String CATEGORY = "/category";
}
