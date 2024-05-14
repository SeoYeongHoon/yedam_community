package com.yedam.app.yedam_examstudent.web;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.app.yedam_examstudent.service.AnswerVO;
import com.yedam.app.yedam_examstudent.service.CbtStudentService;
import com.yedam.app.yedam_examstudent.service.QuizboxVO;
import com.yedam.app.yedam_examstudent.service.TestVO;

@Controller
public class CbtStudentController {
	
	//service주입
	@Autowired
	CbtStudentService cbtStudentService;

	//시험전체목록 조회
	@GetMapping("testList")
	public String testList(Model model) {
		List<TestVO> list = cbtStudentService.testListAll();
		model.addAttribute("testList", list);
		return "cbt_student/testMain";
	}
	//시험단건(상세)정보 조회
	@GetMapping("testDetail")
	public String testDetail(TestVO testVO, Model model) {
		TestVO list = cbtStudentService.testDetail(testVO);
		model.addAttribute("testDetail", list);
		return "cbt_student/testDetail";
	}
	//시험시작 페이지 >> 시작정보, 문제내용, 문제보기
	@GetMapping("testStart")
	public String testStart(TestVO testVO, QuizboxVO quizboxVO, AnswerVO answerVO,  Model model) {
		TestVO list1 = cbtStudentService.testStart(testVO);
		QuizboxVO list2 = cbtStudentService.testQuiz1(quizboxVO);
		List<AnswerVO> list3 = cbtStudentService.testQuiz2(answerVO);
		//페이징관련
		int quizCnt = cbtStudentService.quizCnt(quizboxVO);
		int page = 1;
		//QuizPagination quizPagination = new QuizPagination();
		//quizPagination.pageInfo(quizCnt1);
		model.addAttribute("testStart", list1);
		model.addAttribute("testQuiz1", list2);
		model.addAttribute("testQuiz2", list3);
		model.addAttribute("quizCnt", quizCnt);
		model.addAttribute("page", page);
		return "cbt_student/testStart";
	}
}