package com.microsoft.china.eagleeyes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	@RequestMapping(path = "index", method = RequestMethod.GET)
	public String index() {
		return INDEX;
	}
	
	private static final String INDEX = "/index"; 
}
