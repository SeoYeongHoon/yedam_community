package com.yedam.app.yedam_examstudent.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yedam.app.yedam_examstudent.service.AnswerVO;
import com.yedam.app.yedam_examstudent.service.ExamResultVO;
import com.yedam.app.yedam_examstudent.service.QuizboxVO;
import com.yedam.app.yedam_examstudent.service.TestResultVO;
import com.yedam.app.yedam_examstudent.service.TestVO;

@Mapper
public interface CbtStudentMapper {
	//전체시험목록 조회
	public List<TestVO> selectTestListAll(String userId);
	//시험상세정보 조회
	public TestVO selectTestDetail(TestVO testVO);
	//시험문제랜덤
	public List<QuizboxVO> selectTestQuizRand(QuizboxVO quizboxVO);
	//시험시작정보 조회
	public TestVO selectTestStart(TestVO testVO);
	//시험문제
	public List<TestVO> selectTestQuiz(TestVO testVO);
	//시험시작정보 조회 = 문제내용
	public QuizboxVO selectTestQuiz1(QuizboxVO quizboxVO);
	//시험시작정보 조회 = 문제보기
	public List<AnswerVO> selectTestQuiz2(AnswerVO answerVO);
	//문제개수 구하기
	public int selectQuizCnt(QuizboxVO quizboxVO);
	//문제 제출
	public boolean insertTestSubmit(TestResultVO testResultVO);
	//시험 결과 등록
	public boolean insertTestResult(ExamResultVO examResultVO);
	//시험 결과 수정
	public boolean insertTestResultScore(ExamResultVO examResultVO);
	//시험 결과 정보
	public ExamResultVO selectTestResult(ExamResultVO examResultVO);
}
