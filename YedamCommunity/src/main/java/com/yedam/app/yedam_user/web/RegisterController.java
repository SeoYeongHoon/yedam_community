package com.yedam.app.yedam_user.web;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@RequestMapping(value="register", method=RequestMethod.POST)
    public String registerPost(HttpServletRequest req, UserVO userVO, RedirectAttributes rttr) throws Exception{
        
		System.out.println("Register 메서드 진입");
        System.out.println("전달된 데이터 : " + userVO);
        
        if(userService.insertUser(userVO) > 0) {
            System.out.println("데이터 저장");
            return "redirect:/";
        } else {
        	
        }
        
        return "redirect:/";
    }
}
