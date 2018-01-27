package com.microsoft.china.eagleeyes.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.microsoft.china.eagleeyes.entity.Violation;

public interface ViolationDao extends JpaRepository<Violation, Integer> {
	
	@Transactional
	@Modifying
	@Query("delete from Violation")
	void deleteAll();

}
