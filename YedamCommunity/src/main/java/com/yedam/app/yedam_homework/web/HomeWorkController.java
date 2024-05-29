package com.yedam.app.yedam_homework.web;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.app.yedam_common.PageDTO;
import com.yedam.app.yedam_curriculum.service.CurriculumService;
import com.yedam.app.yedam_curriculum.service.CurriculumVO;
import com.yedam.app.yedam_homework.service.CommentVO;
import com.yedam.app.yedam_homework.service.HomeWorkReplyService;
import com.yedam.app.yedam_homework.service.HomeWorkService;
import com.yedam.app.yedam_homework.service.HomeWorkTargetVO;
import com.yedam.app.yedam_homework.service.HomeWorkVO;
import com.yedam.app.yedam_homework.service.ReplyVO;
import com.yedam.app.yedam_homework.upload.service.HomeWorkFileService;
import com.yedam.app.yedam_homework.upload.service.HomeWorkFileVO;
import com.yedam.app.yedam_homework.upload.service.ReplyFileVO;
import com.yedam.app.yedam_subjects.service.SubjectService;

/**
 * 과제 관리
 * 
 * @author 전영재 개발 일자 202405-23
 * 
 */
@Controller
@RequestMapping("/admin")
public class HomeWorkController {

	// 로컬 저장 경로
	@Value("${file.upload.path}")
	private String uploadPath;

	// 서비스 호출
	@Autowired
	HomeWorkService homeworkService;

	@Autowired
	SubjectService subjectService;

	@Autowired
	HomeWorkReplyService homeworkReplyService;

	@Autowired
	HomeWorkFileService homeworkfileService;

	@Autowired
	CurriculumService curriculumService;

	// ----------------
	// 과제 목록(교수)
	// ----------------
	@GetMapping("/homework_T")
	//@RequestMapping("/admin")
	public String homework(Model model) {
		List<CurriculumVO> classList = curriculumService.CurriculumList();
		model.addAttribute("classId", classList);
		return "homework/homeworkList_t";
	}

	// 과제 목록 출력
	@GetMapping("/homeworkList_T")
	@ResponseBody
	//@RequestMapping("/admin")
	public List<HomeWorkVO> homeworkList(@RequestParam("homeworkAll") String homeworkAll) {
		List<HomeWorkVO> homeworkList = homeworkService.homeworkList();
		return homeworkList;
	}

	// 강의실 카테고리
	@GetMapping("/classCategory")
	@ResponseBody
	//@RequestMapping("/admin")
	public List<HomeWorkVO> classs(@RequestParam("vals") int classId) {
		List<HomeWorkVO> list = homeworkService.classCategory(classId);
		return list;
	}

	// 과제 삭제
	@DeleteMapping("/deleteHomeworks")
	@ResponseBody
	//@RequestMapping("/admin")
	public int deleteHomeworks(@RequestParam int homeworkId) {
		return homeworkService.homeworkDelete(homeworkId);
	}
	
	// ----------------
	// 과제 등록 - 페이지
	// ----------------
	@GetMapping("/homeworkInsert")
	//@RequestMapping("/admin")
	public String homeworkInsertForm(HomeWorkVO homeworkVO, Model model) {
		// 과목 조회
		List<HomeWorkVO> subjectNameList = homeworkService.subjectNameList(homeworkVO);
		model.addAttribute("subject", subjectNameList);
		model.addAttribute("homeworkVO", homeworkVO);
		return "homework/homeworkInsert";
	}

	// ----------------
	// 과제 등록 -처리
	// ----------------
	@PostMapping("/homeworkInsert")
	@ResponseBody
	//@RequestMapping("/admin")
	public String homeworkInsertProcess(@RequestPart MultipartFile[] uploadFiles, HomeWorkVO homeworkVO,
			HomeWorkTargetVO homeworktargetVO) {

		// 과제등록
		homeworkService.homeworkInsert(homeworkVO);

		// 타겟 등록
		HomeWorkTargetVO targetVo = homeworkService.homeworktargetList(homeworkVO);
		homeworktargetVO.setHomeworkId(targetVo.getHomeworkId());
		homeworktargetVO.setCurriculumId(targetVo.getCurriculumId());
		homeworkService.homeworkTargetInsert(homeworktargetVO);

		// 과제 파일 업로드
		homeworkfileService.homeworkUploadFile(uploadFiles, targetVo);

		return null;
	}

	// ----------------
	// 과제 수정페이지
	// ----------------
	@PutMapping("/updateHomework")
	@ResponseBody
	public Map<String, Object> updateHomework(HomeWorkVO homeworkVO) {
		
		return homeworkService.homeworkUpdate(homeworkVO);
	}
	
	// ----------------
	// 과제 필터링 및 페이징
	// ----------------
	@GetMapping("/filterHomeworks")
	@ResponseBody
	public Map<String, Object> filterHomeworks(@RequestParam("filter") int filter,
											   @RequestParam(defaultValue = "1") int page,
											   @RequestParam(defaultValue = "") String searchQuery) {
		
		List<HomeWorkVO> homeworks = homeworkService.getHomeworksByFilter(filter, page, searchQuery);
		int totalCnt = homeworkService.getTotalCnt(filter, searchQuery);
		
		PageDTO pageDTO = new PageDTO(page, totalCnt, 5);
		
		Map<String, Object> response = new HashMap<>();
		response.put("homeworks", homeworks);
		response.put("page", pageDTO);
		
		
		return response;
	}
	

	
	
}
