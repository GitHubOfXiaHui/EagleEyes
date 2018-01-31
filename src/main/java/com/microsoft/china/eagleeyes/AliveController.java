package com.microsoft.china.eagleeyes;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AliveController {
	
	@RequestMapping(path = "alive", method = RequestMethod.GET)
	public String alive() {
		return "alive";
	}
	
}
