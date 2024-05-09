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
		model.addAttribute("testlist",list);
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
	
	// 문제 저장소에 문제 출력
	@GetMapping("/quizlist")
	public String quizList(Model model) {
		List<TeacherVO> list = examService.quizList();
		model.addAttribute("quizlist",list);
		return "cbt_teacher/quiz";
	}
	
	// 문제 저장소에 지문 출력
}