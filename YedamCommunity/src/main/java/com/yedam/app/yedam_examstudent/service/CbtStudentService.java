package com.yedam.app.yedam_examstudent.service;

import java.util.List;

public interface CbtStudentService {

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
}
