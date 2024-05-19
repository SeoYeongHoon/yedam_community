package com.yedam.app.yedam_examstudent.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yedam.app.yedam_examstudent.service.AnswerVO;
import com.yedam.app.yedam_examstudent.service.QuizboxVO;
import com.yedam.app.yedam_examstudent.service.TestVO;

@Mapper
public interface CbtStudentMapper {
	//전체시험목록 조회
	public List<TestVO> selectTestListAll();
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
}
