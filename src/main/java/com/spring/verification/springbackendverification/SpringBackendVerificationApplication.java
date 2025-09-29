package com.spring.verification.springbackendverification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(basePackages = "com.spring.verification.springbackendverification",
		excludeFilters = @Filter(type = FilterType.REGEX, pattern = "com.spring.verification.springbackendverification.security.*"))
public class SpringBackendVerificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBackendVerificationApplication.class, args);
	}
}