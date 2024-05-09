package com.yedam.app.yedam_cbt.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.app.yedam_cbt.service.CbtStudentService;
import com.yedam.app.yedam_cbt.service.TestVO;

@Controller
public class CbtStudentController {
	
	// service 주입
	@Autowired
	CbtStudentService cbtStudentService;

	@GetMapping("testList")
	public String testList(Model model) {
		List<TestVO> list = cbtStudentService.testListAll();
		model.addAttribute("testList", list);
		return "cbt_student/cbt_main";
	}
}
