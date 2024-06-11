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
	
	// 403 에러 페이지
	@GetMapping("/access")
	public String accessDeniedForm() {
		return "errorPages/access";
	}
	
	// 404 에러 페이지
	@GetMapping("/error404")
	public String noPageFound() {
		return "errorPages/error404";
	}
	
	// 500 에러 페이지
	@GetMapping("/error500")
	public String exceptionError() {
		return "errorPages/error500";
	}
}
