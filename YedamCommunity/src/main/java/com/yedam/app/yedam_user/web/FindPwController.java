package com.yedam.app.yedam_user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yedam.app.yedam_user.service.UserService;

@Controller
public class FindPwController {
	@Autowired
	UserService userService;
	
	@GetMapping("/findPw")
	public String findPwPage() {
		return "login/findPw";
	}
	
	@PostMapping("/sendPasswordResetEmail")
	public ResponseEntity<String> sendPasswordResetEmail(@RequestParam String id,
														 @RequestParam String name,
														 @RequestParam String email) {
		System.out.println("아이디: " + id);
		System.out.println("이름: " + name);
		System.out.println("아이디: " + email);
		
		try {
			userService.sendPasswordResetEmail(id, name, email);
			return ResponseEntity.ok("패스워드 초기화 메일을 보냈습니다");
		} catch (RuntimeException e) {
			return ResponseEntity.status(404).body(e.getMessage());
		}
		
		
		
	}
}
