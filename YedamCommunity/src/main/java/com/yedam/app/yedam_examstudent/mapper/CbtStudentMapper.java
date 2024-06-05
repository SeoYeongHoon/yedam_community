package com.yedam.app.yedam_examstudent.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yedam.app.yedam_examstudent.service.AnswerboxVO;
import com.yedam.app.yedam_examstudent.service.ExamResultVO;
import com.yedam.app.yedam_examstudent.service.QuizboxVO;
import com.yedam.app.yedam_examstudent.service.TestResultVO;
import com.yedam.app.yedam_examstudent.service.TestVO;
import com.yedam.app.yedam_subjects.service.SubjectsVO;

@Mapper
public interface CbtStudentMapper {

	
	//ㅡㅡㅡㅡㅡㅡ
	//시험결과유뮤
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
	//시험과목
	//ㅡㅡㅡㅡ
	public List<SubjectsVO> selectTestSub(SubjectsVO subjectsVO);
	//ㅡㅡㅡㅡ
	//시험목록
	//ㅡㅡㅡㅡ
	public List<TestVO> selectTestListAll(TestVO testVO);
	//ㅡㅡㅡㅡ
	//시험개수
	//ㅡㅡㅡㅡ
	public int selectTestListSize(int userId);
	
	
	
	//ㅡㅡㅡㅡㅡㅡㅡ
	//시험문제 랜덤
	//ㅡㅡㅡㅡㅡㅡㅡ
	public List<QuizboxVO> selectTestQuizRand(QuizboxVO quizboxVO);
	//ㅡㅡㅡㅡㅡㅡㅡ
	//시험보기 등록
	//ㅡㅡㅡㅡㅡㅡㅡ
	public boolean insertTestQuizExample(AnswerboxVO answerboxVO);
	//ㅡㅡㅡㅡ
	//시험상세
	//ㅡㅡㅡㅡ
	public TestVO selectTestDetail(TestVO testVO);

	
	
	//ㅡㅡㅡㅡ
	//시험시작
	//ㅡㅡㅡㅡ
	public TestVO selectTestStart(TestVO testVO);
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	//시험시작 문제정보
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	public QuizboxVO selectTestQuiz(QuizboxVO quizboxVO);
	
	
	
	
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	//시험결과 (AJAX)
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	public boolean insertTestResult(ExamResultVO examResultVO);
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	//문제제출 (AJAX)
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	public boolean insertTestSubmit(TestResultVO testResultVO);
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	//시험결과 (AJAX)
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	public boolean insertTestResultScore(ExamResultVO examResultVO);
	
	
	
	//ㅡㅡㅡㅡㅡㅡㅡ
	//시험결과 정보
	//ㅡㅡㅡㅡㅡㅡㅡ
	public ExamResultVO selectTestResult(ExamResultVO examResultVO);
	//ㅡㅡㅡㅡㅡㅡㅡ
	//시험결과 문제
	//ㅡㅡㅡㅡㅡㅡㅡ
	public List<QuizboxVO> selectTestResultQuiz(QuizboxVO quizboxVO);
}
