package com.microsoft.china.eagleeyes.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.microsoft.china.eagleeyes.entity.Violation;

public interface ViolationService {

	List<Violation> upload(MultipartFile violationFile) throws IOException;

	List<Violation> findAll();

}
