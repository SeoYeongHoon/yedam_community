package com.yedam.app.yedam_examteacher.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yedam.app.yedam_examteacher.service.TeacherVO;


public interface ExamMapper {
	//--------------------------------------------
	// 시험목록/등록 페이지 기능모음
	//--------------------------------------------
	public List<TeacherVO> selectExamAll(Map<String, Object> params); // 시험문제 조회(페이징)
	int testTotalCnt(int cId, String searchQuery); // 시험문제 count
	public int insertTest(TeacherVO teacherVO); // 시험 등록
	public List<TeacherVO> selectUserAll(TeacherVO teacherVO); // 시험 대상자 조회
	public int insertQuizbox(TeacherVO teacherVO); // 시험에 출제할 문제 등록
	public int insertTestUser(TeacherVO teacherVO); // 시험 대상자 등록
	public List<TeacherVO> reTestUser(TeacherVO teacherVO); // 재시험 대상자 등록
	
	//--------------------------------------------
	// 문제 등록/조회 페이지 기능 모음	
	//--------------------------------------------
	public List<TeacherVO> selectQuizAll(); // 문제 저장소에 문제 출력 => 필터출력으로 대체. 나중에 삭제.
	public List<TeacherVO> selectAnswerAll(TeacherVO teacherVO); // 문제 저장소에 지문 출력 -> 나중에 삭제
	public List<TeacherVO> quizAnswer(@Param("qId") int qId); // 문제 지문 출력
	public int insertQuiz(TeacherVO teacherVO); //객관식문제 등록
	public int insertQuizJu(TeacherVO teacherVO); //주관식문제 등록
	public int insertAnswer(TeacherVO teacherVO); // 문제 정답지문 등록
	public int insertAnswer2(TeacherVO teacherVO); // 문제 정답지문 등록
	public int insertAnswer3(TeacherVO teacherVO); // 문제 정답지문 등록
	public int insertAnswer4(TeacherVO teacherVO); // 문제 정답지문 등록
	public int insertAnswer5(TeacherVO teacherVO); // 문제 정답지문 등록
	public int insertAnswerJu(TeacherVO teacherVO); // 주관식 문제 정답지문 등록
	public TeacherVO selectQuiz(TeacherVO teacherVO); // 문제 단건 조회 -> 나중에 삭제
	public List<TeacherVO> selectCurr(); // 과정명 출력(과목 등록할때 사용)
	public int insertSubject(TeacherVO teacherVO); // 과목 등록
	public int deleteSubject(TeacherVO teacherVO); // 과목 삭제
	public List<TeacherVO> selectSubjectAll(); // 과목명 출력	(문제 필터링할때 사용)
	public List<TeacherVO> subjectOfCurr(@Param("curriId") int cId); // 과목명 출력 (과목 추가/삭제때 사용)
	//public List<TeacherVO> filterQuiz(@Param("sName") String sName); // 등록된 문제 필터출력 
	List<TeacherVO> filterQuiz(Map<String, Object> params); // 문제 조회(페이징)
	int getQuizCnt(String subjectName); // 문제 count
	public List<TeacherVO> infoQuiz(@Param("qId") int qId); // 문제 자세히보기 단건조회
	
	//--------------------------------------------
	// 강의실 정보 출력(과목평균,시험리스트,수강생)
	//--------------------------------------------
	public List<TeacherVO> subjectAvg(@Param("classId") int cId); // 과목평균점수
	public List<TeacherVO> testList(@Param("classId") int cId); // 강의실별 시험리스트
	public List<TeacherVO> userList(@Param("classId") int cId); // 강의실별 수강생리스트
	public List<TeacherVO> currList(@Param("classId") int cId); // 과정명 출력
	public TeacherVO testInfo(TeacherVO teacherVO); // 시험목록 단건조회 페이지
	public List<TeacherVO> testResult(@Param("testId") int tId); // 시험본 학생들 점수 전체리스트
	public TeacherVO userTestInfo(TeacherVO teacherVO); // 시험본 학생 개개인의 수강정보 단건조회
	public List<TeacherVO> userScore(@Param("userId") int uId); // 학생 개개인의 과목당 점수리스트
	
	//--------------------------------------------
	// 학생 피드백 작성 페이지 기능모음
	//--------------------------------------------
	public TeacherVO userFeed(TeacherVO teacherVO); // 유저 시험 결과 조회
	public int feedAdd(TeacherVO teacherVO);
}
