package com.yedam.app.yedam_home.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yedam.app.yedam_post.service.PostService;
import com.yedam.app.yedam_post.service.PostVO;


@Controller
public class HomeController {
	
	@Autowired
	PostService postservice;
  
	@GetMapping("/all/home")
	public String homePage() {
		
		return "mainPages/home";
	}
	
//	@GetMapping("/all/home")
//	public String BoardList(Model model) {
//		List<PostVO> list = postservice.getPostAll();
//		
//		model.addAttribute(list);
//		return "mainPages/home";
//	}
}
