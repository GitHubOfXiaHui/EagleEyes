package com.microsoft.china.eagleeyes.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.microsoft.china.eagleeyes.entity.Category;

public interface CategoryService {

	List<Category> upload(MultipartFile categoryFile) throws IOException;

	List<Category> findAll();

	List<Category> find(String categoryGroup, String categoryName);

	void update(Category category);

}
