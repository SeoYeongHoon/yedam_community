package com.yedam.app;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class Boot240417ApplicationTests {

//	@Test
//	void contextLoads() {
//	}
	
	// 암호화 테스트
	@Autowired
	PasswordEncoder passwordEncoder;
	
//	@Test
	public void testEncoder() {
		String password = "1234";
		
		String enPwd = passwordEncoder.encode(password); // encode: 암호화, decode: 복호화
		System.out.println("Encoded Password: " + enPwd);
		
		// 비밀번호가 제대로 암호화가 되었는지 확인. 다를 시 true
		boolean matchResult = passwordEncoder.matches(password, enPwd); // .matches(원래변수, 암호화된 변수);
		System.out.println("Match Result: " + matchResult);
	}
	
	@Autowired
	StringEncryptor jasyptStringEncryptor;
	
	@Test
	public void encryption() {
		String [] strs = {
				"net.sf.log4jdbc.sql.jdbcapi.DriverSpy",
				"jdbc:log4jdbc:oracle:thin:@127.0.0.1:1521:xe",
				"hr",
				"1234"
		};
		
		for (String str : strs) {
			String encStr = jasyptStringEncryptor.encrypt(str);
			System.out.println(encStr);
		}
	}
}
