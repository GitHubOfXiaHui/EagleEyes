package com.microsoft.china.eagleeyes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microsoft.china.eagleeyes.dao.ConstantDao;
import com.microsoft.china.eagleeyes.entity.Constant;
import com.microsoft.china.eagleeyes.service.ConstantService;

@Service
public class ConstantServiceImpl implements ConstantService {
	
	@Autowired
	private ConstantDao constantDao;

	@Transactional(readOnly = true)
	@Override
	public List<Constant> findAll() {
		return constantDao.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Constant> find(String rule, String name) {
		rule = "%" + rule + "%";
		name = "%" + name + "%";
		return constantDao.findByRuleLikeAndNameLike(rule, name);
	}

	@Transactional
	@Override
	public Constant update(Constant constant) {
		return constantDao.save(constant);
	}

}
