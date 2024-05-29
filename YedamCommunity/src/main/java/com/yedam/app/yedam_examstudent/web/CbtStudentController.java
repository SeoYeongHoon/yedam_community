package com.yedam.app.yedam_examstudent.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.yedam_common.LoginUserVO;
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

	
	//ㅡㅡㅡㅡ
	//시험전체
	//ㅡㅡㅡㅡ
	@GetMapping("testList2")
	public String testList(int page,
						   TestVO testVO,
						   ExamResultVO examResultVO,
			               Authentication authentication,
			               Model model) {
		//HttpSession session = req.getSession();
		//LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal(); //세션값 가져오기
		int logid = 14;
		//int logid = userVO.getuserId();
		testVO.setUserId(logid);
		testVO.setPage(page);
		List<TestVO> list1 = cbtStudentService.testListAll(testVO); //시험목록
		List<ExamResultVO> list2 = new ArrayList<>();
		int size = cbtStudentService.testListSize(logid); //시험개수
		int[] array1 = new int[list1.size()];
		int[] array3 = new int[list1.size()];
		Date date = new Date();
		for(int i = 0; i < list1.size(); i++) {			
			testVO.setUserId(logid);
			testVO.setTestId(list1.get(i).getTestId());
			array1[i] = (cbtStudentService.isTestResult(testVO));
			testVO.setUserId(logid);
			testVO.setTestId(list1.get(i).getTestId());
			list2.add(cbtStudentService.isTestFeedback(testVO));
			if(date.after(list1.get(i).getTestDate())) {
				array3[i] = 1;
			}
			else {
				array3[i] = 0;
			}
		}
		model.addAttribute("testList", list1); //시험목록
		model.addAttribute("isResult", array1); //시험결과유무
		model.addAttribute("isFeedback", list2); //피드백유무
		model.addAttribute("dateComp", array3); //응시기간 유효성
		model.addAttribute("page", page); //페이지
		model.addAttribute("size", size); //시험개수
		model.addAttribute("logId", logid); //로그인정보
		return "cbt_student/testList2";
	}
	
	
	
	//ㅡㅡㅡㅡ
	//시험단건
	//ㅡㅡㅡㅡ
	@GetMapping("testDetail")
	public String testDetail(TestVO testVO, 
			                 QuizboxVO quizboxVO,
			                 Authentication authentication,
			                 Model model) {
		//LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal(); //세션값 가져오기
		int logid = 14;
		//int logid = userVO.getuserId();
		TestVO info = cbtStudentService.testDetail(testVO); //시험상세
		List<QuizboxVO> list = cbtStudentService.testQuizRand(quizboxVO); //랜덤문제
		int quizCnt = list.size(); //문제개수
		int[] randQuizId = new int[quizCnt]; //랜덤 문제 번호 배열
		int[] randRn = new int[quizCnt]; //랜덤 문제 일련번호 배열
		int[] randQuizScore = new int[quizCnt]; //랜덤 문제 배점 배열
		int i = 0;
		for(QuizboxVO quiz : list) { //랜덤 문제 목록에 값 꺼내기		
			randQuizId[i] = quiz.getQuizId(); //랜덤으로 생성된 문제의 번호 저장
			randRn[i] = quiz.getRn(); //랜덤으로 생성된 문제의 일련번호 저장
			randQuizScore[i] = quiz.getQuizScore(); //랜덤으로 생성된 문제의 배점 저장
			i++;
		}
		int isResult = 0;
		int dateComp = 0;
		Date date = new Date();
		testVO.setUserId(logid);
		testVO.setTestId(info.getTestId());
		isResult = (cbtStudentService.isTestResult(testVO));
		if(date.after(info.getTestDate())) {
			dateComp = 1;
		}
		else {
			dateComp = 0;
		}
		model.addAttribute("testDetail", info); //상세정보
		model.addAttribute("randQuizId", randQuizId); //문제 번호
		model.addAttribute("randRn", randRn); //문제 일련번호
		model.addAttribute("randQuizScore", randQuizScore); //문제 배점
		model.addAttribute("isResult", isResult); //시험결과 유무
		model.addAttribute("dateComp", dateComp); //날짜 비교
		return "cbt_student/testDetail";
	}
	
	
	
	//ㅡㅡㅡㅡ
	//시험시작
	//ㅡㅡㅡㅡ
	@PostMapping("testStart")
	public String testStart(int page, 
			                int[] randQuizId, 
			                int[] randRn, 
			                int[] randQuizScore, 
			                TestVO testVO, 
			                QuizboxVO quizboxVO, 
			                AnswerVO answerVO, 
			                Authentication authentication,
			                Model model) {
		//LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal(); //세션값 가져오기
		int logid = 14;
		//int logid = userVO.getuserId();
		testVO.setUserId(logid);
		TestVO info = cbtStudentService.testStart(testVO); //응시정보
		quizboxVO.setQuizId(randQuizId[0]);
		quizboxVO.setTestId(info.getTestId());
		quizboxVO.setSubjectId(info.getSubjectId());
		QuizboxVO quiz = cbtStudentService.testQuiz(quizboxVO); //시험문제
		model.addAttribute("testStart", info); //응시정보
		model.addAttribute("testQuiz", quiz); //시험문제 내용정보
		model.addAttribute("page", page); //현재 페이지번호
		model.addAttribute("randQuizId", randQuizId); //문제번호 전달
		model.addAttribute("randRn", randRn); //문제 일련번호
		model.addAttribute("randQuizScore", randQuizScore); //문제 배저
		return "cbt_student/testStart";
	}
	//ㅡㅡㅡㅡㅡㅡㅡㅡ
	//문제갱신 AJAX
	//ㅡㅡㅡㅡㅡㅡㅡㅡ
	@ResponseBody
	@GetMapping("testStart")
	public QuizboxVO testStart(@RequestParam("page") int page, 
								  @RequestParam("testId") int testId, 
								  @RequestParam("subjectId") int subjectId,
								  @RequestParam("quizCnt") int quizCnt, 
								  @RequestParam("randQuizId") int[] randQuizId, 
								  @RequestParam("randRn") int[] randRn,
								  QuizboxVO quizboxVO){
		int i = page - 1;
		quizboxVO.setQuizId(randQuizId[i]); //해당 문제번호
		quizboxVO.setTestId(testId); //해당 시험번호
		quizboxVO.setSubjectId(subjectId); //해당 과목번호
		return cbtStudentService.testQuiz(quizboxVO);
	}
	//ㅡㅡㅡㅡㅡㅡㅡㅡ
	//문제제출 AJAX
	//ㅡㅡㅡㅡㅡㅡㅡㅡ
	@ResponseBody
	@PostMapping("testStart1")
	public boolean testStart(@RequestParam("submitAnswer") int[] submitAnswer, 
							@RequestParam("randQuizId") int[] randQuizId,
							@RequestParam("randRn") int[] randRn,
							@RequestParam("randQuizScore") int[] randQuizScore,
							int testId, 
							TestResultVO testResultVO,
							ExamResultVO examResultVO,
							Authentication authentication) {
		//LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal(); //세션값 가져오기
		int logid = 14;
		//int logid = userVO.getuserId();
		boolean result = false;
		examResultVO.setUserId(logid);
		examResultVO.setTestId(testId);
		cbtStudentService.testSubmit2(examResultVO); //[1]시험결과 정보등록
		for(int i = 0; i < randRn.length; i++) { //전체 문제 개수만큼 반복
			testResultVO.setTestAnswer(submitAnswer[i]);
			testResultVO.setQuizId(randQuizId[i]);
			testResultVO.setTestId(testId);
			testResultVO.setUserId(logid);
			testResultVO.setResultId(examResultVO.getResultId());
			cbtStudentService.testSubmit(testResultVO); //[2]문제 등록
		}
		result = cbtStudentService.testSubmit3(examResultVO); //[3]총점 업데이트
		return result;
	}
	
	
	
	//ㅡㅡㅡㅡ
	//시험결과
	//ㅡㅡㅡㅡ
	@GetMapping("testResult")
	public String testResult(int testId,
			                 int min, 
			                 int sec,
			                 ExamResultVO examResultVO,
			                 QuizboxVO quizboxVO,
			                 Authentication authentication,
			                 Model model) {
		//LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal(); //세션값 가져오기
		int logid = 14;
		//int logid = userVO.getuserId();
		examResultVO.setUserId(logid);
		examResultVO.setTestId(testId);
		ExamResultVO info = cbtStudentService.testResult(examResultVO);
		List<QuizboxVO> list = new ArrayList<>();
		quizboxVO.setSubjectId(info.getSubjectId());
		quizboxVO.setTestId(testId);
		list = cbtStudentService.testResultQuiz(quizboxVO); //매퍼 결과 전체 리스트에 추가
		model.addAttribute("testResult",info); //시험결과 정보
		model.addAttribute("testMin", min); //응시시간 분
		model.addAttribute("testSec", sec); //응시시간 초
		model.addAttribute("quizList", list); //시험결과 문제+보기 정보
		return "cbt_student/testResult";
	}
	
	
	//ㅡㅡㅡㅡ
	//오답확인
	//ㅡㅡㅡㅡ
	@GetMapping("testResult2")
	public String testResult2(int testId,
							  ExamResultVO examResultVO,
				              QuizboxVO quizboxVO,
				              Authentication authentication,
							  Model model) {
		//LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal(); //세션값 가져오기
		int logid = 14;
		//int logid = userVO.getuserId();
		examResultVO.setUserId(logid);
		examResultVO.setTestId(testId);
		ExamResultVO info = cbtStudentService.testResult(examResultVO);
		List<QuizboxVO> list = new ArrayList<>();
		int trueCnt = 0;
		int falseCnt = 0;
		quizboxVO.setSubjectId(info.getSubjectId());
		quizboxVO.setTestId(testId);
		list = cbtStudentService.testResultQuiz(quizboxVO); //매퍼 결과 전체 리스트에 추가
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getIsCorrect() == 1) {
				trueCnt++;
			}
			else {
				falseCnt++;
			}
		}
		model.addAttribute("testResult",info); //시험결과 정보
		model.addAttribute("quizList", list); //시험결과 문제+보기 정보
		model.addAttribute("trueCnt", trueCnt); //맞은개수
		model.addAttribute("falseCnt", falseCnt); //틀린개수
		return "cbt_student/testResult2";
	}
}