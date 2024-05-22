package com.yedam.app.yedam_user.web;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yedam.app.yedam_user.service.RegisterVO;
import com.yedam.app.yedam_user.service.UserService;
import com.yedam.app.yedam_user.upload.service.ProfileImageService;

// 작성자: 서영훈, 작성일자: 05/22, 회원가입 컨트롤러
@Controller
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	ProfileImageService profileImageService;
	
	@GetMapping("/register")
	public String registerPage() {
		return "register/register";
	}
	
	@PostMapping("/register")
	public String registerRequest(MultipartFile[] uploadFiles, 
			                     RegisterVO registerVO, 
			                     Model model, 
			                     RedirectAttributes rttr, 
			                     HttpServletRequest req) {
		
		String password = req.getParameter("password");
		String passwordConfirm = req.getParameter("password-confirm");
		System.out.println(password + ", " + passwordConfirm);
		
		System.out.println("업로드 파일: " + uploadFiles);
		
	    try {
	    	// 아이디 비번 확인
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
	    	
	    	List<String> imageList = profileImageService.uploadFile(uploadFiles);
            if (!imageList.isEmpty()) {
                String imagePath = imageList.get(0);
                registerVO.setProfileImageLocation(imagePath);
                MultipartFile profileImage = uploadFiles[0];
                registerVO.setProfileImageName(profileImage.getOriginalFilename());
                registerVO.setProfileImageSize((int) profileImage.getSize());
                registerVO.setProfileImageExt(profileImage.getOriginalFilename().substring(profileImage.getOriginalFilename().lastIndexOf(".") + 1));
                registerVO.setDownloadLocation(imagePath);
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
}
