package com.yedam.app.yedam_examstudent.service;

import java.util.List;

import com.yedam.app.yedam_user.service.UserVO;

public interface CbtStudentService {

	//마이페이지 정보
	public UserVO myPageInfo(int userId);
	//회원탈퇴
	public boolean unjoinUser(int userId);
	//최근시험목록
	public List<TestVO> recentTest(int userId);
	
	
	//시험결과유무
	public int isTestResult(TestVO testVO);
	//피드백유무
	public int isTestFeedback(TestVO testVO);
	//재시험유무
	public int isTestReexam(TestVO testVO);
	//시험목록
	public List<TestVO> testListAll(int userId);

	
	//시험문제 랜덤
	public List<QuizboxVO> testQuizRand(QuizboxVO quizboxVO);
	//시험상세
	public TestVO testDetail(TestVO testVO);

	
	//시험시작
	public TestVO testStart(TestVO testVO);
	//시험문제 (AJAX)
	public List<TestVO> testQuiz(TestVO testVO);
	//시험시작 문제정보
	public QuizboxVO testQuiz1(QuizboxVO quizboxVO);
	//시험시작 보기정보
	public List<AnswerVO> testQuiz2(AnswerVO answerVO);
	//시험결과 (AJAX)
	public boolean testSubmit2(ExamResultVO examResultVO);
	//문제제출 (AJAX)
	public boolean testSubmit(TestResultVO testResultVO);
	//시험결과 (AJAX)
	public boolean testSubmit3(ExamResultVO examResultVO);
	
	
	//시험결과
	public ExamResultVO testResult(ExamResultVO examResultVO);
	//시험결과 문제정보
	public List<QuizboxVO> testResultQuiz1(QuizboxVO quizboxVO);
	//시험결과 보기정보
	public List<AnswerVO> testResultQuiz2(AnswerVO answerVO);
	//테스트
	public List<QuizboxVO> testTest(QuizboxVO quizboxVO);
}
