package com.yedam.app.yedam_examteacher.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.app.yedam_examteacher.service.ExamService;
import com.yedam.app.yedam_examteacher.service.teacherVO;

@Controller
public class ExamController {
	
	@Autowired
	ExamService examService;
	
	// 등록된 시험리스트
	@GetMapping("/examlist")
	public String examList(Model model) {
		List<teacherVO> list = examService.examList();
		model.addAttribute("examlist",list);
		return "cbt_teacher/examlist";
	}
}
