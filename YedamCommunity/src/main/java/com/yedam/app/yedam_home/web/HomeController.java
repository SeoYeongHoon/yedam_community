package com.yedam.app.yedam_home.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.app.yedam_post.service.PostService;


@Controller
public class HomeController {
	
	@Autowired
	PostService postservice;
	
	@GetMapping("/home")
	public String homePage() {
		
		return "mainPages/home";
	}
}
