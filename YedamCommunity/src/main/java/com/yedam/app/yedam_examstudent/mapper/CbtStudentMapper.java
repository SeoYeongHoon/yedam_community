package com.yedam.app.yedam_examstudent.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yedam.app.yedam_examstudent.service.AnswerVO;
import com.yedam.app.yedam_examstudent.service.ExamResultVO;
import com.yedam.app.yedam_examstudent.service.QuizboxVO;
import com.yedam.app.yedam_examstudent.service.TestResultVO;
import com.yedam.app.yedam_examstudent.service.TestVO;
import com.yedam.app.yedam_user.service.UserVO;

@Mapper
public interface CbtStudentMapper {

	//마이페이지 정보
	public UserVO selectMyPageInfo(int userId);
	//회원탈퇴
	public int deleteUser(int userId);
	//최근시험목록
	public List<TestVO> selectRecentTest(int userId);
	
	
	//시험결과유뮤
	public int isTestResult(TestVO testVO);
	//피드백유무
	public int isTestFeedback(TestVO testVO);
	//재시험유무
	public int isTestReexam(TestVO testVO);
	//시험목록
	public List<TestVO> selectTestListAll(int userId);
	
	//시험문제 랜덤
	public List<QuizboxVO> selectTestQuizRand(QuizboxVO quizboxVO);
	//시험상세
	public TestVO selectTestDetail(TestVO testVO);

	//시험시작
	public TestVO selectTestStart(TestVO testVO);
	//시험문제 (AJAX)
	public List<TestVO> selectTestQuiz(TestVO testVO);
	//시험시작 문제정보
	public QuizboxVO selectTestQuiz1(QuizboxVO quizboxVO);
	//시험시작 보기정보
	public List<AnswerVO> selectTestQuiz2(AnswerVO answerVO);
	//시험결과 (AJAX)
	public boolean insertTestResult(ExamResultVO examResultVO);
	//문제제출 (AJAX)
	public boolean insertTestSubmit(TestResultVO testResultVO);
	//시험결과 (AJAX)
	public boolean insertTestResultScore(ExamResultVO examResultVO);
	
	//시험결과 정보
	public ExamResultVO selectTestResult(ExamResultVO examResultVO);
	//시험결과 문제정보
	public List<QuizboxVO> selectTestResultQuiz1(QuizboxVO quizboxVO);
	//시험결과 보기 정보
	public List<AnswerVO> selectTestResultQuiz2(AnswerVO answerVO);
	//테스트
	public List<QuizboxVO> selectTest(QuizboxVO quizboxVO);
}
