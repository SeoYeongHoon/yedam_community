package com.yedam.app.yedam_common.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/errorPages")
public class ErrorController {
	
	@GetMapping("/quit")
	public String quitForm() {
		return "errorPages/quit";
	}
	
	@GetMapping("/access")
	public String accessDeniedForm() {
		return "errorPages/access";
	}
}
