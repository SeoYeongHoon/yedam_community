package com.yedam.app.yedam_home.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
	
	@GetMapping("/all/home")
	public String homePage() {
		
		return "mainPages/home";
	}
}
