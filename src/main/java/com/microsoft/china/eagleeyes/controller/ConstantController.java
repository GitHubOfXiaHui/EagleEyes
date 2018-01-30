package com.microsoft.china.eagleeyes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.microsoft.china.eagleeyes.entity.Constant;
import com.microsoft.china.eagleeyes.service.ConstantService;

@Controller
@RequestMapping("constant")
public class ConstantController {

	@Autowired
	private ConstantService constantService;

	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<Constant> constants = constantService.findAll();
		model.addAttribute("constants", constants);
		return CONSTANT;
	}

	@RequestMapping(path = "/search", method = RequestMethod.POST)
	public String search(String rule, String name, Model model) {
		List<Constant> constants = constantService.find(rule, name);
		model.addAttribute("rule", rule);
		model.addAttribute("name", name);
		model.addAttribute("constants", constants);
		return CONSTANT;
	}

	@RequestMapping(path = "/edit", method = RequestMethod.POST)
	public String edit(Constant constant) {
		constantService.update(constant);
		return REDIRECT_LIST;
	}

	private static final String CONSTANT = "constant";
	private static final String REDIRECT_LIST = "redirect:list";
}
