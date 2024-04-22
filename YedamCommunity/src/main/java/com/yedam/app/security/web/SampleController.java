package com.yedam.app.security.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {
	@GetMapping("all")
	public void exAll() {}
	
	@GetMapping("admin")
	public void exAdmin() {}
	
	@GetMapping("user")
	public void exUser() {}
}
