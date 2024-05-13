package com.yedam.app.yedam_curriculum.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.app.yedam_curriculum.service.CurriculumService;
import com.yedam.app.yedam_curriculum.service.CurriculumVO;

@Controller
public class CurriculumController {

	@Autowired
	CurriculumService curriculumService;
	
	//강의실 전체 조회
	@GetMapping("selectClass")
	public String classList(Model model) {
		List<CurriculumVO> list = curriculumService.CurriculumList();
		model.addAttribute("class",list);
		return "curriculum/selectClass";
	}
}
