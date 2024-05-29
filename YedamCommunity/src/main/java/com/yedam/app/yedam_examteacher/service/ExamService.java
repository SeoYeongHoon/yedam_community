package com.yedam.app.yedam_examteacher.service;

import java.util.List;
import java.util.Map;

public interface ExamService {
	//--------------------------------------------
	// 시험목록조회/등록 페이지 기능 모음
	//--------------------------------------------
	//public List<TeacherVO> testList(int cId); // 시험목록 조회
	public List<TeacherVO> testList(int cId, int page, String searchQuery); // 시험목록 페이징 테스트중
	int testListCnt(int cId, String searchQuery); 
	public int testInsert(TeacherVO teacherVO); // 시험 등록
	public List<TeacherVO> userList(TeacherVO teacherVO); // 시험 대상자 출력
	public List<TeacherVO> reTestUserList(TeacherVO teacherVO); // 시험 대상자 출력
	public int quizboxInsert(QuizVO quizVO); // 시험에 출제될 문제 등록
	public int testUserInsert(int[] userId); // 시험 대상자 등록
	
	//--------------------------------------------
	// 문제 조회/등록 페이지 기능 모음
	//--------------------------------------------
	public void quizInsert(TeacherVO teacherVO); //객관식문제 등록
	public void quizInsertJu(TeacherVO teacherVO); //주관식문제 등록
	public List<TeacherVO> quizList(); // 문제저장소에 문제출력 -> 필터링 출력으로 기능구현함. 마지막에 삭제해야됨
	public List<TeacherVO> answerList1(TeacherVO teacherVO); // 문제저장소에 지문출력 -> 나중에 삭제
	public List<TeacherVO> answerList(int qId); // 문제에 대한 지문 출력
	public TeacherVO quizInfo(TeacherVO teacherVO); // 문제 단건조회 => 나중에 삭제 (getQuizInfo로 진행함)
	public List<TeacherVO> subjectList(); // 과목명 출력(문제 필터할때 사용)
	public int subjectInsert(TeacherVO teacherVO); // 과목명 추가
	public int subjectDelete(TeacherVO teacherVO); // 과목명 삭제
	//public Map<String, Object> subjectDelete(TeacherVO teacherVO); // 과목명 삭제 테스트중인 코드
	//public List<TeacherVO> getQuizFilter(String sName); // 문제 필터링 출력
	public List<TeacherVO> getQuizFilter(int page, String subjectName); // 문제 필터링 페이징 출력
	int getQuizCount(String subjectName); // 문제 수 count
	public List<TeacherVO> getQuizInfo(int qId); // 문제 자세히보기 (단건조회)
	public List<TeacherVO> currList(); // 과정명 출력(과목명 추가할때 사용)
	public List<TeacherVO> subjectListOfCurr(int cId); // 과목명 출력(과목 추가/삭제시 사용)
	
	
	//--------------------------------------------
	// 교수님 메인페이지 기능 모음
	//--------------------------------------------
	public List<TeacherVO> subjectAvg(int cId); // 강의실별 과목평균점수 조회
	public List<TeacherVO> subTestList(int cId); // 강의실별 시험리스트 조회
	public TeacherVO testInfo(TeacherVO teacherVO); // 시험정보 단건조회
	public List<TeacherVO> subUserList(int cId); // 강의실별 수강생 조회
	public List<TeacherVO> classList(int cId); // 강의실에 따른 과정명 조회
	public List<TeacherVO> userTestResult(int tId); // 강의실 - 선택한 시험항목 결과 조회
	public TeacherVO userTestInfo(TeacherVO teacherVO); // 시험본 학생 개개인의 수강정보 단건조회
	public List<TeacherVO> userScoreList(int uId); // 학생 개개인의 과목 점수리스트 조회
	
	//--------------------------------------------
	// 유저 피드백 작성페이지 기능 모음
	//--------------------------------------------
	public TeacherVO userFeedInfo(TeacherVO teacherVO); // 시험본 개인 정보 조회
	public Map<String, Object> feedUpdate(TeacherVO teacherVO); // 피드백 등록(수정)
}
