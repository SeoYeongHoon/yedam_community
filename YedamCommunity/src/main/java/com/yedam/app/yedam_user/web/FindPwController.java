package com.yedam.app.yedam_user.web;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yedam.app.yedam_user.service.UserService;
import com.yedam.app.yedam_user.service.UserVO;

@Controller
public class FindPwController {
	@Autowired
	UserService userService;
	
	@GetMapping("/findPw")
	public String findPwPage() {
		return "login/findPw";
	}
	
	@PostMapping("/sendPasswordResetEmail")
	public ResponseEntity<String> sendPasswordResetEmail(@RequestParam String id,
														 @RequestParam String name,
														 @RequestParam String email,
														 HttpServletResponse res) throws IOException {
		System.out.println("아이디: " + id);
		System.out.println("이름: " + name);
		System.out.println("아이디: " + email);
		
		try {
			userService.sendPasswordResetEmail(id, name, email);
			String uri = "http://localhost:8080/mailSuccess";
			res.sendRedirect(uri);
			return ResponseEntity.ok().build();
//			return ResponseEntity.ok("패스워드 초기화 메일을 보냈습니다");
		} catch (RuntimeException e) {
			return ResponseEntity.status(404).body(e.getMessage());
		}
	}
	
	@GetMapping("/resetPw")
	public String resetPasswordForm(@RequestParam String token, Model model) {
		Optional<UserVO> userOptional = userService.findByResetToken(token);
		if (userOptional.isPresent()) {
            model.addAttribute("token", token);
            return "login/resetPw";
        } else {
            model.addAttribute("message", "유효하지 않은 토큰입니다.");
            return "error";
        }
	}
	
	@PostMapping("/resetPw")
	public String updatePassword(@RequestParam String resetToken,
								 @RequestParam String newPassword,
								 Model model) {
		try {
			userService.updatePassword(resetToken, newPassword);
			model.addAttribute("message", "비밀번호 변경 성공!");
			return "login/pwUpdateComplete";
		} catch(RuntimeException e) {
			model.addAttribute("message", e.getMessage());
            return "error/access"; // 오류 발생 시 오류 페이지로 이동
		}
	}
}
