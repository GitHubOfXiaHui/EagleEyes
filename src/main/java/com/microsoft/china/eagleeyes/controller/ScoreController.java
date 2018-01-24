package com.microsoft.china.eagleeyes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ScoreController {

	@RequestMapping(path = "score", method = RequestMethod.GET)
	public String score() {
		return SCORE;
	}
	
	private static final String SCORE = "/score";
}
