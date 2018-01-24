package com.microsoft.china.eagleeyes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FailController {

	@RequestMapping(path = "fail", method = RequestMethod.GET)
	public String fail() {
		return FAIL;
	}
	
	private static final String FAIL = "/fail";
}
