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
	public String testInsertForm(Model model) {
		model.addAttribute("teacherVO", new TeacherVO());
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

	// 문제 등록 - 처리
	@PostMapping("quizinsert")
	public String quizInsertProcess(TeacherVO teacherVO) {
		examService.quizInsert(teacherVO);
		return "redirect:quizlist";
	}

	// 문제 저장소 페이지
	@GetMapping("quizlist")
	public String quizList(TeacherVO teacherVO, Model model) {
		
		List<TeacherVO> list2 = examService.answerList1(teacherVO);
		List<TeacherVO> list3 = examService.subjectList();

		model.addAttribute("answerList", list2);
		model.addAttribute("subjectList", list3);
		
		return "cbt_teacher/quiz";
	}
	
	// 문제에 대한 지문들 출력
	@PostMapping("textcontent")
	@ResponseBody
	public List<TeacherVO> textContentList(@RequestParam("qId") int qId){
		return examService.answerList(qId);
	}

	// 등록된 문제 필터링해서 출력
	@PostMapping("quizlist")
	@ResponseBody
	public List<TeacherVO> quizList(@RequestParam("sName") String sName) {
		return examService.getQuizFilter(sName);
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
			uri = "quizlist";
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
}
