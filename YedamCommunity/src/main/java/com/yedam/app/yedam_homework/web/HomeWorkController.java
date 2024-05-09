package com.yedam.app.yedam_homework.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yedam.app.yedam_homework.service.HomeWorkService;
import com.yedam.app.yedam_homework.service.HomeWorkVO;

@Controller
public class HomeWorkController {

	@Autowired
	HomeWorkService hService;// 서비스 불러옴
	
	//과제 전체조회
	@GetMapping("homeworkList")
	public String homeworkList(Model model) {
		return "homework/hlist_teacher"; // 출력할 페이지
	}
	
	//과제 등록
	@GetMapping("insert_homework")
	public String homeworkInsert(Model model) {
		return "homework/insert_homework";
	}
	
}
