package com.yedam.app.yedam_user.web;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yedam.app.yedam_user.service.RegisterVO;
import com.yedam.app.yedam_user.service.UserService;
import com.yedam.app.yedam_user.service.UserVO;

//@RequestMapping("/user")
//@RequiredArgsConstructor
@Controller
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/register")
	public String registerPage() {
		return "register/register";
		//classpath:/templates/register/register.html
	}
	
	@GetMapping("/registerComplete")
	public String registerCompletePage() {
		return "register/registerComplete";
	}
	
	@RequestMapping(value="register", method=RequestMethod.POST)
    public String registerPost(HttpServletRequest req, RegisterVO registerVO, RedirectAttributes rttr) throws Exception{
        
		System.out.println("Register 메서드 진입");
        System.out.println("전달된 데이터 : " + registerVO);
        
        if(userService.requestUser(registerVO) > 0) {
            System.out.println("데이터 저장");
            return "register/registerComplete";
        } else {
        	
        }
        
        return "redirect:/";
    }
}
