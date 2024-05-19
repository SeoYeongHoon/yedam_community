package com.yedam.app.yedam_examstudent.service;

import java.util.List;

public interface CbtStudentService {
	//전체시험목록 조회
	public List<TestVO> testListAll();
	//시험상세정보 조회
	public TestVO testDetail(TestVO testVO);
	//시험문제랜덤
	public List<QuizboxVO> testQuizRand(QuizboxVO quizboxVO);
	//시험시작정보 조회
	public TestVO testStart(TestVO testVO);
	//시험문제
	public List<TestVO> testQuiz(TestVO testVO);
	//시험시작정보 조회 = 문제내용
	public QuizboxVO testQuiz1(QuizboxVO quizboxVO);
	//시험시작정보 조회 = 문제보기
	public List<AnswerVO> testQuiz2(AnswerVO answerVO);
	//문제개수 구하기
	public int quizCnt(QuizboxVO quizboxVO);
}
