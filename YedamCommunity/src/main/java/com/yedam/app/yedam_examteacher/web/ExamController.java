package com.yedam.app.yedam_examteacher.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.yedam_examteacher.service.ExamService;
import com.yedam.app.yedam_examteacher.service.TeacherVO;

@Controller
public class ExamController {

	@Autowired
	ExamService examService;
	
	// 등록된 시험리스트 출력
	@GetMapping("testlist")
	public String examList(Model model) {
		List<TeacherVO> list = examService.testList();
		model.addAttribute("testList", list);
		return "cbt_teacher/testlist";
	}
	
	// 시험 등록 - 페이지
	@GetMapping("testinsert")
	public String testInsertForm(TeacherVO teacherVO, Model model) {
		List<TeacherVO> list = examService.userList(teacherVO);
		model.addAttribute("teacherVO", new TeacherVO());
		model.addAttribute("userList", list);
		return "cbt_teacher/insertTest";
	}
	
	// 문제 등록 - 페이지
	@GetMapping("quizinsert")
	public String quizInsertForm(Model model) {
		List<TeacherVO> list = examService.subjectList();
		model.addAttribute("subjectList", list);
		model.addAttribute("teacherVO", new TeacherVO());
		return "cbt_teacher/insertquiz";
	}

	// 문제 객관식 등록 - 처리
	@PostMapping("quizinsert")
	public String quizInsertProcess(TeacherVO teacherVO) {
		examService.quizInsert(teacherVO);
		return "redirect:quizlist";
	}
	
	// 문제 주관식 등록 - 처리
	@PostMapping("quizinsertJu")
	public String quizInsertProcessJu(TeacherVO teacherVO) {
		examService.quizInsertJu(teacherVO);
		return "redirect:quizlist";
	}

	// 문제 저장소 페이지
	@GetMapping("quizlist")
	public String quizList(TeacherVO teacherVO, Model model) {
		
		List<TeacherVO> findVO = examService.answerList1(teacherVO);
		List<TeacherVO> list3 = examService.subjectList();

		model.addAttribute("answerList", findVO);
		model.addAttribute("subjectList", list3);
		System.out.println(list3);
		return "cbt_teacher/quiz";
	}
	
	// 등록된 문제 필터링해서 출력
	@PostMapping("quizlist")
	@ResponseBody
	public List<TeacherVO> quizList(@RequestParam("sName") String sName) {
		return examService.getQuizFilter(sName);
	}
	
	// 문제에 대한 지문들 출력
	@PostMapping("textcontent")
	@ResponseBody
	public List<TeacherVO> textContentList(@RequestParam("qId") int qId){
		return examService.answerList(qId);
	}

	// 문제 단건조회
	@PostMapping("quizinfo")
	@ResponseBody
	public List<TeacherVO> quizInfo(@RequestParam("qId") int qId) {
		return examService.getQuizInfo(qId);
	}

	// 과목 추가 - 처리
	@PostMapping("subjectinsert")
	public String subjectInsertProcess(TeacherVO teacherVO) {
		int sId = examService.subjectInsert(teacherVO);
		String uri = null;
		if (sId > -1) {
			uri = "redirect:quizlist";
		} else {
			uri = "quizlist";
		}
		return uri;
	}

	// 과목 삭제
	@GetMapping("subjectdelete")
	public String subjectDelete(TeacherVO teacherVO) {
		examService.subjectDelete(teacherVO);
		return "redirect:quizlist";
	}
	
	// 교수님 메인페이지 (강의실별 평균점수,시험리스트,학생)
	@GetMapping("teachermain")
	public String teacherMain(Model model) {
		return "cbt_teacher/teacherMain";
	}
	
	// 강의실별 시험정보 페이지
	@GetMapping("testinfo")
	public String classInfoForm(TeacherVO teacherVO, Model model) {
		TeacherVO findVO = examService.testInfo(teacherVO);
		model.addAttribute("testInfo", findVO);
		return "cbt_teacher/testinfo";
	}
	
	// 강의실별 학생 개인의 과정정보 및 성적조회 페이지
	@GetMapping("userTestInfo")
	public String userTestInfo(TeacherVO teacherVO, Model model) {
		TeacherVO findVO = examService.userTestInfo(teacherVO);
		model.addAttribute("userTestInfo", findVO);
		return "cbt_teacher/userTestInfo";
	}
	
	// 학생 개개인의 과목별 점수 조회 기능
	@PostMapping("userScore")
	@ResponseBody
	public List<TeacherVO> userScore(@RequestParam("userId") int uId){
		return examService.userScoreList(uId);
	}
	
	// 강의실별 시험 결과(통과자,재시험자) 확인
	@PostMapping("testresult")
	@ResponseBody
	public List<TeacherVO> testResult(@RequestParam("testId") int tId){
		return examService.userTestResult(tId);
	}
	
	// 교수님 페이지 - 강의실에 따른 과정명 출력
	@PostMapping("classinfo")
	@ResponseBody
	public List<TeacherVO> classInfo(@RequestParam("classId") int cId) {
		return examService.classList(cId);
	}
	
	// 교수님 페이지 - 강의실별 수강생 정보
	@PostMapping("userinfo")
	@ResponseBody
	public List<TeacherVO> userInfo(@RequestParam("classId") int cId) {
		return examService.subUserList(cId);
	}
	
	// 교수님 페이지 - 강의실별 시험 정보
	@PostMapping("testinfo")
	@ResponseBody
	public List<TeacherVO> testInfo(@RequestParam("classId") int cId) {
		return examService.subTestList(cId);
	}
	
	// 교수님 페이지 - 강의실별 시험 평균점수
	@PostMapping("subjectavg")
	@ResponseBody
	public List<TeacherVO> subjectAvg(@RequestParam("classId") int cId) {
		return examService.subjectAvg(cId);
	}
	

}
