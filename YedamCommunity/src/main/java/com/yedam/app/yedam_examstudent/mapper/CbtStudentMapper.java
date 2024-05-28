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

	//ㅡㅡㅡㅡㅡㅡㅡㅡ
	//마이페이지 정보
	//ㅡㅡㅡㅡㅡㅡㅡㅡ
	public UserVO selectMyPageInfo(int userId);
	//ㅡㅡㅡㅡ
	//회원탈퇴
	//ㅡㅡㅡㅡ
	public int deleteUser(int userId);
	//ㅡㅡㅡㅡㅡㅡ
	//최근시험목록
	//ㅡㅡㅡㅡㅡㅡ
	public List<TestVO> selectRecentTest(int userId);
	
	
	
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
	//시험목록
	//ㅡㅡㅡㅡ
	public List<TestVO> selectTestListAll(TestVO testVO);
	//ㅡㅡㅡㅡ
	//시험과목
	//ㅡㅡㅡㅡ
	public List<TestVO> selectUserSubject(int userId);
	
	
	
	//ㅡㅡㅡㅡㅡㅡㅡ
	//시험문제 랜덤
	//ㅡㅡㅡㅡㅡㅡㅡ
	public List<QuizboxVO> selectTestQuizRand(QuizboxVO quizboxVO);
	//ㅡㅡㅡㅡ
	//시험상세
	//ㅡㅡㅡㅡ
	public TestVO selectTestDetail(TestVO testVO);

	
	
	//ㅡㅡㅡㅡ
	//시험시작
	//ㅡㅡㅡㅡ
	public TestVO selectTestStart(TestVO testVO);
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	//시험문제 (AJAX)
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	public List<TestVO> selectTestQuiz(TestVO testVO);
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	//시험시작 문제정보
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	public QuizboxVO selectTestQuiz1(QuizboxVO quizboxVO);
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	//시험시작 보기정보
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	public List<AnswerVO> selectTestQuiz2(AnswerVO answerVO);
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
