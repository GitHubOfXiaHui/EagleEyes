package com.microsoft.china.eagleeyes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/fail")
public class FailController {

	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public String list() {
		return FAIL;
	}
	
	private static final String FAIL = "/fail";
}
