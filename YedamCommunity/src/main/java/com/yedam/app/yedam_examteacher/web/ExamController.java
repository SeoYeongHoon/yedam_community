package com.yedam.app.yedam_examteacher.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yedam.app.yedam_examteacher.service.ExamService;
import com.yedam.app.yedam_examteacher.service.TeacherVO;

@Controller
public class ExamController {
	
	@Autowired
	ExamService examService;
	
	// 등록된 시험리스트
	@GetMapping("/testlist")
	public String examList(Model model) {
		List<TeacherVO> list = examService.testList();
		model.addAttribute("testList",list);
		return "cbt_teacher/testlist";
	}
	
	// 문제 등록 - 페이지
	@GetMapping("/quizinsert")
	public String quizInsertForm(Model model) {
		model.addAttribute("teacherVO", new TeacherVO());
		return "cbt_teacher/insertquiz";
	}
	
	// 문제 등록 - 처리
	@PostMapping("/quizinsert")
	public String quizInsertProcess(TeacherVO teacherVO) {
		examService.quizInsert(teacherVO);
		return "redirect:quizlist";
	}
	
	// 문제 저장소에 여러가지 값들 출력
	@GetMapping("/quizlist")
	public String quizList(Model model) {
		List<TeacherVO> list = examService.quizList();
		List<TeacherVO> list2 = examService.answerList();
		List<TeacherVO> list3 = examService.subjectList();
		model.addAttribute("quizList",list);
		model.addAttribute("answerList",list2);
		model.addAttribute("subjectList",list3);
		System.out.println(list);
		return "cbt_teacher/quiz";
	}

	// 문제 단건조회를 문제 세부보기 모달창으로 적용시켜야 한다
	@GetMapping("/quizinfo")
	public String quizInfo(TeacherVO teacherVO, Model model) {
		TeacherVO findVO = examService.quizInfo(teacherVO);
		model.addAttribute("quizInfo",findVO);
		return "cbt_teacher/quizinfo";
	}
	
	// 과목 추가 - 페이지
	@GetMapping("/subjectinsert")
	public String subjectInsertForm(Model model) {
		model.addAttribute("teacherVO", new TeacherVO());
		return "cbt_teacher/quiz";
	}
	
	// 과목 추가 - 처리
	@PostMapping("/subjectinsert")
	public String subjectInsertProcess(TeacherVO teacherVO) {
		int sId = examService.subjectInsert(teacherVO);
		String uri = null;
		if(sId > -1) {
			uri = "redirect:quizlist";
		} else {
			uri = "testlist";
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
