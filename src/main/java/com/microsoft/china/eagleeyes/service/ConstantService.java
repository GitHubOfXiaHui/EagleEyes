package com.microsoft.china.eagleeyes.service;

import java.util.List;

import com.microsoft.china.eagleeyes.entity.Constant;

public interface ConstantService {

	List<Constant> findAll();

	List<Constant> find(String rule, String name);

	Constant update(Constant constant);

}
