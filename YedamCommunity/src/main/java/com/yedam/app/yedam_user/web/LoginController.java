package com.yedam.app.yedam_user.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yedam.app.yedam_user.service.UserService;
import com.yedam.app.yedam_user.service.UserVO;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String loginPage() {
		return "login/login";
		//classpath:/templates/login/login.html
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
    public String loginPOST(HttpServletRequest req, UserVO userVO, RedirectAttributes rttr) throws Exception{
        
		String id = req.getParameter("id");
		String pw = req.getParameter("password");
		System.out.println(id);
	    System.out.println(pw);
        
	    userVO.setId(id);
	    userVO.setPassword(pw);
	    
	    System.out.println("유저VO 1: " + userVO);
	    userVO = userService.loginCheck(userVO);
	    System.out.println("유저VO 2: " + userVO);
	    
	    if (userVO != null) {
	    	HttpSession session = req.getSession();
	    	session.setAttribute("logid", id);
	    	session.setAttribute("logPw", pw);
	    	session.setAttribute("logName", userVO.getName());
	    	System.out.println("로그인 성공");
	    	System.out.println(userVO);
	    	return "mainPages/home"; 
	    } else {
	    	System.out.println("로그인 실패");
	    	return "redirect:/";
	    }
    }
}