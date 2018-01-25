package com.microsoft.china.eagleeyes.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microsoft.china.eagleeyes.entity.Constant;

public interface ConstantDao extends JpaRepository<Constant, Integer> {

	List<Constant> findByRuleLikeAndNameLike(String rule, String name);

}
