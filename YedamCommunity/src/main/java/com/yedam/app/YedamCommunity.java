package com.yedam.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan(basePackages = "com.yedam.app.**.mapper")
public class YedamCommunity {

	public static void main(String[] args) {
		SpringApplication.run(YedamCommunity.class, args);
	}

}
