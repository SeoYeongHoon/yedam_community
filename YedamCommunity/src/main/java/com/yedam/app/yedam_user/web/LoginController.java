package com.yedam.app.yedam_user.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yedam.app.yedam_user.service.UserService;
import com.yedam.app.yedam_user.service.UserVO;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String loginPage(HttpServletRequest req) {
		return "login/loginForm";
	}
	
//	아이디 찾기 페이지 및 기능
	@PostMapping("/showId")
	public String showId(@ModelAttribute UserVO userVO, RedirectAttributes rttr, Model model) {
		UserVO list = userService.findUserId(userVO);
		
		if (list == null) {
			rttr.addFlashAttribute("wrongInput", "해당하는 아이디가 없습니다! 다시 한 번 입력해주세요.");
			return "redirect:/findId";
		}
		
		model.addAttribute("idInfo", list);
		return "login/showId";
	}
	
	// 비밀번호 찾기 페이지 및 기능
	@PostMapping("/showPw")
	public String showPw(@ModelAttribute UserVO userVO, RedirectAttributes rttr, Model model) {
		System.out.println("전달된 데이터: " + userVO.getPassword());
		
		UserVO list = userService.findUserPw(userVO);
		model.addAttribute("pwInfo", list);
		return "login/showPw";
	}
	
//	@GetMapping("/mailSendSuccess")
//	public String mailSendSuccess() {
//		return "login/mailSendSuccess";
//	}
}
