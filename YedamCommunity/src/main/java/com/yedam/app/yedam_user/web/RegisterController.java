package com.yedam.app.yedam_user.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yedam.app.yedam_curriculum.service.CurriculumService;
import com.yedam.app.yedam_curriculum.service.CurriculumVO;
import com.yedam.app.yedam_user.service.UserService;
import com.yedam.app.yedam_user.service.UserVO;
import com.yedam.app.yedam_user.upload.service.ProfileImageService;


// 작성자: 서영훈, 작성일자: 05/22, 회원가입 컨트롤러
@Controller
@RequestMapping("/all")
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	ProfileImageService profileImageService;
	
	@Autowired
	CurriculumService curriculumService;
	
	@GetMapping("/register")
	public String registerPage(Model model) {
		List<CurriculumVO> courseList = curriculumService.allCurriculumList();
		model.addAttribute("courses", courseList);
		
		return "register/register";
	}
	
	@PostMapping("/register")
	public String registerRequest(MultipartFile[] uploadFiles, 
//			                     RegisterVO registerVO,
								 @RequestParam String courseSelect,
			                     UserVO userVO,
			                     Model model, 
			                     RedirectAttributes rttr, 
			                     HttpServletRequest req) {
		
		String password = req.getParameter("password");
		String passwordConfirm = req.getParameter("password-confirm");
//		System.out.println(password + ", " + passwordConfirm);
		
		System.out.println("SELECT 값: " + courseSelect);
		System.out.println("과정 이름 값: " + userVO.getCurriculumId());
		userVO.setCurriculumName(courseSelect);
		
		
//		System.out.println("업로드 파일: " + uploadFiles);
		
	    try {
	    	// 아이디 비번 확인
	    	boolean isExist = userService.isUserExist(userVO.getId());
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
                userVO.setProfileImageLocation(imagePath);
                MultipartFile profileImage = uploadFiles[0];
                userVO.setProfileImageName(profileImage.getOriginalFilename());
                userVO.setProfileImageSize((int) profileImage.getSize());
                userVO.setProfileImageExt(profileImage.getOriginalFilename().substring(profileImage.getOriginalFilename().lastIndexOf(".") + 1));
                userVO.setDownloadLocation(imagePath);
            }

            userService.registerUser(userVO);
            System.out.println("성공! " + userVO);
            return "register/registerComplete";
        } catch (Exception e) {
            model.addAttribute("error", "회원가입 실패! 다시 시도해주세요.");
            System.out.println("실패!!" + userVO.toString());
            System.out.println(e);
            return "redirect:/";
        }
	}
	
	@GetMapping("/registerComplete")
	public String registerCompletePage() {
		return "register/registerComplete";
	}
    
	// 휴대폰 인증
	@PostMapping("/sendVerificationEmail")
	public ResponseEntity<String> sendVarificationEmail(@RequestParam String email) {
		
		System.out.println("이메일:" + email);
		
		try {
			userService.sendVerificationEmail(email);
			return ResponseEntity.ok().build();
		} catch (RuntimeException e) {
			return ResponseEntity.status(404).body(e.getMessage());
		}
		
	}
	
	@PostMapping("/verifyCode")
	public ResponseEntity<String> verifyCode(@RequestParam String email,
											 @RequestParam String code) {
		System.out.println("verifyCode 메소드 실행");
		boolean isVerified = userService.verifyCode(email, code);
		System.out.println("서비스가 실행되었나?");
		if (isVerified) {
			System.out.println("인증성공");
			return ResponseEntity.ok("인증완료");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증실패");
		}
	}
	
//	@PostMapping("/verify-code")
//    public Map<String, Object> verifyCode(@RequestBody FindPhoneVO findPhoneVO) {
//        String phoneNumber = findPhoneVO.getPhoneNumber();
//        String verificationCode = findPhoneVO.getVerificationCode();
//
//        Map<String, Object> responseBody = new HashMap<>();
//        if (verificationCode.equals(verificationCodes.get(phoneNumber))) {
//            verificationCodes.remove(phoneNumber);
//            responseBody.put("success", true);
//        } else {
//            responseBody.put("success", false);
//        }
//        return responseBody;
//    }
//	
}
