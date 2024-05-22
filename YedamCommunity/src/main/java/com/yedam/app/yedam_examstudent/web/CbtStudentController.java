package com.yedam.app.yedam_examstudent.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.yedam_examstudent.service.AnswerVO;
import com.yedam.app.yedam_examstudent.service.CbtStudentService;
import com.yedam.app.yedam_examstudent.service.ExamResultVO;
import com.yedam.app.yedam_examstudent.service.QuizboxVO;
import com.yedam.app.yedam_examstudent.service.TestResultVO;
import com.yedam.app.yedam_examstudent.service.TestVO;

@Controller
public class CbtStudentController {
	
	//service주입
	@Autowired
	CbtStudentService cbtStudentService;
	
	//시험전체목록 조회
	@GetMapping("testList2")
	public String testList(TestVO testVO, 
			               ExamResultVO examResultVO, 
			               HttpSession session, 
			               Model model) {
		//HttpSession session = req.getSession();
		String logid ="14"; //(String) session.getAttribute("logid");
		List<TestVO> list1 = cbtStudentService.testListAll(logid);
		model.addAttribute("testList", list1); //시험전체목록
		model.addAttribute("logid", logid); //시험전체목록
		return "cbt_student/testList2";
	}
	
	//시험단건(상세)정보 조회
	@GetMapping("testDetail")
	public String testDetail(TestVO testVO, QuizboxVO quizboxVO, Model model) {
		//int quizCnt = cbtStudentService.quizCnt(quizboxVO);
		TestVO list = cbtStudentService.testDetail(testVO);
		//quizboxVO.setTestId(list.getTestId()); //시험 번호 저장
		//quizboxVO.setSubjectId(list.getSubjectId()); //과목 번호 저장
		//int testId = list.getTestId();
		//int subjectId = list.getSubjectId();
		List<QuizboxVO> list2 = cbtStudentService.testQuizRand(quizboxVO);
		int quizCnt = list2.size();
		int[] randQuizId = new int[quizCnt]; //랜덤 문제 번호를 배열로 저장
		int[] randRn = new int[quizCnt]; //랜덤 문제 일련번호를 배열로 저장
		int[] randQuizScore = new int[quizCnt]; //랜덤 문제 배점 배열로 저장
		int i = 0;
		for(QuizboxVO quiz : list2) { //랜덤 문제 목록에 값 꺼내기		
			randQuizId[i] = quiz.getQuizId(); //랜덤으로 생성된 문제의 번호 저장
			randRn[i] = quiz.getRn(); //랜덤으로 생성된 문제의 일련번호 저장
			randQuizScore[i] = quiz.getQuizScore(); //랜덤으로 생성된 문제의 배점 저장
			i++;
		}
		model.addAttribute("testDetail", list); //시험 상세정보 전달
		//model.addAttribute("quizCnt", quizCnt); //시험 문제개수 전달
		//model.addAttribute("testId", testId); //시험 번호 전달
		//model.addAttribute("subjectId", subjectId); //과목 번호 전달
		model.addAttribute("randQuizId", randQuizId); //문제 번호 전달
		model.addAttribute("randRn", randRn); //문제 일련번호 전달
		model.addAttribute("randQuizScore", randQuizScore); //문제 배점 전달
		return "cbt_student/testDetail";
	}
	
	//시험시작 페이지 >> 시작정보, 문제내용, 문제보기
	@PostMapping("testStart")
	public String testStart(int page, 
			                int quizCnt, 
			                int testId, 
			                int subjectId, 
			                int[] randQuizId, 
			                int[] randRn, 
			                int[] randQuizScore, 
			                TestVO testVO, 
			                QuizboxVO quizboxVO, 
			                AnswerVO answerVO, 
			                Model model) {
		TestVO list1 = cbtStudentService.testStart(testVO);
		//int i = page - 1;
	//	for(int rn : randRn) { //랜덤 문제 목록을 일련번호와 비교
	//		if(rn == page) { //현재 페이지 번호와 일련번호가 같을 때
				quizboxVO.setQuizId(randQuizId[0]); //해당 시험의 문제번호 저장
				quizboxVO.setTestId(testId); //해당 시험 번호 저장
				quizboxVO.setSubjectId(subjectId); //해당 과목 번호 저장
				answerVO.setQuizId(randQuizId[0]); //해당 시험의 문제번호 저장
				answerVO.setTestId(testId); //해당 시험 번호 저장
				answerVO.setSubjectId(subjectId); //해당 과목 번호 저장
		//	}
		//	i++;
		//}
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
		model.addAttribute("randQuizScore", randQuizScore); //문제일련번호 전달
		return "cbt_student/testStart";
	}
	//문제갱신 ajax
	@ResponseBody
	@GetMapping("testStart")
	public List<TestVO> testStart(@RequestParam("testId") int testId, 
			@RequestParam("subjectId") int subjectId, 
			@RequestParam("page") int page, 
			@RequestParam("randQuizId") int[] randQuizId, 
			@RequestParam("randRn") int[] randRn, 
			@RequestParam("quizCnt") int quizCnt, 
			TestVO testVO){
		int i = page - 1;
		//for(int rn : randRn) { //랜덤 문제 목록을 일련번호와 비교
		//	if(rn == page) { //현재 페이지 번호와 일련번호가 같을 때
				testVO.setQuizId(randQuizId[i]); //해당 시험의 문제번호 저장
				testVO.setTestId(testId); //해당 시험 번호 저장
				testVO.setSubjectId(subjectId); //해당 과목 번호 저장
		//	}
		//	if(i < quizCnt) {				
		//		i++;
		//	}
		//}
		return cbtStudentService.testQuiz(testVO);
	}
	
	//문제제출 ajax
	@ResponseBody
	@PostMapping("testStart1")
	public boolean testStart(@RequestParam("submitAnswer") int[] submitAnswer, 
							@RequestParam("randQuizId") int[] randQuizId,
							@RequestParam("randRn") int[] randRn,
							@RequestParam("randQuizScore") int[] randQuizScore,
							int testId, 
							TestResultVO testResultVO,
							ExamResultVO examResultVO) {
		//시험결과 정보등록
		cbtStudentService.testSubmit2(examResultVO);
		boolean a = false;
		
		//List<TestResultVO> list = new ArrayList<>();
		for(int i = 0; i < randRn.length; i++) { //전체 문제 개수만큼 반복
			testResultVO.setTestAnswer(submitAnswer[i]); //문제 답안 저장
			testResultVO.setQuizId(randQuizId[i]); //문제 번호 저장
			testResultVO.setTestId(testId);
			testResultVO.setResultId(examResultVO.getResultId());
			//list.add(testResultVO);
			a = cbtStudentService.testSubmit(testResultVO); //문제 등록
		}
		
		// 총점 업데이트
		cbtStudentService.testSubmit3(examResultVO);
		return a;
	}
	
	
	@GetMapping("testResult")
	public String testResult(ExamResultVO examResultVO, 
			                 int testId, 
			                 int min, 
			                 int sec, 
			                 Model model) {
		examResultVO.setTestId(testId); //파라미터로 받은 시험번호 저장
		ExamResultVO info = cbtStudentService.testResult(examResultVO);
		/*
		boolean insert1 = false;
		boolean insert2 = false;
		boolean insert3 = false;
		if(cbtStudentService.testSubmit(testResultVO)) { //답안 저장 성공하면
			if(cbtStudentService.testSubmit2(examResultVO)) { //시험 결과 정보 등록 성공하면
				if(cbtStudentService.testSubmit3(examResultVO)) { //재시험 유무 수정 성공하면
					insert1 = true;
					insert2 = true;
					insert3 = true;
				}
			}
		}*/
		model.addAttribute("testResult",info); //시험결과 정보 전달
		model.addAttribute("testMin", min); //응시시간 분 전달
		model.addAttribute("testSec", sec); //응시시간 초 전달
		//model.addAttribute("insert1", insert1); //응시시간 초 전달
		//model.addAttribute("insert2", insert2); //응시시간 초 전달
		//model.addAttribute("insert3", insert3); //응시시간 초 전달
		return "cbt_student/testResult";
	}
}