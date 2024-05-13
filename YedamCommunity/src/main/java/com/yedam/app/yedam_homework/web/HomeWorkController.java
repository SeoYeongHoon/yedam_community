package com.yedam.app.yedam_homework.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yedam.app.yedam_homework.service.HomeWorkService;
import com.yedam.app.yedam_homework.service.HomeWorkVO;

@Controller
public class HomeWorkController {

	@Value("${file.upload.path}")
    private String uploadPath;
	
	@Autowired
	HomeWorkService heomworkService;// 서비스 불러옴

	// 과제 전체조회
	@GetMapping("homeworkList")
	public String homeworkList(Model model) {
		List<HomeWorkVO> list = heomworkService.homeworkList();
		model.addAttribute("homeworklist", list);
		return "homework/hlist_teacher"; // 출력할 페이지
	}
	
	// 과제 등록 - 페이지
	@GetMapping("insert_homework")
	public String homeworkInsertForm(Model model) {
		model.addAttribute("homeworkVO",new HomeWorkVO());
		return "homework/insert_homework";
	} 
	
	// 과제 등록 -처리
	@PostMapping("insert_homework")
	public String homeworkInsertProcess(HomeWorkVO homeworkVO) {
		heomworkService.homeworkInsert(homeworkVO);
		return "redirect:homeworkList";
	}
	
	// 과제상세페이지
	@GetMapping("homework_detail")
	public String homework(Model model) {
		return "homework/homework_detail";
	}
	
	

}
