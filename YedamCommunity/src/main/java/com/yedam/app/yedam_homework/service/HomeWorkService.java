package com.yedam.app.yedam_homework.service;

import java.util.List;

public interface HomeWorkService {

	// 과제 전체 조회
	public List<HomeWorkVO> homeworkList();

	// 과제 등록
	public int homeworkInsert(HomeWorkVO homeworkVO);
	
	// 과제 상세 조회
	public HomeWorkVO homeworkInfo(HomeWorkVO homeworkVO);
	
	// 과목명 조회
	public List<HomeWorkVO> subjectNameList(HomeWorkVO homeworkVO);

}
