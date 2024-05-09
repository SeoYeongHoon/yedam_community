package com.yedam.app.yedam_user.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {
	@GetMapping("/logout")
	public String loginPage(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		
		return "redirect:/";
		//classpath:/templates/login/login.html
	}
}
