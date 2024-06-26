package com.yedam.app.yedam_user.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yedam.app.yedam_common.LoginUserVO;
import com.yedam.app.yedam_common.SecurityUtils;
import com.yedam.app.yedam_examstudent.service.CbtStudentService;
import com.yedam.app.yedam_examstudent.service.TestVO;
import com.yedam.app.yedam_homework.service.HomeWorkService;
import com.yedam.app.yedam_homework.service.HomeWorkVO;
import com.yedam.app.yedam_homework.service.ReplyVO;
import com.yedam.app.yedam_post.service.PostReplyVO;
import com.yedam.app.yedam_post.service.PostService;
import com.yedam.app.yedam_post.service.PostVO;
import com.yedam.app.yedam_user.service.UserService;
import com.yedam.app.yedam_user.service.UserVO;
import com.yedam.app.yedam_user.upload.service.ProfileImageService;


@Controller
public class MyPageController {
	@Autowired
	UserService userService;
	
	@Autowired
	HomeWorkService homeWorkService;
	
	@Autowired
	ProfileImageService profileImageService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	CbtStudentService cbtStudentService;
	
	@Autowired
	PostService postService;
	
	@GetMapping("/mypage")
	public String myPage(TestVO testVO,
						 HomeWorkVO homeWorkVO,
						 PostVO postVO,
						 Model model) {
		
		int logid = SecurityUtils.getCurrentLogId();
		
		// 내 게시글 출력
		List<PostVO> myPostList = postService.getMyPost(logid);
		model.addAttribute("myPost", myPostList);
		
		// 내 댓글 출력
		List<PostReplyVO> myReplyList = postService.getMyReply(logid);
		model.addAttribute("myReply", myReplyList);
		
		// 내 시험 출력
		testVO.setUserId(logid);
		testVO.setPage(1);
		List<TestVO> testList = cbtStudentService.testListAll(testVO);
		model.addAttribute("recentTest", testList);
		
		// 내 과제 출력
		List<HomeWorkVO> homeworkList = homeWorkService.getRecentTest(logid);
		model.addAttribute("recentHomework", homeworkList);
		
		return "mypage/mypage";
	}
	
	// 개인정보 수정
	@PostMapping("/updateUserInfo")
	@ResponseBody
	public ResponseEntity<String> updateUser(@RequestParam("logid") int userId,
											 @RequestParam("id") String id,
											 @RequestParam("username") String name,
								             @RequestParam("userpassword") String password,
								             @RequestParam("useremail") String email,
								             @RequestParam("usertel") String tel,
								             @RequestParam("userpassword_confirm") String passwordConfirm,
								             MultipartFile[] uploadFiles,
								             RedirectAttributes rttr,
								             HttpServletRequest req) {
		
		UserVO userVO = new UserVO();
		userVO.setId(id);
		
		if (name != null && !name.isEmpty()) {
			userVO.setName(name);
        }
        if (password != null && !password.isEmpty()) {
        	if (!password.equals(passwordConfirm)) {
        		rttr.addFlashAttribute("pwError", "비밀번호가 서로 다릅니다.");
        		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비밀번호가 서로 다릅니다.");
        	}
        	String encodedPw = passwordEncoder.encode(password);
    		userVO.setPassword(encodedPw);
        }
        if (email != null && !email.isEmpty()) {
        	userVO.setEmail(email);
        }
        if (tel != null && !tel.isEmpty()) {
        	userVO.setTel(tel);
        }
		
        if (uploadFiles != null && uploadFiles.length > 0 && !uploadFiles[0].isEmpty()) {
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
        } else {
            // 새로운 이미지가 업로드되지 않았을 때 기존 이미지 정보를 유지
            UserVO userOrigin = userService.getByUserId(userVO.getId());
            userVO.setProfileImageLocation(userOrigin.getProfileImageLocation());
            userVO.setProfileImageName(userOrigin.getProfileImageName());
            userVO.setProfileImageSize(userOrigin.getProfileImageSize());
            userVO.setProfileImageExt(userOrigin.getProfileImageExt());
            userVO.setDownloadLocation(userOrigin.getDownloadLocation());
        }
        
        userService.updateUserInfo(userVO);
        return ResponseEntity.ok("Success");
	}
	
	// 회원 탈퇴
	@GetMapping("/quitUser")
	public String quitUserRequest(int userId) {
		userService.removeUser(userId);
		
		return "redirect:/all/login";
	}
}
