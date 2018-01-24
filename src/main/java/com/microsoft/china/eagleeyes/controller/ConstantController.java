package com.microsoft.china.eagleeyes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ConstantController {

	@RequestMapping(path = "constant", method = RequestMethod.GET)
	public String list(Model model) {
		
		return CONSTANT;
	}
	
	private static final String CONSTANT = "/constant";
}
