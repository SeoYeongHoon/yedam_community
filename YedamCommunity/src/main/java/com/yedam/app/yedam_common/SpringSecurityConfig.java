package com.yedam.app.yedam_common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

	// 암호화
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// 인증 및 인가 설정
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
			.authorizeHttpRequests(requests ->requests
				.antMatchers("/**").permitAll()
				.antMatchers("/adminMain").hasRole("ADMIN")
				.antMatchers("/home").authenticated()
				.anyRequest().authenticated())
			.formLogin(login ->login
				.loginPage("/").permitAll()
				.defaultSuccessUrl("/home", true))
			.logout(logout ->logout
				.logoutSuccessUrl("/")
				.permitAll());
		
		http.csrf(csrf ->csrf.disable());
		
		return http.build();
	}
}
