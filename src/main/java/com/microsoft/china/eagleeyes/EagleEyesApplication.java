package com.microsoft.china.eagleeyes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class EagleEyesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EagleEyesApplication.class, args);
	}
}
