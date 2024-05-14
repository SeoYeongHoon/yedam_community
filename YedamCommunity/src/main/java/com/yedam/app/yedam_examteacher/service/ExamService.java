package com.yedam.app.yedam_examteacher.service;

import java.util.List;
import java.util.Map;

public interface ExamService {
	// 전체조회
	public List<TeacherVO> allList();
	
	// 시험목록조회
	public List<TeacherVO> testList();
	
	// 문제등록
	public void quizInsert(TeacherVO teacherVO);
	
	// 문제저장소에 문제출력
	public List<TeacherVO> quizList();
	
	// 문제저장소에 지문출력
	public List<TeacherVO> answerList();
	
	// 문제 단건조회
	public TeacherVO quizInfo(TeacherVO teacherVO);
	
	// 과목명 출력
	public List<TeacherVO> subjectList();
	
	// 과목명 추가
	public int subjectInsert(TeacherVO teacherVO);
	
	// 과목명 삭제
	public Map<String, Object> subjectDelete(TeacherVO teacherVO);
}
