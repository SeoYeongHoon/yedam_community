package com.yedam.app.yedam_examstudent.web;

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
			               HttpSession session, 
			               Model model) {
		//HttpSession session = req.getSession();
		String logid ="14"; //(String) session.getAttribute("logid");
		List<TestVO> list = cbtStudentService.testListAll(logid);
		model.addAttribute("testList", list); //시험전체목록
		model.addAttribute("logid", logid); //로그인정보
		return "cbt_student/testList2";
	}
	
	//시험단건(상세)정보 조회
	@GetMapping("testDetail")
	public String testDetail(TestVO testVO, 
			                 QuizboxVO quizboxVO, 
			                 Model model) {
		TestVO list1 = cbtStudentService.testDetail(testVO); //시험번호 저장됨
		List<QuizboxVO> list2 = cbtStudentService.testQuizRand(quizboxVO); //시험번호 저장됨
		int quizCnt = list2.size(); //문제개수
		int[] randQuizId = new int[quizCnt]; //랜덤 문제 번호 배열
		int[] randRn = new int[quizCnt]; //랜덤 문제 일련번호 배열
		int[] randQuizScore = new int[quizCnt]; //랜덤 문제 배점 배열
		int i = 0;
		for(QuizboxVO quiz : list2) { //랜덤 문제 목록에 값 꺼내기		
			randQuizId[i] = quiz.getQuizId(); //랜덤으로 생성된 문제의 번호 저장
			randRn[i] = quiz.getRn(); //랜덤으로 생성된 문제의 일련번호 저장
			randQuizScore[i] = quiz.getQuizScore(); //랜덤으로 생성된 문제의 배점 저장
			i++;
		}
		model.addAttribute("testDetail", list1); //시험 상세정보 전달
		model.addAttribute("randQuizId", randQuizId); //문제 번호 전달
		model.addAttribute("randRn", randRn); //문제 일련번호 전달
		model.addAttribute("randQuizScore", randQuizScore); //문제 배점 전달
		return "cbt_student/testDetail";
	}
	//시험시작 페이지 >> 시작정보, 문제내용, 문제보기
	@PostMapping("testStart")
	public String testStart(int page,  
			                int[] randQuizId, 
			                int[] randRn, 
			                int[] randQuizScore, 
			                TestVO testVO, 
			                QuizboxVO quizboxVO, 
			                AnswerVO answerVO, 
			                Model model) {
		TestVO list1 = cbtStudentService.testStart(testVO); //시험시작정보
		quizboxVO.setQuizId(randQuizId[0]); //해당 시험의 문제번호 저장
		quizboxVO.setTestId(list1.getTestId()); //해당 시험 번호 저장
		quizboxVO.setSubjectId(list1.getSubjectId()); //해당 과목 번호 저장
		answerVO.setQuizId(randQuizId[0]); //해당 시험의 문제번호 저장
		answerVO.setTestId(list1.getTestId()); //해당 시험 번호 저장
		answerVO.setSubjectId(list1.getSubjectId()); //해당 과목 번호 저장
		QuizboxVO list2 = cbtStudentService.testQuiz1(quizboxVO); //시험문제
		List<AnswerVO> list3 = cbtStudentService.testQuiz2(answerVO); //문제보기
		model.addAttribute("testStart", list1); //시험 응시정보 전달
		model.addAttribute("testQuiz1", list2); //시험 문제 내용정보 전달
		model.addAttribute("testQuiz2", list3); //시험 문제 보기정보 전달
		model.addAttribute("page", page); //현재 페이지 번호 전달
		model.addAttribute("randQuizId", randQuizId); //문제번호 전달
		model.addAttribute("randRn", randRn); //문제일련번호 전달
		model.addAttribute("randQuizScore", randQuizScore); //문제일련번호 전달
		return "cbt_student/testStart";
	}
	//문제갱신 ajax
	@ResponseBody
	@GetMapping("testStart")
	public List<TestVO> testStart(@RequestParam("page") int page, 
								  @RequestParam("testId") int testId, 
								  @RequestParam("subjectId") int subjectId,
								  @RequestParam("quizCnt") int quizCnt, 
								  @RequestParam("randQuizId") int[] randQuizId, 
								  @RequestParam("randRn") int[] randRn,
								  TestVO testVO){
		int i = page - 1;
		testVO.setQuizId(randQuizId[i]); //해당 시험의 문제번호 저장
		testVO.setTestId(testId); //해당 시험 번호 저장
		testVO.setSubjectId(subjectId); //해당 과목 번호 저장
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
		boolean result = false;
		examResultVO.setTestId(testId);
		cbtStudentService.testSubmit2(examResultVO); //시험결과 정보등록
		//List<TestResultVO> list = new ArrayList<>();
		for(int i = 0; i < randRn.length; i++) { //전체 문제 개수만큼 반복
			testResultVO.setTestAnswer(submitAnswer[i]); //문제 답안 저장
			testResultVO.setQuizId(randQuizId[i]); //문제 번호 저장
			testResultVO.setTestId(testId);
			testResultVO.setResultId(examResultVO.getResultId());
			//list.add(testResultVO);
			result = cbtStudentService.testSubmit(testResultVO); //문제 등록
		}
		cbtStudentService.testSubmit3(examResultVO); // 총점 업데이트
		return result;
	}
	
	//시험결과
	@GetMapping("testResult")
	public String testResult(ExamResultVO examResultVO, 
			                 int testId, 
			                 int min, 
			                 int sec, 
			                 Model model) {
		examResultVO.setTestId(testId); //파라미터로 받은 시험번호 저장
		ExamResultVO info = cbtStudentService.testResult(examResultVO);
		model.addAttribute("testResult",info); //시험결과 정보 전달
		model.addAttribute("testMin", min); //응시시간 분 전달
		model.addAttribute("testSec", sec); //응시시간 초 전달
		return "cbt_student/testResult";
	}
}