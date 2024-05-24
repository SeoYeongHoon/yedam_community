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
	
	//시험전체
	@GetMapping("testList2")
	public String testList(TestVO testVO,
						   ExamResultVO examResultVO,
			               HttpSession session, 
			               Model model) {
		//HttpSession session = req.getSession();
		String logid ="14"; //(String) session.getAttribute("logid");
		List<TestVO> list = cbtStudentService.testListAll(Integer.parseInt(logid)); //시험목록
		model.addAttribute("testList", list); //시험목록
		model.addAttribute("logId", logid); //로그인정보
		return "cbt_student/testList2";
	}
	
	//시험단건(상세)
	@GetMapping("testDetail")
	public String testDetail(TestVO testVO, 
			                 QuizboxVO quizboxVO, 
			                 Model model) {
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
		model.addAttribute("testDetail", info); //상세정보
		model.addAttribute("randQuizId", randQuizId); //문제 번호
		model.addAttribute("randRn", randRn); //문제 일련번호
		model.addAttribute("randQuizScore", randQuizScore); //문제 배점
		return "cbt_student/testDetail";
	}
	//시험시작
	@PostMapping("testStart")
	public String testStart(int page, 
			                int[] randQuizId, 
			                int[] randRn, 
			                int[] randQuizScore, 
			                TestVO testVO, 
			                QuizboxVO quizboxVO, 
			                AnswerVO answerVO, 
			                Model model) {
		String logid ="14";
		testVO.setUserId(Integer.parseInt(logid));
		TestVO info = cbtStudentService.testStart(testVO); //응시정보
		quizboxVO.setQuizId(randQuizId[0]);
		quizboxVO.setTestId(info.getTestId());
		quizboxVO.setSubjectId(info.getSubjectId());
		answerVO.setQuizId(randQuizId[0]);
		answerVO.setTestId(info.getTestId());
		answerVO.setSubjectId(info.getSubjectId());
		QuizboxVO quiz1 = cbtStudentService.testQuiz1(quizboxVO); //시험문제
		List<AnswerVO> quiz2 = cbtStudentService.testQuiz2(answerVO); //문제보기
		model.addAttribute("testStart", info); //응시정보
		model.addAttribute("testQuiz1", quiz1); //시험문제 내용정보
		model.addAttribute("testQuiz2", quiz2); //시험문제 보기정보
		model.addAttribute("page", page); //현재 페이지번호
		model.addAttribute("randQuizId", randQuizId); //문제번호 전달
		model.addAttribute("randRn", randRn); //문제 일련번호
		model.addAttribute("randQuizScore", randQuizScore); //문제 배저
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
		testVO.setQuizId(randQuizId[i]); //해당 문제번호
		testVO.setTestId(testId); //해당 시험번호
		testVO.setSubjectId(subjectId); //해당 과목번호
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
		String logid ="14";
		examResultVO.setUserId(Integer.parseInt(logid));
		examResultVO.setTestId(testId);
		cbtStudentService.testSubmit2(examResultVO); //[1]시험결과 정보등록
		for(int i = 0; i < randRn.length; i++) { //전체 문제 개수만큼 반복
			testResultVO.setTestAnswer(submitAnswer[i]);
			testResultVO.setQuizId(randQuizId[i]);
			testResultVO.setTestId(testId);
			testResultVO.setUserId(Integer.parseInt(logid));
			testResultVO.setResultId(examResultVO.getResultId());
			cbtStudentService.testSubmit(testResultVO); //[2]문제 등록
		}
		result = cbtStudentService.testSubmit3(examResultVO); //[3]총점 업데이트
		return result;
	}
	//시험결과
	@PostMapping("testResult")
	public String testResult(int testId,
			                 int min, 
			                 int sec,
			                 ExamResultVO examResultVO,
			                 QuizboxVO quizboxVO,
			                 AnswerVO answerVO,
			                 Model model) {
		String logid ="14";
		examResultVO.setUserId(Integer.parseInt(logid));
		examResultVO.setTestId(testId);
		ExamResultVO info = cbtStudentService.testResult(examResultVO);
		quizboxVO.setSubjectId(info.getSubjectId());
		quizboxVO.setTestId(testId);
		List<QuizboxVO> quiz1 = cbtStudentService.testResultQuiz1(quizboxVO);
		answerVO.setSubjectId(info.getSubjectId());
		answerVO.setTestId(testId);
		List<AnswerVO> quiz2 = cbtStudentService.testResultQuiz2(answerVO);
		model.addAttribute("testResult",info); //시험결과 정보
		model.addAttribute("testMin", min); //응시시간 분
		model.addAttribute("testSec", sec); //응시시간 초
		model.addAttribute("testResultQuiz1", quiz1); //시험결과 문제정보
		model.addAttribute("testResultQuiz2", quiz2); //시험결과 보기정보
		return "cbt_student/testResult";
	}
}