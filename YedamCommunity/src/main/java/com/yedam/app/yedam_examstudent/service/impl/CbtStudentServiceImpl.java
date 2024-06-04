package com.yedam.app.yedam_examstudent.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.yedam_examstudent.mapper.CbtStudentMapper;
import com.yedam.app.yedam_examstudent.service.CbtStudentService;
import com.yedam.app.yedam_examstudent.service.ExamResultVO;
import com.yedam.app.yedam_examstudent.service.QuizboxVO;
import com.yedam.app.yedam_examstudent.service.TestResultVO;
import com.yedam.app.yedam_examstudent.service.TestVO;
import com.yedam.app.yedam_subjects.service.SubjectsVO;

@Service
public class CbtStudentServiceImpl implements CbtStudentService{

	//mapper 주입
	@Autowired
	CbtStudentMapper cbtStudentMapper;
	
	
	//ㅡㅡㅡㅡㅡㅡ
	//시험결과유무
	//ㅡㅡㅡㅡㅡㅡ
	@Override
	public int isTestResult(TestVO testVO) {
		return cbtStudentMapper.isTestResult(testVO);
	}
	//ㅡㅡㅡㅡㅡ
	//피드백유무
	//ㅡㅡㅡㅡㅡ
	@Override
	public ExamResultVO isTestFeedback(TestVO testVO) {
		return cbtStudentMapper.isTestFeedback(testVO);
	}
	//ㅡㅡㅡㅡㅡ
	//재시험유무
	//ㅡㅡㅡㅡㅡ
	@Override
	public int isTestReexam(TestVO testVO) {
		return cbtStudentMapper.isTestReexam(testVO);
	}
	//ㅡㅡㅡㅡ
	//시험과목
	//ㅡㅡㅡㅡ
	@Override
	public List<SubjectsVO> testSub(SubjectsVO subjectsVO) {
		return cbtStudentMapper.selectTestSub(subjectsVO);
	}
	//ㅡㅡㅡㅡ
	//시험목록
	//ㅡㅡㅡㅡ
	@Override
	public List<TestVO> testListAll(TestVO testVO) {
		return cbtStudentMapper.selectTestListAll(testVO);
	}
	//ㅡㅡㅡㅡ
	//시험개수
	//ㅡㅡㅡㅡ
	@Override
	public int testListSize(int userId) {
		return cbtStudentMapper.selectTestListSize(userId);
	}
	
	
	
	//ㅡㅡㅡㅡㅡㅡ
	//시험문제랜덤
	//ㅡㅡㅡㅡㅡㅡ
	@Override
	public List<QuizboxVO> testQuizRand(QuizboxVO quizboxVO) {
		return cbtStudentMapper.selectTestQuizRand(quizboxVO);
	}
	//ㅡㅡㅡㅡㅡㅡ
	//시험보기랜덤
	//ㅡㅡㅡㅡㅡㅡ
	@Override
	public List<QuizboxVO> testQuizRand2(QuizboxVO quizboxVO) {
		return cbtStudentMapper.selectTestQuizRand2(quizboxVO);
	}
	//ㅡㅡㅡㅡ
	//시험상세
	//ㅡㅡㅡㅡ
	@Override
	public TestVO testDetail(TestVO testVO) {
		return cbtStudentMapper.selectTestDetail(testVO);
	}
	
	
	
	//ㅡㅡㅡㅡ
	//시험시작
	//ㅡㅡㅡㅡ
	@Override
	public TestVO testStart(TestVO testVO) {
		return cbtStudentMapper.selectTestStart(testVO);
	}
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	//시험시작 문제정보
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	@Override
	public QuizboxVO testQuiz(QuizboxVO quizboxVO) {
		return cbtStudentMapper.selectTestQuiz(quizboxVO);
	}
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	//시험결과 (AJAX)
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	@Override
	public boolean testSubmit2(ExamResultVO examResultVO) {
		return cbtStudentMapper.insertTestResult(examResultVO);
	}
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	//문제제출 (AJAX)
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	@Override
	public boolean testSubmit(TestResultVO testResultVO) {
		return cbtStudentMapper.insertTestSubmit(testResultVO);
	}
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	//시험결과 (AJAX)
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	@Override
	public boolean testSubmit3(ExamResultVO examResultVO) {
		return cbtStudentMapper.insertTestResultScore(examResultVO);
	}
	
	
	
	//ㅡㅡㅡㅡ
	//시험결과
	//ㅡㅡㅡㅡ
	@Override
	public ExamResultVO testResult(ExamResultVO examResultVO) {
		return cbtStudentMapper.selectTestResult(examResultVO);
	}
	//ㅡㅡㅡㅡㅡㅡㅡ
	//시험결과 문제
	//ㅡㅡㅡㅡㅡㅡㅡ
	@Override
	public List<QuizboxVO> testResultQuiz(QuizboxVO quizboxVO) {
		return cbtStudentMapper.selectTestResultQuiz(quizboxVO);
	}
	
	
	


}
