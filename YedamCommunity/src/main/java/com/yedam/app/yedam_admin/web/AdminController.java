package com.yedam.app.yedam_admin.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
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
import org.springframework.web.multipart.MultipartFile;

import com.yedam.app.yedam_admin.service.AdminService;
import com.yedam.app.yedam_common.PageDTO;
import com.yedam.app.yedam_common.SecurityUtils;
import com.yedam.app.yedam_curriculum.service.CurriculumService;
import com.yedam.app.yedam_curriculum.service.CurriculumVO;
import com.yedam.app.yedam_post.service.ReportVO;
import com.yedam.app.yedam_user.service.RegisterVO;
import com.yedam.app.yedam_user.service.UserService;
import com.yedam.app.yedam_user.service.UserVO;
import com.yedam.app.yedam_user.upload.service.ProfileImageService;

@Controller
@EnableScheduling
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UserService userService;
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	CurriculumService curriculumService;
	
	@Autowired
	ProfileImageService profileImageService;
	
	//ㅡㅡㅡㅡ
	// 과정 기간 끝나면 수강생 -> 수료생 자동 변경
	// 매 자정마다 함수 실행
	//ㅡㅡㅡㅡ
	@Scheduled(cron = "0 0 0 * * *")
	@GetMapping("/checkUserType")
	public void checkUserType() {
		userService.updateUserType();
	}
	
	// 어드민 메인 페이지 및 회원가입 요청 목록
	@GetMapping("/adminMain")
	public String adminPage(Model model, CurriculumVO curriculumVO) {
		if (!SecurityUtils.hasRole("ROLE_ADMIN")) {
            return "error/access";
        }
		System.out.println(curriculumVO.getCurriculumEndDate());
		List<UserVO> requestList = userService.stdList();
		List<CurriculumVO> currList = curriculumService.CurriculumList();
		model.addAttribute("requests", requestList);
		model.addAttribute("class", currList);
	    
	    return "admin/adminMain";
	}
	
	// 유저 필터링 및 페이징
	@GetMapping("/filterUsers")
	@ResponseBody
	public Map<String, Object> filterUsers(@RequestParam(defaultValue = "1") int page, 
	                                       @RequestParam(defaultValue = "showAll") String filter,
	                                       @RequestParam(defaultValue = "") String searchQuery,
	                                       Model model) {
	    List<UserVO> users = userService.getUsersByFilter(filter, page, searchQuery);
	    int totalCnt = userService.userTotalCnt(filter, searchQuery);
	    
	    
	    PageDTO pageDTO = new PageDTO(page, totalCnt, 5);

	    Map<String, Object> response = new HashMap<>();
	    response.put("users", users);
	    response.put("page", pageDTO);
	    
	    return response;
	}


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

//	// 회원가입 신청(Register 테이블에 임시등록)
	@GetMapping("/insertUser")
	@ResponseBody
	public int insertUser(@RequestParam("registerId") int registerId) {
		return userService.approveUser(registerId);
	}

	// 신청한 유저들 중 승인한 것 Users 테이블로 이동
	@PostMapping("/insertCheckedUsers")
	public String insertCheckedUsers(@RequestBody Map<String, List<String>> requestBody) {
		List<String> registerIds = requestBody.get("registerIds");
		userService.insertCheckedUsers(registerIds);

		return "admin/adminMain";
	}
	
	// 과정 정보 CSV 파일 등록
	@PostMapping("/uploadCsv")
	public ResponseEntity<List<Map<String, String>>> handleFileUpload(@RequestParam("file") MultipartFile file) {
		List<Map<String, String>> data = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
			 CSVParser csvParser = CSVFormat.DEFAULT.builder()
													.setHeader()
													.setSkipHeaderRecord(true)
													.build()
													.parse(reader)) {
			
				for (CSVRecord csvRecord : csvParser) {
					Map<String, String> row = new HashMap<>();
					row.put("stdName", csvRecord.get("이름"));
					row.put("stdPhone", csvRecord.get("전화번호"));
					row.put("stdEmail", csvRecord.get("이메일"));
					data.add(row);
					System.out.println("ROW 데이터: " + row);
					userService.insertTempUsers(row);
				}
				
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		System.out.println("데이터: " + data);
		return ResponseEntity.ok(data);
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
	public String addCourse(MultipartFile[] uploadFiles, CurriculumVO curriculumVO, Model model) {
		System.err.println("커리큘럼 VO = "+curriculumVO);
		
	    try {
	      
	    	System.out.println("데이터 입력");
	        List<String> imageList = profileImageService.uploadFile(uploadFiles);
            if (!imageList.isEmpty()) {
                String imagePath = imageList.get(0);
                curriculumVO.setProfileImageLocation(imagePath);
                MultipartFile profileImage = uploadFiles[0];
                curriculumVO.setProfileImageName(profileImage.getOriginalFilename());
                curriculumVO.setProfileImageSize((int) profileImage.getSize());
                curriculumVO.setProfileImageExt(profileImage.getOriginalFilename().substring(profileImage.getOriginalFilename().lastIndexOf(".") + 1));
                curriculumVO.setDownloadLocation(imagePath);
                System.out.println("데이터 입력 완료");
            }
            curriculumService.insertCurriculum(curriculumVO);
	        
	        model.addAttribute("message", "과정이 성공적으로 등록되었습니다.");
	        return "redirect:/admin/course";
	    } catch (Exception e) {
	        model.addAttribute("message", "과정 등록에 실패했습니다.");
	        return "admin/course";
	    }
	}
	
//	모달창에 해당 과정의 학생 리스트 출력
	@GetMapping("/showCourse")
	@ResponseBody
	public List<UserVO> showCourseStd(@RequestParam("curriculumId") int curriculumId) {
		List<UserVO> students = curriculumService.showCurriculumStd(curriculumId);
		
		return students;
	}
	
	// 과정 삭제
	@DeleteMapping("/course/{curriculumId}")
	@ResponseBody
	public boolean deleteCourse(@PathVariable("curriculumId") int curriculumId) {
		return curriculumService.removeCurriculum(curriculumId);
	}
	
	
//	신고목록 페이지 및 항목 출력
	@GetMapping("/manageReport")
	public String manageReport(Model model) {
		List<ReportVO> reportList = adminService.getReportList();
		model.addAttribute("reports", reportList);
		
		return "admin/manageReport";
	}
}
