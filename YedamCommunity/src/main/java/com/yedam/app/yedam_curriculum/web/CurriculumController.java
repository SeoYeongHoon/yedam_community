package com.yedam.app.yedam_curriculum.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.yedam_curriculum.service.CurriculumService;
import com.yedam.app.yedam_curriculum.service.CurriculumVO;

@Controller
@RequestMapping("/admin")
public class CurriculumController {

	@Autowired
	CurriculumService curriculumService;
	
	//강의실 전체 조회 - 과제등록
	@GetMapping("/selectClass")
	public String classList(Model model) {
		List<CurriculumVO> list = curriculumService.CurriculumList();
		model.addAttribute("class",list);
		return "curriculum/selectClass";
	}
	
	//강의실 전체 조회 - 문제등록
	@GetMapping("/selectClassExam")
	public String classListExam(Model model) {
		List<CurriculumVO> list = curriculumService.CurriculumList();
		model.addAttribute("class",list);
		return "curriculum/selectClassExam";
	}
	
	//강의실별 과목명 조회
	@GetMapping("/selectSubject")
	@ResponseBody
	public List<CurriculumVO> selectSubject(CurriculumVO curriculumVO) {
		List<CurriculumVO> list = curriculumService.classSubject(curriculumVO);
		return list;
	}
}
