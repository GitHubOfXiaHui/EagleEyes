package com.microsoft.china.eagleeyes.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.microsoft.china.eagleeyes.entity.Category;

public interface CategoryDao extends JpaRepository<Category, Integer> {

	List<Category> findByCategoryGroupLikeAndCategoryNameLike(String categoryGroup, String categoryName);
	
	@Transactional
	@Modifying
	@Query("delete from Category")
	void deleteAll();
	
}
