package com.yedam.app.yedam_examstudent.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.yedam_examstudent.mapper.CbtStudentMapper;
import com.yedam.app.yedam_examstudent.service.AnswerVO;
import com.yedam.app.yedam_examstudent.service.CbtStudentService;
import com.yedam.app.yedam_examstudent.service.ExamResultVO;
import com.yedam.app.yedam_examstudent.service.QuizboxVO;
import com.yedam.app.yedam_examstudent.service.TestResultVO;
import com.yedam.app.yedam_examstudent.service.TestVO;

@Service
public class CbtStudentServiceImpl implements CbtStudentService{

	//mapper 주입
	@Autowired
	CbtStudentMapper cbtStudentMapper;
	
	//전체시험목록 조회
	@Override
	public List<TestVO> testListAll(String userId) {
		return cbtStudentMapper.selectTestListAll(userId);
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
	//문제제출
	@Override
	public boolean testSubmit(TestResultVO testResultVO) {
		return cbtStudentMapper.insertTestSubmit(testResultVO);
	}
	//시험결과정보 등록
	public boolean testSubmit2(ExamResultVO examResultVO) {
		return cbtStudentMapper.insertTestResult(examResultVO);
	}
	//시험결과정보 수정 //총점 업데이트
	@Override
	public boolean testSubmit3(ExamResultVO examResultVO) {
		return cbtStudentMapper.insertTestResultScore(examResultVO);
	}
	//시험결과정보
	@Override
	public ExamResultVO testResult(ExamResultVO examResultVO) {
		return cbtStudentMapper.selectTestResult(examResultVO);
	}
	
	
	
	
	
	
}
