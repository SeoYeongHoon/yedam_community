package com.yedam.app.yedam_examteacher.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.app.yedam_examteacher.service.ExamService;
import com.yedam.app.yedam_examteacher.service.TeacherVO;

@Controller
public class ExamController {
	
	@Autowired
	ExamService examService;
	
	// 등록된 시험리스트
	@GetMapping("testlist")
	public String examList(Model model) {
		List<TeacherVO> list = examService.testList();
		model.addAttribute("testList",list);
		return "cbt_teacher/testlist";
	}
	
	// 문제 등록 - 페이지
	@GetMapping("quizinsert")
	public String quizInsertForm(Model model) {
		model.addAttribute("teacherVO", new TeacherVO());
		return "cbt_teacher/insertquiz";
	}
	
	// 문제 등록 - 처리
	@PostMapping("quizinsert")
	public String quizInsertProcess(TeacherVO teacherVO) {
		examService.quizInsert(teacherVO);
		return "redirect:quizlist";
	}
	
	// 문제 저장소에 여러가지 값들 출력
	@GetMapping("quizlist")
	public String quizList(TeacherVO teacherVO, Model model) {
		List<TeacherVO> list1 = examService.quizList();
		List<TeacherVO> list2 = examService.answerList(teacherVO);
		List<TeacherVO> list3 = examService.subjectList();
		
		TeacherVO findVO = examService.quizInfo(teacherVO);
		model.addAttribute("quizInfo",findVO);
		
		Gson gson = new GsonBuilder().create();
		String json1 = gson.toJson(list1);	
		
		model.addAttribute("quizList",json1);
		model.addAttribute("answerList",list2);
		model.addAttribute("subjectList",list3);
		model.addAttribute("teacherVO", new TeacherVO());
		return "cbt_teacher/quiz";
	}
	// 등록된 문제 출력
	@PostMapping("quizlist")
	@ResponseBody
	public List<TeacherVO> quizList(TeacherVO teacherVO){
		return examService.quizList();
	}

	// 문제 단건조회를 문제 세부보기 모달창으로 적용시켜야 한다
	@GetMapping("quizinfo")
	@ResponseBody
	public String quizInfo(TeacherVO teacherVO){
		TeacherVO qInfo = examService.quizInfo(teacherVO);
		
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(qInfo);	
		
		return json;
	}
	
	// 과목 추가 - 처리
	@PostMapping("subjectinsert")
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
