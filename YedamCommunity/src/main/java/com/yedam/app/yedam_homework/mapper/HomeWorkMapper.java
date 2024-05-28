package com.yedam.app.yedam_homework.mapper;

import java.util.List;
import java.util.Map;

import com.yedam.app.yedam_homework.service.HomeWorkTargetVO;
import com.yedam.app.yedam_homework.service.HomeWorkVO;
import com.yedam.app.yedam_homework.service.ReplyVO;

public interface HomeWorkMapper {

	// 과제전체조회
	public List<HomeWorkVO> selectHomeworkAll();

	// 해당 학생 과제 조회
	public List<HomeWorkVO> selectUserHomework(int userid);

	// 과제등록
	public int insertHomework(HomeWorkVO homeworkVO);
	// 과제 수정
	public int updateHomework(HomeWorkVO homeworkVO);

	// 과제 삭제
	public int deleteHomework(int homeworkId);

	// 과제 상세 조회
	public HomeWorkVO selectHomework(HomeWorkVO homeworkVO);

	// 과목명 조회
	public List<HomeWorkVO> selectSubjectName(HomeWorkVO homeworkVO);

	// 과제 대상자 등록
	public int insertHomeworkTarget(HomeWorkTargetVO homeworktargetVO);

	// 과제 대상자 조회
	public HomeWorkTargetVO selectHomeworktarget(HomeWorkVO homeworkVO);

	// 강의실 카테고리
	public List<HomeWorkVO> selectClass(int curriculumId);

	// 과목명 카테고리
	public List<HomeWorkVO> selectSubject(int subjectId, int userId);

	// 최근 과제 출력
	public List<HomeWorkVO> selectRecentHomework(int userId);
	
	// 과제 페이징 및 필터링
	public List<HomeWorkVO> getHomeworksByFilter(Map<String, Object> params);
	
	// 페이징용 카운트
	int getTotalCnt(int filter, String searchQuery);
}
