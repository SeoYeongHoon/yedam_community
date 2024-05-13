package com.yedam.app.yedam_homework.upload.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
public class MultipartConfig {
	
	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
		
	}
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxRequestSize(DataSize.ofMegabytes(100));
		factory.setMaxFileSize(DataSize.ofMegabytes(100));
		return factory.createMultipartConfig();
	}
}
