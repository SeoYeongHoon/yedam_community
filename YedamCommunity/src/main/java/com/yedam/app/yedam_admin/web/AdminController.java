package com.yedam.app.yedam_admin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	@GetMapping("/admin")
	public String adminPage() {
		return "admin";
		//classpath:/templates/admin.html
	}
}
