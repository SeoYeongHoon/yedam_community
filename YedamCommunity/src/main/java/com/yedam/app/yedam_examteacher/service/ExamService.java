package com.yedam.app.yedam_examteacher.service;

import java.util.List;
import java.util.Map;

public interface ExamService {
	// 전체조회
	public List<TeacherVO> allList(TeacherVO teacherVO);
	
	// 시험목록조회
	public List<TeacherVO> testList();
	
	// 시험 등록
	public int testInsert(TeacherVO teacherVO);
	
	// 문제등록
	public void quizInsert(TeacherVO teacherVO);
	
	// 문제저장소에 문제출력 -> 필터링 출력으로 기능구현함. 마지막에 삭제해야됨
	public List<TeacherVO> quizList();
	
	// 문제저장소에 지문출력 -> 나중에 삭제
	public List<TeacherVO> answerList1(TeacherVO teacherVO);
	
	// 문제에 대한 지문 출력
	public List<TeacherVO> answerList(int qId);
	
	// 문제 단건조회 => 나중에 삭제 (getQuizInfo로 진행함)
	public TeacherVO quizInfo(TeacherVO teacherVO);
	
	// 과목명 출력
	public List<TeacherVO> subjectList();
	
	// 과목명 추가
	public int subjectInsert(TeacherVO teacherVO);
	
	// 과목명 삭제
	public Map<String, Object> subjectDelete(TeacherVO teacherVO);
	
	// 문제 필터링 출력
	public List<TeacherVO> getQuizFilter(String sName);
	
	// 문제 자세히보기 단건조회
	public List<TeacherVO> getQuizInfo(int qId);
}
