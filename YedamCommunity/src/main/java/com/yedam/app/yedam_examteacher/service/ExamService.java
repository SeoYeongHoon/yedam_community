package com.yedam.app.yedam_examteacher.service;

import java.util.List;

public interface ExamService {
	// 시험목록조회
	public List<TeacherVO> testList();
	
	// 문제등록
	public int quizInsert(TeacherVO teacherVO);
	
	// 문제저장소에 문제출력
	public List<TeacherVO> quizList();
	
	// 문제저장소에 지문출력
	public List<TeacherVO> answerList();
}
