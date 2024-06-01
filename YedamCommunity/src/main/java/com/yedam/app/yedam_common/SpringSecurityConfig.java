package com.yedam.app.yedam_common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
//@EnableWebSecurity
public class SpringSecurityConfig {
	
	@Autowired
	private AuthenticationFailureHandler customAuthenticationFailureHandler;

	// 암호화
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// 인증 및 인가 설정
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
			.authorizeHttpRequests(requests -> requests
//				.antMatchers("/**").permitAll()
				.antMatchers("/adminMain/**").hasRole("ADMIN")
				.antMatchers("/all/home").authenticated()
				.antMatchers("/all/**").permitAll()
				.antMatchers("/css/**", "/fonts/**", "/images/**", "/js/**", "/vendor/**").permitAll()
				.anyRequest().authenticated())
			.formLogin(login -> login
				.loginPage("/all/login").permitAll()
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/all/home", true)
				.failureHandler(customAuthenticationFailureHandler)
				)
			.logout(logout -> logout
				.logoutSuccessUrl("/all/login")
				.permitAll());
		
		http.csrf(csrf -> csrf.disable());
		
		return http.build();
	}
}
