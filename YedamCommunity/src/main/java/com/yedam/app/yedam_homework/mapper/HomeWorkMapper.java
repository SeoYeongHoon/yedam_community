package com.yedam.app.yedam_homework.mapper;

import java.util.List;

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
}
