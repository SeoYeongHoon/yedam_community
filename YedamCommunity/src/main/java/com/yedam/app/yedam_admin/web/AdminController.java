package com.yedam.app.yedam_admin.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.yedam_common.PageDTO;
import com.yedam.app.yedam_curriculum.service.CurriculumService;
import com.yedam.app.yedam_curriculum.service.CurriculumVO;
//import com.yedam.app.yedam_common.PageDTO;
import com.yedam.app.yedam_user.service.RegisterVO;
import com.yedam.app.yedam_user.service.UserService;
import com.yedam.app.yedam_user.service.UserVO;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UserService userService;
	
	@Autowired
	CurriculumService curriculumService;

	// 어드민 메인 페이지 및 유저 전체조회
	@GetMapping("/adminMain")
	public String adminPage(Model model, 
							@RequestParam(defaultValue = "showAll") String filter
							) {
		// 회원가입 신청 유저 리스트
		List<UserVO> requestList = userService.stdList();
		model.addAttribute("requests", requestList);
		
		PageDTO pageDTO = new PageDTO(1, userService.userTotalCnt(filter), 5);
		model.addAttribute("page", pageDTO);
		
		System.out.println("전체 페이지 정보: " + pageDTO);
		
		return "admin/adminMain";
	}
	
	// 유저 필터링 및 페이징
	@GetMapping("/filterUsers")
	@ResponseBody
	public List<UserVO> filterUsers(@RequestParam(defaultValue = "1") int page, 
									@RequestParam(defaultValue = "showAll") String filter, 
									Model model) {
		
		List<UserVO> users = userService.getUsersByFilter(filter, page);
		System.out.println("페이지 정보: " + page);
		model.addAttribute("users", users);
		
		return users;
	}

	// 수강생 or 수료생 or 전체 필터링
//	@GetMapping("/filterUsers")
//	@ResponseBody
//	public List<UserVO> filterUsers(String filter, Model model, HttpServletRequest req) {
//		String page = req.getParameter("page");
//		page = page == null ? "1" : page;
//		
//		int boardCountInPage = Integer.parseInt(page);
//		
//		PageDTO pageDTO = new PageDTO(Integer.parseInt(page), userService.userTotalCnt(), 5);
//		
//		List<UserVO> users = userService.getUsersByFilter(filter, boardCountInPage);
//		model.addAttribute("users", users);
//		req.setAttribute("page", pageDTO);
//		
//		System.out.println("유저 정보: " + users);
//		System.out.println("페이지 정보: " + pageDTO);
//
//		return users;
//	}
	

	// 회원가입 신청한 유저 상세 정보
	@GetMapping("/reqDetails")
	@ResponseBody
	public RegisterVO getReqDetails(@RequestParam("registerId") String registerId) {
		return userService.getReqInfoById(registerId);
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
	@GetMapping("/course")
	public String manageCourse(Model model) {
		List<CurriculumVO> currList = curriculumService.CurriculumList();
		model.addAttribute("class", currList);
		
		return "admin/manageCourse";
	}
	
//	과정 등록
	@PostMapping("/course")
	@ResponseBody
	public String addCourse( CurriculumVO curriculumVO) {
		System.out.println("전달된 데이터: " + curriculumVO);
		
		curriculumService.insertCurriculum(curriculumVO);
		
		return "redirect:/manageCourse";
	}
	
//	모달창에 해당 과정의 학생 리스트 출력
	@GetMapping("/showCourse")
	@ResponseBody
	public List<UserVO> showCourseStd(@RequestParam("curriculumId") int curriculumId) {
		System.out.println("아이디: " + curriculumId);
		List<UserVO> students = curriculumService.showCurriculumStd(curriculumId);
		System.out.println("학생정보: " + students);		
		
		return students;
	}
	
	// 과정 삭제
	@DeleteMapping("/course/{curriculumId}")
	@ResponseBody
	public boolean deleteCourse(@PathVariable("curriculumId") int curriculumId) {
		return curriculumService.removeCurriculum(curriculumId);
	}
	
	
//	신고목록 페이지
	@GetMapping("/manageReport")
	public String manageReport() {
		return "admin/manageReport";
	}
}
