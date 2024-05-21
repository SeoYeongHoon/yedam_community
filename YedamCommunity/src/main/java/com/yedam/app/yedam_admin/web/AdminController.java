package com.yedam.app.yedam_admin.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.yedam_curriculum.service.CurriculumService;
import com.yedam.app.yedam_curriculum.service.CurriculumVO;
//import com.yedam.app.yedam_common.PageDTO;
import com.yedam.app.yedam_user.service.RegisterVO;
import com.yedam.app.yedam_user.service.UserService;
import com.yedam.app.yedam_user.service.UserVO;

@Controller
public class AdminController {

	@Autowired
	UserService userService;
	
	@Autowired
	CurriculumService curriculumService;
	
//	@Autowired
//	AdminService adminService;

	// 어드민 메인 페이지 및 유저 전체조회
	@GetMapping("/adminMain")
	public String adminPage(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession();
	    String logid = (String) session.getAttribute("logid");
	    System.out.println("로그인 아이디: " + logid);
	    
	    if (logid == null) {
	    	return "login/loginForm";
	    }

	    if (!logid.equals("admin")) {
	        return "redirect:/home";
	    }
	    
		// 관리자 최초 페이지. 수강생, 수료생 리스트 출력.
		List<UserVO> requestList = userService.stdList();
		List<UserVO> userList = userService.userList();
		model.addAttribute("requests", requestList);
		model.addAttribute("users", userList);

		return "admin/adminMain";
	}

	// 수강생 or 수료생 or 전체 필터링
	@GetMapping("/filterUsers")
	@ResponseBody
	public List<UserVO> filterUsers(@RequestParam("filter") String filter, Model model) {
		List<UserVO> users = userService.getUsersByFilter(filter);
		model.addAttribute("users", users);

		return users;
	}

	// 회원가입 신청한 유저 상세 정보
	@GetMapping("/reqDetails")
	@ResponseBody
	public RegisterVO getReqDetails(@RequestParam("registerId") String registerId) {
		return userService.getReqById(registerId);
	}

	// 회원 상세 정보
	@GetMapping("/userDetails")
	@ResponseBody
	public UserVO getUserDetails(@RequestParam("userId") String userId) {
		return userService.getUserById(userId);
	}

	// 회원가입 신청 거절(삭제)
	@GetMapping("/refuseUser")
	@ResponseBody
	public boolean refuseUser(@RequestParam("registerId") int registerId) {
		return userService.refuseUser(registerId);
	}

	// 회원 삭제
	@GetMapping("/removeUser")
	@ResponseBody
	public boolean removeUser(@RequestParam("userId") int userId) {
		return userService.removeUser(userId);
	}

	// 회원가입 신청(Register 테이블에 임시등록)
	@GetMapping("/insertUser")
	@ResponseBody
	public int insertUser(@RequestParam("registerId") int registerId) {
		return userService.insertUser(registerId);
	}

	// 신청한 유저들 중 승인한 것 Users 테이블로 이동
	@PostMapping("/insertCheckedUsers")
	public String insertCheckedUsers(@RequestBody Map<String, List<String>> requestBody) {
		List<String> registerIds = requestBody.get("registerIds");
		userService.insertCheckedUsers(registerIds);

		return "admin/adminMain";
	}
	
	
//	과정 목록 출력
	@GetMapping("/manageCourse")
	public String manageCourse(Model model) {
		List<CurriculumVO> currList = curriculumService.CurriculumList();
		model.addAttribute("class", currList);
		
		return "admin/manageCourse";
	}
	
//	과정 등록
	@PostMapping("/manageCourse")
	public String addCourse(@ModelAttribute CurriculumVO curriculumVO) {
		System.out.println("전달된 데이터: " + curriculumVO);
		
		curriculumService.insertCurriculum(curriculumVO);
		
		return "redirect:/manageCourse";
	}
	
//	모달창에 해당 과정의 학생 리스트 출력
	@GetMapping("/showCourse")
	@ResponseBody
	public List<UserVO> showCourseStd(@RequestParam("curriculumId") int curriculumId, Model model) {
		System.out.println("아이디: " + curriculumId);
		List<UserVO> students = curriculumService.showCurriculumStd(curriculumId);
		model.addAttribute("students", students);
		System.out.println("학생정보: " + students);
		
		return students;
	}
	
	
//	신고목록 페이지
	@GetMapping("/manageReport")
	public String manageReport() {
		return "admin/manageReport";
	}
}
