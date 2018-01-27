package com.microsoft.china.eagleeyes.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.microsoft.china.eagleeyes.entity.Violation;
import com.microsoft.china.eagleeyes.service.ViolationService;

@Controller
@RequestMapping("/violation")
public class ViolationController {

	@Autowired
	private ViolationService violationService;

	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<Violation> violations = violationService.findAll();
		model.addAttribute("vios", violations);
		return VIOLATION;
	}

	@RequestMapping(path = "/upload", method = RequestMethod.POST)
	public String upload(MultipartFile violationFile, Model model) throws IOException {
		List<Violation> violations = violationService.upload(violationFile);
		model.addAttribute("vios", violations);
		return VIOLATION;
	}

	private static final String VIOLATION = "violation";
}
