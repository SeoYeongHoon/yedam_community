package com.yedam.app.yedam_admin.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.yedam.app.yedam_common.PageDTO;
import com.yedam.app.yedam_user.service.RegisterVO;
import com.yedam.app.yedam_user.service.UserService;
import com.yedam.app.yedam_user.service.UserVO;

@Controller
public class AdminController {
	
	@Autowired
	UserService userService;
	
	// 어드민 메인 페이지 및 유저 전체조회
	@GetMapping("/adminMain")
	public String adminPage(Model model, HttpServletRequest req) {
		
//		String page = req.getParameter("page");
//		page = page == null ? "1" : page;
//		int boardCountInPage = Integer.parseInt(page);
//		List<UserVO> list = userService.userList(boardCountInPage);
//		PageDTO pageDTO = new PageDTO(Integer.parseInt(page), userService.userTotalCnt(), 5);
//		
//		model.addAttribute("users", list);
//		System.out.println("위 리스트: " + list);
//		
//		req.setAttribute("page", pageDTO);
		
		// 관리자 최초 페이지. 수강생, 수료생 리스트 출력.
		List<UserVO> list = userService.stdList();
		model.addAttribute("users", list);
		
		return "admin/adminMain";
		// classpath:/templates/admin/adminMain.html
	}
	
	// 수강생 or 수료생 or 전체 필터링
	@GetMapping("/filterUsers")
	@ResponseBody
	public List<UserVO> filterUsers(@RequestParam("filter") String filter, Model model) {
		
		List<UserVO> users = userService.getUsersByFilter(filter);
		model.addAttribute("users", users);
		
		return users;
	}
	
	@GetMapping("/userDetails")
	@ResponseBody
	public RegisterVO getUserDetails(@RequestParam("registerId") String registerId) {
		return userService.getUserById(registerId);
	}
	
	@GetMapping("/deleteUser")
	@ResponseBody
	public boolean deleteUser(@RequestParam("registerId") int registerId) {
		return userService.removeUser(registerId);
	}
	
	@GetMapping("/insertUser")
	@ResponseBody
	public int insertUser(@RequestParam("registerId") int registerId) {
		return userService.insertUser(registerId);
	}
}
