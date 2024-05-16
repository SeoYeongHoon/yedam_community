package com.yedam.app.yedam_examstudent.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	private static int[] quiz;
	private static int i = 0;
	//시험시작 페이지 >> 시작정보, 문제내용, 문제보기
	@GetMapping("testStart")
	public String testStart(@RequestParam int page, TestVO testVO, QuizboxVO quizboxVO, AnswerVO answerVO,  Model model) {
		int quizCnt = cbtStudentService.quizCnt(quizboxVO); //시험문제개수
		TestVO list1 = cbtStudentService.testStart(testVO); //시험응시정보
		QuizboxVO list2 = cbtStudentService.testQuiz1(quizboxVO); //시험문제관련
		quiz = new int[quizCnt]; //문제 개수만큼 배열 크기 초기화
//		if(quiz.length < 20) {				
//			for(int num : quiz) { //배열 내 존재여부 확인
//				if(num != list2.getQuizId()) {
//					quiz[i] = list2.getQuizId(); //배열에 추가
//					if(i < 19) { //배열 인덱스 증가
//						i++;
//					}
//				}
//					while(num == list2.getQuizId()) {
//						list2 = cbtStudentService.testQuiz1(quizboxVO); //시험문제관련
//					}
//			}
//		}
		
		
		if(quiz.length < 20) {
			while(Arrays.asList(quiz).contains(list2.getQuizId())) {
				list2 = cbtStudentService.testQuiz1(quizboxVO); //시험문제관련
			}
			quiz[i] = list2.getQuizId(); //배열에 추가
			if(i < 19) { //배열 인덱스 증가
				i++;
			}
		}
		System.out.println(quiz[2]);
		System.out.println(quiz[1]);
		answerVO.setQuizId(list2.getQuizId()); //시험문제번호 넘기기
		List<AnswerVO> list3 = cbtStudentService.testQuiz2(answerVO); //시험보기관련
		model.addAttribute("testStart", list1);
		model.addAttribute("testQuiz1", list2);
		model.addAttribute("testQuiz2", list3);
		model.addAttribute("quizCnt", quizCnt);
		model.addAttribute("page", page); //페이지번호 파라미터
		return "cbt_student/testStart";
	}
}