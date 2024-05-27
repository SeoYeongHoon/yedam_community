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
import com.yedam.app.yedam_user.service.UserVO;

@Service
public class CbtStudentServiceImpl implements CbtStudentService{

	//mapper 주입
	@Autowired
	CbtStudentMapper cbtStudentMapper;
	
	//마이페이지 정보
	@Override
	public UserVO myPageInfo(int userId) {
		return cbtStudentMapper.selectMyPageInfo(userId);
	}
	//회원탈퇴
	@Override
	public boolean unjoinUser(int userId) {
		return cbtStudentMapper.deleteUser(userId) == 1;
	}
	//최근시험목록
	@Override
	public List<TestVO> recentTest(int userId) {
		return cbtStudentMapper.selectRecentTest(userId);
	}
	
	
	//시험결과유무
	@Override
	public int isTestResult(TestVO testVO) {
		return cbtStudentMapper.isTestResult(testVO);
	}
	//피드백유무
	@Override
	public int isTestFeedback(TestVO testVO) {
		return cbtStudentMapper.isTestFeedback(testVO);
	}
	//재시험유무
	@Override
	public int isTestReexam(TestVO testVO) {
		return cbtStudentMapper.isTestReexam(testVO);
	}
	//시험목록
	@Override
	public List<TestVO> testListAll(int userId) {
		return cbtStudentMapper.selectTestListAll(userId);
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
	//시험결과 문제정보
	@Override
	public List<QuizboxVO> testResultQuiz1(QuizboxVO quizboxVO) {
		return cbtStudentMapper.selectTestResultQuiz1(quizboxVO);
	}
	//시험결과 보기정보
	@Override
	public List<AnswerVO> testResultQuiz2(AnswerVO answerVO) {
		return cbtStudentMapper.selectTestResultQuiz2(answerVO);
	}


	@Override
	public List<QuizboxVO> testTest(QuizboxVO quizboxVO) {
		return cbtStudentMapper.selectTest(quizboxVO);
	}
	
	
	



	

}
