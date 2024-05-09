package com.yedam.app.yedam_start.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {
	@GetMapping("/")
	public String startPage() {
		return "mainPages/start";
		//classpath:/templates/start.html
	}
}
