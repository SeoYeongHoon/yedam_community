package com.yedam.app.yedam_examstudent.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.yedam_common.LoginUserVO;
import com.yedam.app.yedam_examstudent.service.AnswerVO;
import com.yedam.app.yedam_examstudent.service.CbtStudentService;
import com.yedam.app.yedam_examstudent.service.ExamResultVO;
import com.yedam.app.yedam_examstudent.service.QuizboxVO;
import com.yedam.app.yedam_examstudent.service.TestResultVO;
import com.yedam.app.yedam_examstudent.service.TestVO;
import com.yedam.app.yedam_subjects.service.SubjectsVO;

@Component
@EnableScheduling
@Controller

@RequestMapping("/all")
public class CbtStudentController {
	
	//service주입
	@Autowired
	CbtStudentService cbtStudentService;
	
//	@Scheduled(cron = "0 0 0 * * *")
//	public void schedule() {
//		System.out.println(1)
//	}
	
	//ㅡㅡㅡㅡ
	//시험전체
	//ㅡㅡㅡㅡ
	@GetMapping("testList2")
	public String testList(@RequestParam(defaultValue = "1") int page,
						   TestVO testVO,
						   ExamResultVO examResultVO,
						   SubjectsVO subjectsVO,
			               Authentication authentication,
			               Model model) {
		//ㅡㅡㅡ
		//세션값
		//ㅡㅡㅡ
		LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal(); //세션값 가져오기
		//ㅡㅡㅡㅡㅡㅡㅡ
		//시험과목 찾기
		//ㅡㅡㅡㅡㅡㅡㅡ
		subjectsVO.setUserId(userVO.getuserId());
		List<SubjectsVO> list = cbtStudentService.testSub(subjectsVO);
		//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		//시험 전체 목록 페이징 결과
		//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		testVO.setUserId(userVO.getuserId());
		testVO.setType("");
		testVO.setfilterData("");
		testVO.setPage(page);
		List<TestVO> list1 = cbtStudentService.testListAll(testVO);
		int size = cbtStudentService.testListSize(userVO.getuserId()); //시험개수		
		//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		//시험 전체 목록에 따른 시험결과, 피드백, 미응시 찾기
		//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		List<ExamResultVO> list2 = new ArrayList<>(); //피드백 관련 변수
		int[] array1 = new int[list1.size()]; //시험결과 유무 관련 변수
		for(int i = 0; i < list1.size(); i++) {
			//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
			//시험결과의 존재 유무 찾기
			//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
			testVO.setUserId(userVO.getuserId());
			testVO.setTestId(list1.get(i).getTestId());
			array1[i] = (cbtStudentService.isTestResult(testVO));
			//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
			//피드백의 존재 유무 찾기
			//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
			testVO.setUserId(userVO.getuserId());
			testVO.setTestId(list1.get(i).getTestId());
			list2.add(cbtStudentService.isTestFeedback(testVO));
		}
		model.addAttribute("testSub", list); //시험과목
		model.addAttribute("testList", list1); //시험목록
		model.addAttribute("isFeedback", list2); //피드백유무
		model.addAttribute("isResult", array1); //시험결과유무
		model.addAttribute("page", page); //페이지
		model.addAttribute("size", size); //시험개수
		model.addAttribute("logId", userVO.getuserId()); //로그인정보
		return "cbt_student/testList2";
	}
	
	//---------------
	//시험목록 필터링 AJAX
	//---------------
	@ResponseBody
	@GetMapping("listFilter")
	public Map<String, Object> listFilter(@RequestParam("type") String type,
								   @RequestParam("filterData") String filterData,
								   @RequestParam("page") int page,
								   Authentication authentication,
								   TestVO testVO,
								   ExamResultVO examResultVO){
		//ㅡㅡㅡ
		//세션값
		//ㅡㅡㅡ
		LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal(); //세션값 가져오기
		System.out.println(filterData);
		if(filterData.equals("all") || filterData.equals("")) {
			testVO.setfilterData("");
			testVO.setType("");
		}
		else {
			testVO.setfilterData(filterData);
			testVO.setType(type);
		}
		testVO.setUserId(userVO.getuserId());
		testVO.setPage(page);
		List<TestVO> list1 = cbtStudentService.testListAll(testVO);
		List<ExamResultVO> list2 = new ArrayList<>(); //피드백 관련 변수
		int[] array1 = new int[list1.size()]; //시험결과 유무 관련 변수
		//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		//시험 전체 목록에 따른 시험결과, 피드백, 미응시 찾기
		//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		for(int i = 0; i < list1.size(); i++) {
			//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
			//시험결과의 존재 유무 찾기
			//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
			testVO.setUserId(userVO.getuserId());
			testVO.setTestId(list1.get(i).getTestId());
			array1[i] = (cbtStudentService.isTestResult(testVO));
			//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
			//피드백의 존재 유무 찾기
			//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
			testVO.setUserId(userVO.getuserId());
			testVO.setTestId(list1.get(i).getTestId());
			list2.add(cbtStudentService.isTestFeedback(testVO));
		}
		Map<String, Object> map = new HashMap<>();
		map.put("testList",list1);
		map.put("isFeedback", list2);
		map.put("isResult", array1);
		return map;
	}
	
	
	
	//ㅡㅡㅡㅡ
	//시험단건
	//ㅡㅡㅡㅡ
	@GetMapping("testDetail")
	public String testDetail(TestVO testVO, 
			                 QuizboxVO quizboxVO, 
			                 Model model,
			                 Authentication authentication) {
		TestVO info = cbtStudentService.testDetail(testVO); //시험상세
		List<QuizboxVO> list = cbtStudentService.testQuizRand(quizboxVO); //랜덤문제
		LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal(); //세션값 가져오기
		//int logid = 14;
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
		testVO.setUserId(userVO.getuserId());
		testVO.setTestId(info.getTestId());
		isResult = (cbtStudentService.isTestResult(testVO));
		//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		//마감날짜 23:59:59 지난 경우 미응시 처리
		//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		Calendar cal = Calendar.getInstance(); //캘린더 객체 생성
		cal.setTime(info.getTestDate()); //시간 설정
		cal.set(Calendar.HOUR_OF_DAY , 23); //시간 지정
		cal.set(Calendar.MINUTE, 59); //분 지정
		cal.set(Calendar.SECOND, 59); //초 지정
		Date date1 = cal.getTime(); //캘린더 객체 날짜 타입 변수
		Date date2 = new Date(); //비교할 현재 날짜 변수
		if(date2.after(date1)) { //현재 날짜가 응시기간을 지난 경우
			dateComp = 1;
		}
		model.addAttribute("testDetail", info); //상세정보
		model.addAttribute("randQuizId", randQuizId); //문제 번호
		model.addAttribute("randRn", randRn); //문제 일련번호
		model.addAttribute("randQuizScore", randQuizScore); //문제 배점
		model.addAttribute("isResult", isResult); //시험결과 유무
		model.addAttribute("dateComp", dateComp); //날짜 비교
		model.addAttribute("logId", userVO.getuserId()); //로그인정보
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
			                Model model,
			                Authentication authentication) {
		LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal(); //세션값 가져오기
		testVO.setUserId(userVO.getuserId());
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
		model.addAttribute("randQuizScore", randQuizScore); //문제 배점
		model.addAttribute("logId", userVO.getuserId()); //로그인정보
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
		boolean result = false;
		LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal(); //세션값 가져오기
		examResultVO.setUserId(userVO.getuserId());
		examResultVO.setTestId(testId);
		cbtStudentService.testSubmit2(examResultVO); //[1]시험결과 정보등록
		for(int i = 0; i < randRn.length; i++) { //전체 문제 개수만큼 반복
			testResultVO.setTestAnswer(submitAnswer[i]);
			testResultVO.setQuizId(randQuizId[i]);
			testResultVO.setTestId(testId);
			testResultVO.setUserId(userVO.getuserId());
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
			                 Model model,
			                 Authentication authentication) {
		LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal(); //세션값 가져오기
		examResultVO.setUserId(userVO.getuserId());
		examResultVO.setTestId(testId);
		ExamResultVO info = cbtStudentService.testResult(examResultVO);
		List<QuizboxVO> list = new ArrayList<>();
		quizboxVO.setUserId(userVO.getuserId());
		quizboxVO.setTestId(testId);
		list = cbtStudentService.testResultQuiz(quizboxVO); //매퍼 결과 전체 리스트에 추가
		model.addAttribute("testResult",info); //시험결과 정보
		model.addAttribute("testMin", min); //응시시간 분
		model.addAttribute("testSec", sec); //응시시간 초
		model.addAttribute("quizList", list); //시험결과 문제+보기 정보
		model.addAttribute("logId", userVO.getuserId()); //로그인정보
		return "cbt_student/testResult";
	}
	
	
	//ㅡㅡㅡㅡ
	//문제풀이
	//ㅡㅡㅡㅡ
	@GetMapping("testResult2")
	public String testResult2(int testId,
							  ExamResultVO examResultVO,
				              QuizboxVO quizboxVO,
							  Model model,
							  Authentication authentication) {
		LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal(); //세션값 가져오기
		examResultVO.setUserId(userVO.getuserId());
		examResultVO.setTestId(testId);
		ExamResultVO info = cbtStudentService.testResult(examResultVO);
		List<QuizboxVO> list = new ArrayList<>();
		int trueCnt = 0;
		int falseCnt = 0;
		quizboxVO.setUserId(userVO.getuserId());
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
		System.out.println();
		model.addAttribute("testResult",info); //시험결과 정보
		model.addAttribute("quizList", list); //시험결과 문제+보기 정보
		model.addAttribute("trueCnt", trueCnt); //맞은개수
		model.addAttribute("falseCnt", falseCnt); //틀린개수
		model.addAttribute("logId", userVO.getuserId()); //로그인정보
		return "cbt_student/testResult2";
	}
}