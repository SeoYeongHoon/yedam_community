package com.yedam.app.yedam_examstudent.service;

import java.util.List;

import com.yedam.app.yedam_user.service.UserVO;

public interface CbtStudentService {

	//ㅡㅡㅡㅡㅡㅡㅡㅡ
	//마이페이지 정보
	//ㅡㅡㅡㅡㅡㅡㅡㅡ
	public UserVO myPageInfo(int userId);
	//ㅡㅡㅡㅡ
	//회원탈퇴
	//ㅡㅡㅡㅡ
	public boolean unjoinUser(int userId);
	//ㅡㅡㅡㅡㅡㅡ
	//최근시험목록
	//ㅡㅡㅡㅡㅡㅡ
	public List<TestVO> recentTest(int userId);
	
	
	
	//ㅡㅡㅡㅡㅡㅡ
	//시험결과유무
	//ㅡㅡㅡㅡㅡㅡ
	public int isTestResult(TestVO testVO);
	//ㅡㅡㅡㅡㅡ
	//피드백유무
	//ㅡㅡㅡㅡㅡ
	public ExamResultVO isTestFeedback(TestVO testVO);
	//ㅡㅡㅡㅡㅡ
	//재시험유무
	//ㅡㅡㅡㅡㅡ
	public int isTestReexam(TestVO testVO);
	//ㅡㅡㅡㅡ
	//시험목록
	//ㅡㅡㅡㅡ
	public List<TestVO> testListAll(TestVO testVO);
	//ㅡㅡㅡㅡ
	//시험과목
	//ㅡㅡㅡㅡ
	public List<TestVO> userSubject(int userId);

	
	
	//ㅡㅡㅡㅡㅡㅡㅡ
	//시험문제 랜덤
	//ㅡㅡㅡㅡㅡㅡㅡ
	public List<QuizboxVO> testQuizRand(QuizboxVO quizboxVO);
	//ㅡㅡㅡㅡ
	//시험상세
	//ㅡㅡㅡㅡ
	public TestVO testDetail(TestVO testVO);

	
	
	//ㅡㅡㅡㅡ
	//시험시작
	//ㅡㅡㅡㅡ
	public TestVO testStart(TestVO testVO);
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	//시험문제 (AJAX)
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	public List<TestVO> testQuiz(TestVO testVO);
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	//시험시작 문제정보
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	public QuizboxVO testQuiz1(QuizboxVO quizboxVO);
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	//시험시작 보기정보
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	public List<AnswerVO> testQuiz2(AnswerVO answerVO);
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	//시험결과 (AJAX)
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	public boolean testSubmit2(ExamResultVO examResultVO);
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	//문제제출 (AJAX)
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	public boolean testSubmit(TestResultVO testResultVO);
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	//시험결과 (AJAX)
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	public boolean testSubmit3(ExamResultVO examResultVO);
	
	
	
	//ㅡㅡㅡㅡ
	//시험결과
	//ㅡㅡㅡㅡ
	public ExamResultVO testResult(ExamResultVO examResultVO);
	//ㅡㅡㅡㅡ
	//시험문제
	//ㅡㅡㅡㅡ
	public List<QuizboxVO> testResultQuiz(QuizboxVO quizboxVO);
}
