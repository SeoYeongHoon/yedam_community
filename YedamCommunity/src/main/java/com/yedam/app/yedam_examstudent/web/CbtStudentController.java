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
import com.yedam.app.yedam_examstudent.service.AnswerboxVO;
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
		//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		//옵션 및 검색 필터링
		//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		if(filterData.equals("all") || filterData.equals("")) { //과목이 전체거나 검색창이 공백인 경우
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
		map.put("testList",list1); //시험목록
		map.put("isFeedback", list2); //피드백유무
		map.put("isResult", array1); //시험결과유무
		return map;
	}
	
	
	
	//ㅡㅡㅡㅡ
	//시험단건
	//ㅡㅡㅡㅡ
	@GetMapping("testDetail")
	public String testDetail(TestVO testVO, 
			                 QuizboxVO quizboxVO,
			                 AnswerboxVO answerboxVO,
			                 Model model,
			                 Authentication authentication) {
		//ㅡㅡ
		//세션
		//ㅡㅡ
		LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal(); //세션값 가져오기
		//ㅡㅡㅡㅡㅡㅡㅡ
		//시험 상세정보
		//ㅡㅡㅡㅡㅡㅡㅡ
		TestVO info = cbtStudentService.testDetail(testVO); //시험상세
		//ㅡㅡㅡㅡㅡ
		//랜덤 문제
		//ㅡㅡㅡㅡㅡ
		List<QuizboxVO> list = cbtStudentService.testQuizRand(quizboxVO); //랜덤문제
		//ㅡㅡㅡㅡㅡㅡㅡ
		//시험보기 등록
		//ㅡㅡㅡㅡㅡㅡㅡ
		int cnt = 0;
		boolean result = false;
		for(int i = 0; i < list.size(); i++) {			
			answerboxVO.setExampleOne(list.get(i).getOne());
			answerboxVO.setExampleTwo(list.get(i).getTwo());
			answerboxVO.setExampleThree(list.get(i).getThree());
			answerboxVO.setExampleFour(list.get(i).getFour());
			answerboxVO.setExampleFive(list.get(i).getFive());
			answerboxVO.setQuizId(list.get(i).getQuizId());
			answerboxVO.setTestId(list.get(i).getTestId());
			answerboxVO.setUserId(userVO.getuserId());
			if(cbtStudentService.testQuizExample(answerboxVO)) {
				cnt++;
			}
		}
		if(cnt > 0) {
			result = true;
		}
		//ㅡㅡㅡㅡㅡㅡㅡ
		//시험결과 유무
		//ㅡㅡㅡㅡㅡㅡㅡ
		int isResult = 0;
		testVO.setUserId(userVO.getuserId());
		testVO.setTestId(info.getTestId());
		isResult = (cbtStudentService.isTestResult(testVO));
		//ㅡㅡㅡㅡㅡㅡ
		//미응시 처리
		//ㅡㅡㅡㅡㅡㅡ
		int dateComp = 0;
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
		model.addAttribute("randQuiz", list); //문제보기
		model.addAttribute("isResult", isResult); //시험결과 유무
		model.addAttribute("dateComp", dateComp); //날짜 비교
		model.addAttribute("result", result); //보기등록
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
			                int[] one,
			                int[] two,
			                int[] three,
			                int[] four,
			                int[] five,
			                TestVO testVO, 
			                QuizboxVO quizboxVO, 
			                AnswerVO answerVO, 
			                Model model,
			                Authentication authentication) {
		LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal(); //세션값 가져오기
		//ㅡㅡㅡㅡㅡㅡㅡ
		//시험 응시정보
		//ㅡㅡㅡㅡㅡㅡㅡ
		testVO.setUserId(userVO.getuserId());
		TestVO info = cbtStudentService.testStart(testVO); //응시정보
		//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		//랜덤 시험문제 및 보기
		//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		quizboxVO.setQuizId(randQuizId[0]);
		quizboxVO.setTestId(info.getTestId());
		QuizboxVO quiz = cbtStudentService.testQuiz(quizboxVO); //시험문제
		model.addAttribute("testStart", info); //응시정보
		model.addAttribute("testQuiz", quiz); //시험문제 내용정보
		model.addAttribute("page", page); //현재 페이지번호
		model.addAttribute("randQuizId", randQuizId); //문제번호 전달
		model.addAttribute("randRn", randRn); //문제 일련번호
		model.addAttribute("randQuizScore", randQuizScore); //문제 배점
		model.addAttribute("one", one); //랜덤 1번보기 번호
		model.addAttribute("two", two); //랜덤 2번보기 번호
		model.addAttribute("three", three); //랜덤 3번보기 번호
		model.addAttribute("four", four); //랜덤 4번보기 번호
		model.addAttribute("five", five); //랜덤 5번보기 번호
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
		//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		//현재 페이지 번호
		//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		int i = page - 1;
		//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		//랜덤 시험문제 및 보기
		//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		quizboxVO.setQuizId(randQuizId[i]); //해당 문제번호
		quizboxVO.setTestId(testId); //해당 시험번호
		return cbtStudentService.testQuiz(quizboxVO); //시험문제
	}
	//ㅡㅡㅡㅡㅡㅡㅡㅡ
	//문제제출 AJAX
	//ㅡㅡㅡㅡㅡㅡㅡㅡ
	@ResponseBody
	@PostMapping("testStart1")
	public boolean testStart(@RequestParam("submitAnswer") String[] submitAnswer, 
							@RequestParam("randQuizId") int[] randQuizId,
							@RequestParam("randRn") int[] randRn,
							@RequestParam("randQuizScore") int[] randQuizScore,
							int testId, 
							TestResultVO testResultVO,
							ExamResultVO examResultVO,
							Authentication authentication) {
		boolean result = false;
		LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal(); //세션값 가져오기
		//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		//[1]시험결과 등록
		//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		examResultVO.setUserId(userVO.getuserId());
		examResultVO.setTestId(testId);
		cbtStudentService.testSubmit2(examResultVO); //[1]시험결과 정보등록
		for(int i = 0; i < randRn.length; i++) { //전체 문제 개수만큼 반복
			//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
			//[2]시험결과 답안등록
			//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
			testResultVO.setTestAnswer(submitAnswer[i]);
			testResultVO.setQuizId(randQuizId[i]);
			testResultVO.setTestId(testId);
			testResultVO.setUserId(userVO.getuserId());
			testResultVO.setResultId(examResultVO.getResultId());
			cbtStudentService.testSubmit(testResultVO); //[2]문제 등록
		}
		//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		//[3]시험결과 총점등록
		//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
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
			                 int[] randQuizId,
			                 int[] one,
				             int[] two,
				             int[] three,
				             int[] four,
				             int[] five,
			                 ExamResultVO examResultVO,
			                 QuizboxVO quizboxVO,
			                 Model model,
			                 Authentication authentication) {
		LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal(); //세션값 가져오기
		//ㅡㅡㅡㅡㅡㅡㅡ
		//시험결과 정보
		//ㅡㅡㅡㅡㅡㅡㅡ
		examResultVO.setUserId(userVO.getuserId());
		examResultVO.setTestId(testId);
		ExamResultVO info = cbtStudentService.testResult(examResultVO);
		//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		//시혐결과 문제정보
		//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		List<QuizboxVO> list = new ArrayList<>();
		for(int i = 0; i < one.length; i++) {			
			quizboxVO.setUserId(userVO.getuserId());
			quizboxVO.setTestId(testId);
			quizboxVO.setQuizId(randQuizId[i]);
			quizboxVO.setOne(one[i]);
			quizboxVO.setTwo(two[i]);
			quizboxVO.setThree(three[i]);
			quizboxVO.setFour(four[i]);
			quizboxVO.setFive(five[i]);
			list.addAll(cbtStudentService.testResultQuiz(quizboxVO)); //매퍼 결과 전체 리스트에 추가
		}
		int answerCnt = 0;
		model.addAttribute("testResult",info); //시험결과 정보
		model.addAttribute("testMin", min); //응시시간 분
		model.addAttribute("testSec", sec); //응시시간 초
		model.addAttribute("quizList", list); //시험결과 문제+보기 정보
		model.addAttribute("one", one); //랜덤 1번보기 번호
		model.addAttribute("two", two); //랜덤 2번보기 번호
		model.addAttribute("three", three); //랜덤 3번보기 번호
		model.addAttribute("four", four); //랜덤 4번보기 번호
		model.addAttribute("five", five); //랜덤 5번보기 번호
		model.addAttribute("logId", userVO.getuserId()); //로그인정보
		model.addAttribute("answerCnt", answerCnt); //맞힌 개수 구하기 위한 임의 변수
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
		//ㅡㅡㅡㅡㅡㅡㅡ
		//시험결과 정보
		//ㅡㅡㅡㅡㅡㅡㅡ
		examResultVO.setUserId(userVO.getuserId());
		examResultVO.setTestId(testId);
		ExamResultVO info = cbtStudentService.testResult(examResultVO);
		//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		//시험결과 문제정보
		//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		List<QuizboxVO> list = new ArrayList<>();
		quizboxVO.setUserId(userVO.getuserId());
		quizboxVO.setTestId(testId);
		list = cbtStudentService.testResultQuiz(quizboxVO); //매퍼 결과 전체 리스트에 추가
		//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		//맞힌개수 틀린개수
		//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		int trueCnt = 0;
		int falseCnt = 0;
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
		model.addAttribute("logId", userVO.getuserId()); //로그인정보
		return "cbt_student/testResult2";
	}
}