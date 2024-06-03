package com.yedam.app.yedam_homework.upload.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Value("${file.upload.path}")
	private String uploadPath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/files/**") // 주소
		.addResourceLocations("file://"+ uploadPath); // file:///c:/upload/
		// uploadPath: 프로퍼티의 file.upload.path에 해당하는 주소
		// /files/upload/파일이름.jpg
	}
}
