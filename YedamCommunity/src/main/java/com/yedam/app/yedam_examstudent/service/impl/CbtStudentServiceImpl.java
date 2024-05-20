package com.yedam.app.yedam_examstudent.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.yedam_examstudent.mapper.CbtStudentMapper;
import com.yedam.app.yedam_examstudent.service.AnswerVO;
import com.yedam.app.yedam_examstudent.service.CbtStudentService;
import com.yedam.app.yedam_examstudent.service.QuizboxVO;
import com.yedam.app.yedam_examstudent.service.TestVO;

@Service
public class CbtStudentServiceImpl implements CbtStudentService{

	//mapper 주입
	@Autowired
	CbtStudentMapper cbtStudentMapper;
	
	//전체시험목록 조회
	@Override
	public List<TestVO> testListAll() {
		return cbtStudentMapper.selectTestListAll();
	}
	//시험상세정보 조회
	@Override
	public TestVO testDetail(TestVO testVO) {
		return cbtStudentMapper.selectTestDetail(testVO);
	}
	//시험문제랜덤
	@Override
	public List<QuizboxVO> testQuizRand(QuizboxVO quizboxVO) {
		return cbtStudentMapper.selectTestQuizRand(quizboxVO);
	}
	//시험시작정보 조회
	@Override
	public TestVO testStart(TestVO testVO) {
		return cbtStudentMapper.selectTestStart(testVO);
	}
	//시험문제
	@Override
	public List<TestVO> testQuiz(TestVO testVO) {
		return cbtStudentMapper.selectTestQuiz(testVO);
	}
	//시험시작정보 조회 = 문제내용
	@Override
	public QuizboxVO testQuiz1(QuizboxVO quizboxVO) {
		return cbtStudentMapper.selectTestQuiz1(quizboxVO);
	}
	//시험시작정보 조회 = 문제보기
	@Override
	public List<AnswerVO> testQuiz2(AnswerVO answerVO) {
		return cbtStudentMapper.selectTestQuiz2(answerVO);
	}
	//문제개수
	@Override
	public int quizCnt(QuizboxVO quizboxVO) {
		return cbtStudentMapper.selectQuizCnt(quizboxVO);
	}
	
	
	
	
}
