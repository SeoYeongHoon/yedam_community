package com.yedam.app.yedam_examteacher.mapper;

import java.util.List;

import com.yedam.app.yedam_examteacher.service.TeacherVO;


public interface ExamMapper {
	// 시험목록 전체조회
	public List<TeacherVO> selectExamAll();
	
	// 문제 등록
	public int insertQuiz(TeacherVO teacherVO);
	
	// 문제 정답지문 등록
	public int insertAnswer(TeacherVO teacherVO);
	public int insertAnswer2(TeacherVO teacherVO);
	public int insertAnswer3(TeacherVO teacherVO);
	public int insertAnswer4(TeacherVO teacherVO);
	public int insertAnswer5(TeacherVO teacherVO);
	
	// 문제 저장소에 문제 출력
	public List<TeacherVO> selectQuizAll();
	
	// 문제 저장소에 지문 출력
	public List<TeacherVO> selectAnswerAll();
	
	// 문제 단건 조회
	public TeacherVO selectQuiz(TeacherVO teacherVO);
	
	// 과목명 출력
	public List<TeacherVO> selectSubjectAll();
	
	// 과목 등록
	public int insertSubject(TeacherVO teacherVO);
	
	// 과목 삭제
	public int deleteSubject(int subjectId);
}
