package com.microsoft.china.eagleeyes.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.microsoft.china.eagleeyes.entity.Category;
import com.microsoft.china.eagleeyes.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<Category> categories = categoryService.findAll();
		model.addAttribute("cas", categories);
		return CATEGORY;
	}

	@RequestMapping(path = "/upload", method = RequestMethod.POST)
	public String upload(MultipartFile categoryFile, Model model) throws IOException {
		List<Category> categories = categoryService.upload(categoryFile);
		model.addAttribute("cas", categories);
		return CATEGORY;
	}

	@RequestMapping(path = "/search", method = RequestMethod.POST)
	public String search(String categoryGroup, String categoryName, Model model) {
		List<Category> categories = categoryService.find(categoryGroup, categoryName);
		model.addAttribute("categoryGroup", categoryGroup);
		model.addAttribute("categoryName", categoryName);
		model.addAttribute("cas", categories);
		return CATEGORY;
	}
	
	@RequestMapping(path = "edit", method = RequestMethod.POST)
	public String edit(Category category, Model model) {
		categoryService.update(category);
		List<Category> categories = categoryService.findAll();
		model.addAttribute("cas", categories);
		return CATEGORY;
	}

	private static final String CATEGORY = "category";
}
