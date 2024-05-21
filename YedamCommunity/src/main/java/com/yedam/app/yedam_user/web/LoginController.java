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
import com.yedam.app.yedam_user.upload.mapper.ProfileImageMapper;
import com.yedam.app.yedam_user.upload.service.ProfileImageVO;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	
	@Autowired
	ProfileImageMapper profileImageMapper;
	
	@GetMapping("/")
	public String loginPage(HttpServletRequest req) {
		HttpSession session = req.getSession();
	    String logid = (String) session.getAttribute("logid");
	    if (logid != null) {
	    	return "redirect:/home";
	    }
		
		return "login/loginForm";
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
    public String loginPOST(HttpServletRequest req, UserVO userVO, RedirectAttributes rttr, Model model) throws Exception{
        
		String id = req.getParameter("id");
		String pw = req.getParameter("password");
		System.out.println(id);
	    System.out.println(pw);
        
	    userVO.setId(id);
	    userVO.setPassword(pw);
	    
	    userVO = userService.loginCheck(userVO);
	    
	    if (userVO != null) {
	    	HttpSession session = req.getSession();
	    	session.setAttribute("logid", id);
	    	session.setAttribute("logPw", pw);
	    	session.setAttribute("logName", userVO.getName());
	    	session.setAttribute("logType", userVO.getUserType());
	    	
	    	ProfileImageVO profileImageVO = profileImageMapper.getProfileImageByLogId(id);
	    	if (profileImageVO != null) {
                session.setAttribute("logImage", profileImageVO.getProfileImageName());
                System.out.println("유저 이미지: " + profileImageVO.getProfileImageName());
            } else {
                session.setAttribute("logImage", null);
                System.out.println("유저 이미지가 없습니다.");
            }
	    	
	    	model.addAttribute("session", session);
	    	
	    	if (userVO.getUserType().equals("ROLE_ADMIN")) {
		    	System.out.println("관리자 로그인");
		    	return "redirect:/adminMain";
	    	} else {
	    		System.out.println("로그인 성공");
		    	return "redirect:/home";
	    	}
	    } else {
	    	System.out.println("로그인 실패");
	    	return "redirect:/";
	    }
    }
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		System.out.println("로그아웃 성공");
		return "redirect:/";
	}
	
//	아이디 찾기 페이지 및 기능
	@PostMapping("/showId")
	public String showId(@ModelAttribute UserVO userVO, RedirectAttributes rttr, Model model) {
		System.out.println("전달된 데이터: " + userVO.getId());
		
		UserVO list = userService.findUserId(userVO);
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
