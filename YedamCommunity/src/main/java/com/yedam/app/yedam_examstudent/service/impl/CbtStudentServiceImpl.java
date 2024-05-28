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
	
	
	
	//시험결과유무
	@Override
	public int isTestResult(TestVO testVO) {
		return cbtStudentMapper.isTestResult(testVO);
	}
	//피드백유무
	@Override
	public ExamResultVO isTestFeedback(TestVO testVO) {
		return cbtStudentMapper.isTestFeedback(testVO);
	}
	//재시험유무
	@Override
	public int isTestReexam(TestVO testVO) {
		return cbtStudentMapper.isTestReexam(testVO);
	}
	//시험목록
	@Override
	public List<TestVO> testListAll(TestVO testVO) {
		return cbtStudentMapper.selectTestListAll(testVO);
	}
	//시험과목
	@Override
	public List<TestVO> userSubject(int userId) {
		return cbtStudentMapper.selectUserSubject(userId);
	}
	//시험개수
	@Override
	public int testListSize(int userId) {
		return cbtStudentMapper.selectTestListSize(userId);
	}
	
	
	
	//시험문제랜덤
	@Override
	public List<QuizboxVO> testQuizRand(QuizboxVO quizboxVO) {
		return cbtStudentMapper.selectTestQuizRand(quizboxVO);
	}
	//시험상세
	@Override
	public TestVO testDetail(TestVO testVO) {
		return cbtStudentMapper.selectTestDetail(testVO);
	}
	
	
	//시험시작
	@Override
	public TestVO testStart(TestVO testVO) {
		return cbtStudentMapper.selectTestStart(testVO);
	}
	//시험문제 (AJAX)
	@Override
	public List<TestVO> testQuiz(TestVO testVO) {
		return cbtStudentMapper.selectTestQuiz(testVO);
	}
	//시험시작 문제정보
	@Override
	public QuizboxVO testQuiz1(QuizboxVO quizboxVO) {
		return cbtStudentMapper.selectTestQuiz1(quizboxVO);
	}
	//시험시작 보기정보
	@Override
	public List<AnswerVO> testQuiz2(AnswerVO answerVO) {
		return cbtStudentMapper.selectTestQuiz2(answerVO);
	}
	//시험결과 (AJAX)
	@Override
	public boolean testSubmit2(ExamResultVO examResultVO) {
		return cbtStudentMapper.insertTestResult(examResultVO);
	}
	//문제제출 (AJAX)
	@Override
	public boolean testSubmit(TestResultVO testResultVO) {
		return cbtStudentMapper.insertTestSubmit(testResultVO);
	}
	//시험결과 (AJAX)
	@Override
	public boolean testSubmit3(ExamResultVO examResultVO) {
		return cbtStudentMapper.insertTestResultScore(examResultVO);
	}
	
	
	//시험결과
	@Override
	public ExamResultVO testResult(ExamResultVO examResultVO) {
		return cbtStudentMapper.selectTestResult(examResultVO);
	}
	//시험결과 문제
	@Override
	public List<QuizboxVO> testResultQuiz(QuizboxVO quizboxVO) {
		return cbtStudentMapper.selectTestResultQuiz(quizboxVO);
	}
	
	
	
	
	



	

}
