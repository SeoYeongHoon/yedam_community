package com.yedam.app.yedam_admin.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.app.yedam_common.PageDTO;
import com.yedam.app.yedam_user.service.UserService;
import com.yedam.app.yedam_user.service.UserVO;

@Controller
public class AdminController {
	
	@Autowired
	UserService userService;
	
	// 어드민 메인 페이지 및 유저 전체조회
	@GetMapping("/adminMain")
	public String adminPage(Model model, HttpServletRequest req) {
		
		String page = req.getParameter("page");
		page = page == null ? "1" : page;
		
		int boardCountInPage = Integer.parseInt(page);

		List<UserVO> list = userService.userList(boardCountInPage);
		
		PageDTO pageDTO = new PageDTO(Integer.parseInt(page), userService.userTotalCnt(), 5);
		
		model.addAttribute("users", list);
		
		req.setAttribute("page", pageDTO);
		
		return "admin/adminMain";
		// classpath:/templates/admin/adminMain.html
	}
}
