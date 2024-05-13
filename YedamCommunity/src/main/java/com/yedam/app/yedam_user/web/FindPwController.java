package com.yedam.app.yedam_user.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FindPwController {
	@GetMapping("/findPw")
	public String findPwPage() {
		return "login/findPw";
	}
}
