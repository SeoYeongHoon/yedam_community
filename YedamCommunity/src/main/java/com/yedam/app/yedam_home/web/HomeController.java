package com.yedam.app.yedam_home.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/home")
	public String homePage() {
		
		return "mainPages/home";
	}
}
