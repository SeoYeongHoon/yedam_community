package com.yedam.app.yedam_examteacher.service;

import java.util.List;
import java.util.Map;

public interface ExamService {
	// 전체조회-테스트중. 나중에 삭제
	public List<TeacherVO> allList(TeacherVO teacherVO);
	
	// 시험목록조회
	public List<TeacherVO> testList();

	// 시험 등록
	public int testInsert(TeacherVO teacherVO);
	
	// 시험 대상자 출력
	public List<TeacherVO> userList(TeacherVO teacherVO);
	
	// 문제등록
	public void quizInsert(TeacherVO teacherVO); //객관식문제
	public void quizInsertJu(TeacherVO teacherVO); //주관식문제
	
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
	
	// 강의실별 과목평균점수 조회
	public List<TeacherVO> subjectAvg(int cId);
	// 강의실별 시험리스트 조회
	public List<TeacherVO> subTestList(int cId);	
	public TeacherVO testInfo(TeacherVO teacherVO);
	// 강의실별 수강생 조회
	public List<TeacherVO> subUserList(int cId);
	// 강의실에 따른 과정명 조회
	public List<TeacherVO> classList(int cId);
}
