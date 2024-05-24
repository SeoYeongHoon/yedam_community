package com.yedam.app.yedam_homework.mapper;

import java.util.List;

import com.yedam.app.yedam_homework.service.HomeWorkTargetVO;
import com.yedam.app.yedam_homework.service.HomeWorkVO;

public interface HomeWorkMapper {

	// 과제전체조회
	public List<HomeWorkVO> selectHomeworkAll();

	// 과제등록
	public int insertHomework(HomeWorkVO homeworkVO);

	// 과제 상세 조회
	public HomeWorkVO selectHomework(HomeWorkVO homeworkVO);

	// 과목명 조회
	public List<HomeWorkVO> selectSubjectName(HomeWorkVO homeworkVO);

	// 과제 대상자 등록
	public int insertHomeworkTarget(HomeWorkTargetVO homeworktargetVO);
	
	//과제 대상자 조회
	public HomeWorkTargetVO selectHomeworktarget(HomeWorkVO homeworkVO);
	//강의실 카테고리
	public List<HomeWorkVO> selectClass(int curriculumId);
}
