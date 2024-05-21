package com.yedam.app.yedam_home.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/home")
	public String homePage(HttpServletRequest req) {
		HttpSession session = req.getSession();
	    String logid = (String) session.getAttribute("logid");
	    System.out.println("로그인 아이디: " + logid);
	    
	    if (logid == null) {
	    	return "redirect:/";
	    }
		
		return "mainPages/home";
	}
}
