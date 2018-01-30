package com.microsoft.china.eagleeyes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("about")
public class AboutController {

	@RequestMapping(path = "/show", method = RequestMethod.GET)
	public String show() {
		return ABOUT;
	}
	
	private static final String ABOUT = "about";
}
