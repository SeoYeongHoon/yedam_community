package com.yedam.app.yedam_user.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	public String registerRequest(RegisterVO registerVO, Model model) {
	    try {
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
