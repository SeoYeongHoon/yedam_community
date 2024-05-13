package com.yedam.app.yedam_homework.web;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.app.yedam_homework.service.HomeWorkService;
import com.yedam.app.yedam_homework.service.HomeWorkVO;

@Controller
public class HomeWorkController {

	@Value("${file.upload.path}")
    private String uploadPath;
	
	@Autowired
	HomeWorkService hService;// 서비스 불러옴

	// 과제 전체조회
	@GetMapping("homeworkList")
	public String homeworkList(Model model) {
		List<HomeWorkVO> list = hService.homeworkList();
		model.addAttribute("hlist", list);
		return "homework/hlist_teacher"; // 출력할 페이지
	}
	
	// 강의실 선택 페이지
	@GetMapping("selectClass")
	public String selectClass(Model model) {
		
		return "homework/selectClass";
	}
	
	// 과제 등록 - 페이지
	@GetMapping("insert_homework")
	public String homeworkInsertForm(Model model) {
		model.addAttribute("homeworkVO",new HomeWorkVO());
		return "homework/insert_homework2";
	} 
	
	// 과제 등록 -처리
	@PostMapping("insert_homework")
	public String homeworkInsertProcess(HomeWorkVO homeworkVO) {
		hService.homeworkInsert(homeworkVO);
		return "redirect:homeworkList";
	}
	
	// 과제상세페이지
	@GetMapping("homework_detail")
	public String homework(Model model) {
		return "homework/homework_detail";
	}
	
	
	/*
	 * // 파일 업로드를 처리하는 메서드
	 * 
	 * @PostMapping("/formUpload") public String
	 * formUploadFile(@RequestParam("file") MultipartFile file) { // 업로드된 파일이 비어있거나
	 * 이미지가 아닌 경우 에러 처리 if (file.isEmpty() ||
	 * !file.getContentType().startsWith("image")) {
	 * System.out.println("이미지가 아니거나 유효하지 않은 파일입니다"); return "error"; // 에러 페이지
	 * 반환하거나 적절히 처리 }
	 * 
	 * String fileName = file.getOriginalFilename(); String savePath = uploadPath +
	 * File.separator + fileName;
	 * 
	 * try { file.transferTo(new File(savePath)); // 파일이 성공적으로 저장됨 } catch
	 * (IOException e) { e.printStackTrace(); // 파일 저장 중 오류 처리 return null; }
	 * 
	 * return "homework/insert_homework2"; }
	 */
}
