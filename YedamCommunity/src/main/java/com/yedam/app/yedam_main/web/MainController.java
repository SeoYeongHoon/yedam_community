package com.yedam.app.yedam_main.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("/")
	public String homePage() {
		return "home";
		//classpath:/templates/home.html
	}
}
