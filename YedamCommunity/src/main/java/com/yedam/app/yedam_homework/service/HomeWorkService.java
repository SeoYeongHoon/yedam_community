package com.yedam.app.yedam_homework.service;

import java.util.List;
import java.util.Map;

public interface HomeWorkService {

	// 과제 전체 조회
	public List<HomeWorkVO> homeworkList();

	// 해당 학생 과제 조회
	public List<HomeWorkVO> userHomeworkList(int userid);

	// 과제 등록
	public int homeworkInsert(HomeWorkVO homeworkVO);

	// 과제 수정
	public Map<String, Object> homeworkUpdate(HomeWorkVO homeworkVO);

	// 과제 삭제
	public int homeworkDelete(int homeworkId);

	// 과제 상세 조회
	public HomeWorkVO homeworkInfo(HomeWorkVO homeworkVO);

	// 과목명 조회
	public List<HomeWorkVO> subjectNameList(HomeWorkVO homeworkVO);

	// 과제 대상자 등록
	public int homeworkTargetInsert(HomeWorkTargetVO homeworktargetVO);

	// 과제 대상자 조회
	public HomeWorkTargetVO homeworktargetList(HomeWorkVO homeworkVO);

	// 강의실 카테고리
	public List<HomeWorkVO> classCategory(int curriculumId);

	// 과목명 카테고리
	public List<HomeWorkVO> subjectCategory(int subjectId, int userId);

	// 최근 과제 출력
	public List<HomeWorkVO> getRecentTest(int userId);

	// 과제 페이징 및 필터링(교수)
	public List<HomeWorkVO> getHomeworksByFilter(int filter, int page, String searchQuery);
	
	// 과제 페이징 및 필터링(학생)
	public List<HomeWorkVO> getHomeworksByFilterStudent(int filter, int page, String searchQuery, int userId);

	// 페이징용 카운트(교수)
	public int getTotalCnt(int filter, String searchQuery);
	
	// 페이징용 카운트(학생)
	public int getTotalCntStudent(int filter, String searchQuery, int userId);
	

}
