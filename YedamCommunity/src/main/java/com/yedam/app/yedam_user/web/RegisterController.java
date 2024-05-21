package com.yedam.app.yedam_user.web;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yedam.app.yedam_user.service.RegisterVO;
import com.yedam.app.yedam_user.service.UserService;

@Controller
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/register")
	public String registerPage() {
		return "register/register";
		//classpath:/templates/register/register.html
	}
	
//	@PostMapping("/register")
//	public String registerRequest(@ModelAttribute("registerVO") RegisterVO registerVO, MultipartFile userImage, Model model) {
//		try {
////			MultipartFile userImage = registerVO.getUserImage();
////			registerVO.setUserImage(userImage);
//			userService.requestUser(registerVO, userImage);
//			System.out.println("성공! " + registerVO);
//			return "/registerComplete";
//		} catch (Exception e) {
//			model.addAttribute("error", "회원가입 실패! 다시 시도해주세요.");
//			System.out.println("실패!!" + registerVO.toString());
//			System.out.println(e);
//			return "redirect:/";
//		}
//	}
	@PostMapping("/register")
	public String registerRequest(RegisterVO registerVO, Model model, RedirectAttributes rttr, HttpServletRequest req) {
		String password = req.getParameter("password");
		String passwordConfirm = req.getParameter("password-confirm");
		System.out.println(password + ", " + passwordConfirm);
		
	    try {
	    	boolean isExist = userService.isUserExist(registerVO.getId());
	    	
	    	if (isExist) {
	    		System.out.println("이미 존재하는 아이디입니다.");
	    		rttr.addFlashAttribute("idError", "이미 존재하는 아이디입니다.");
	    		return "redirect:/register";
	    	}
	    	
	    	if (!password.equals(passwordConfirm)) {
	    		System.out.println("비밀번호가 서로 다릅니다.");
	    		rttr.addFlashAttribute("pwError", "비밀번호가 서로 다릅니다.");
	    		return "redirect:/register";
	    	}
	    	
	        userService.requestUser(registerVO);
	        System.out.println("성공! " + registerVO);
	        return "register/registerComplete";
	    } catch (Exception e) {
	        model.addAttribute("error", "회원가입 실패! 다시 시도해주세요.");
	        System.out.println("실패!!" + registerVO.toString());
	        System.out.println(e);
	        return "redirect:/";
	    }
	}
	
	@GetMapping("/registerComplete")
	public String registerCompletePage() {
		return "register/registerComplete";
	}
	
//	@RequestMapping(value="register", method=RequestMethod.POST)
//    public String registerPost(HttpServletRequest req, RegisterVO registerVO, RedirectAttributes rttr) throws Exception{
//        
//		System.out.println("Register 메서드 진입");
//        System.out.println("전달된 데이터 : " + registerVO);
//        
//        if(userService.requestUser(registerVO) > 0) {
//            System.out.println("데이터 저장");
//            return "register/registerComplete";
//        } else {
//        	
//        }
//        
//        return "redirect:/";
//    }
}
