package com.yedam.app.yedam_examstudent.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yedam.app.yedam_examstudent.service.AnswerVO;
import com.yedam.app.yedam_examstudent.service.CbtStudentService;
import com.yedam.app.yedam_examstudent.service.ExamResultVO;
import com.yedam.app.yedam_examstudent.service.QuizboxVO;
import com.yedam.app.yedam_examstudent.service.TestVO;

@Controller
public class CbtStudentController {
	
	//service주입
	@Autowired
	CbtStudentService cbtStudentService;
	
	//시험전체목록 조회
	@GetMapping("testList")
	public String testList(TestVO testVO, ExamResultVO examResultVO, Model model) {
		List<TestVO> list1 = cbtStudentService.testListAll();
		model.addAttribute("testList", list1); //시험전체목록
		return "cbt_student/testMain";
	}
	
	//시험단건(상세)정보 조회
	@GetMapping("testDetail")
	public String testDetail(TestVO testVO, QuizboxVO quizboxVO, Model model) {
		int quizCnt = cbtStudentService.quizCnt(quizboxVO);
		TestVO list = cbtStudentService.testDetail(testVO);
		quizboxVO.setTestId(list.getTestId()); //시험 번호 저장
		quizboxVO.setSubjectId(list.getSubjectId()); //과목 번호 저장
		int testId = list.getTestId();
		int subjectId = list.getSubjectId();
		List<QuizboxVO> list2 = cbtStudentService.testQuizRand(quizboxVO);
		int[] randQuizId = new int[quizCnt]; //랜덤 문제 번호를 배열로 저장
		int[] randRn = new int[quizCnt]; //랜덤 문제 일련번호를 배열로 저장
		int i = 0;
		for(QuizboxVO quiz : list2) { //랜덤 문제 목록에 값 꺼내기		
			randQuizId[i] = quiz.getQuizId(); //랜덤으로 생성된 문제의 번호 저장
			randRn[i] = quiz.getRn(); //랜덤으로 생성된 문제의 일련번호 저장
			i++;
		}
		model.addAttribute("testDetail", list); //시험 상세정보 전달
		model.addAttribute("quizCnt", quizCnt); //시험 문제개수 전달
		model.addAttribute("testId", testId); //시험 번호 전달
		model.addAttribute("subjectId", subjectId); //과목 번호 전달
		model.addAttribute("randQuizId", randQuizId); //문제 번호 전달
		model.addAttribute("randRn", randRn); //문제일련번호 전달
		return "cbt_student/testDetail";
	}
	
	//시험시작 페이지 >> 시작정보, 문제내용, 문제보기
	@PostMapping("testStart")
	public String testStart(int page, int[] randQuizId, int[] randRn, int quizCnt, int testId, int subjectId, TestVO testVO, QuizboxVO quizboxVO, AnswerVO answerVO, Model model) {
		TestVO list1 = cbtStudentService.testStart(testVO);
		int i = 0;
		for(int rn : randRn) { //랜덤 문제 목록을 일련번호와 비교
			if(rn == page) { //현재 페이지 번호와 일련번호가 같을 때
				quizboxVO.setQuizId(randQuizId[i]); //해당 시험의 문제번호 저장
				quizboxVO.setTestId(testId); //해당 시험 번호 저장
				quizboxVO.setSubjectId(subjectId); //해당 과목 번호 저장
				answerVO.setQuizId(randQuizId[i]); //해당 시험의 문제번호 저장
				answerVO.setTestId(testId); //해당 시험 번호 저장
				answerVO.setSubjectId(subjectId); //해당 과목 번호 저장
			}
			i++;
		}
		QuizboxVO list2 = cbtStudentService.testQuiz1(quizboxVO);
		List<AnswerVO> list3 = cbtStudentService.testQuiz2(answerVO);
		model.addAttribute("testStart", list1); //시험 응시정보 전달
		model.addAttribute("testQuiz1", list2); //시험 문제 내용정보 전달
		model.addAttribute("testQuiz2", list3); //시험 문제 보기정보 전달
		model.addAttribute("quizCnt", quizCnt); //시험 문제개수 전달
		model.addAttribute("testId", testId); //시험 번호 전달
		model.addAttribute("subjectId", subjectId); //과목 번호 전달
		model.addAttribute("page", page); //현재 페이지 번호 전달
		model.addAttribute("randQuizId", randQuizId); //문제번호 전달
		model.addAttribute("randRn", randRn); //문제일련번호 전달
		return "cbt_student/testStart";
	}
}