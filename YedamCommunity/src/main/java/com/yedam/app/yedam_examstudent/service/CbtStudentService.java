package com.yedam.app.yedam_examstudent.service;

import java.util.List;


public interface CbtStudentService {
	
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
	//ㅡㅡㅡㅡ
	//시험개수
	//ㅡㅡㅡㅡ
	public int testListSize(int userId);

	
	
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
	public QuizboxVO testQuiz(QuizboxVO quizboxVO);
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	//시험시작 문제정보
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	public QuizboxVO testQuiz1(QuizboxVO quizboxVO);
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	//시험시작 보기정보
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	public List<AnswerVO> testQuiz2(AnswerVO answerVO);
	public QuizboxVO testQuiz3(QuizboxVO quizboxVO);
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
