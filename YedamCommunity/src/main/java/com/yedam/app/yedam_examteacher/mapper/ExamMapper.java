package com.yedam.app.yedam_examteacher.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.app.yedam_examteacher.service.TeacherVO;


public interface ExamMapper {

	// 시험목록 전체조회
	public List<TeacherVO> selectExamAll();

	// 유저 정보 출력 (시험 출제시 필요)
	public List<TeacherVO> selectUserAll(TeacherVO teacherVO);
	
	// 문제 저장소에 문제 출력 => 필터출력으로 대체. 나중에 삭제.
	public List<TeacherVO> selectQuizAll();
	// 문제 저장소에 지문 출력 -> 나중에 삭제
	public List<TeacherVO> selectAnswerAll(TeacherVO teacherVO);
	
	// 문제 지문 출력
	public List<TeacherVO> quizAnswer(@Param("qId") int qId);
	
	// 과목명 출력
	public List<TeacherVO> selectSubjectAll();	
	
	// 시험 등록
	public int insertTest(TeacherVO teacherVO);
	
	// 문제 등록
	public int insertQuiz(TeacherVO teacherVO); //객관식문제
	public int insertQuizJu(TeacherVO teacherVO); //주관식문제
	
	// 문제 정답지문 등록
	public int insertAnswer(TeacherVO teacherVO);
	public int insertAnswer2(TeacherVO teacherVO);
	public int insertAnswer3(TeacherVO teacherVO);
	public int insertAnswer4(TeacherVO teacherVO);
	public int insertAnswer5(TeacherVO teacherVO);
	public int insertAnswerJu(TeacherVO teacherVO);
	
	// 문제 단건 조회 -> 나중에 삭제
	public TeacherVO selectQuiz(TeacherVO teacherVO);
	
	// 과목 등록
	public int insertSubject(TeacherVO teacherVO);
	
	// 과목 삭제
	public int deleteSubject(int subjectId);
	
	// 등록된 문제 필터출력 테스트
	public List<TeacherVO> filterQuiz(@Param("sName") String sName);
	
	// 문제 자세히보기 단건조회
	public List<TeacherVO> infoQuiz(@Param("qId") int qId);
	
	// 강의실 정보 출력(과목평균,시험리스트,수강생)
	public List<TeacherVO> subjectAvg(@Param("classId") int cId);
	public List<TeacherVO> testList(@Param("classId") int cId);
	public List<TeacherVO> userList(@Param("classId") int cId);
	public List<TeacherVO> currList(@Param("classId") int cId);
	public TeacherVO testInfo(TeacherVO teacherVO); // 시험목록 단건조회
	public List<TeacherVO> testResult(@Param("testId") int tId);
}
