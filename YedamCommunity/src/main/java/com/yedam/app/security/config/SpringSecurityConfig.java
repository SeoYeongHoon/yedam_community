package com.yedam.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	// 암호화
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// 메모리상 인증정보 활용
//	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsService() {
		UserDetails user = User.builder().username("user1").password(passwordEncoder().encode("1234")).roles("USER").build();
		
		return new InMemoryUserDetailsManager(user);
	}
	
	// 인증 및 인가 설정
	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeHttpRequests()
		.antMatchers("/", "/all").permitAll()
		.antMatchers("/user/**").hasRole("USER")
		.antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin().defaultSuccessUrl("/all")
		.and()
		.logout().logoutSuccessUrl("/all");
		
//		httpSecurity.logout();
		httpSecurity.csrf();

		return httpSecurity.build();
	}
}
