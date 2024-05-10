package com.yedam.app.yedam_homework.service;

import java.util.List;

public interface HomeWorkService {

	// 과제 전체 조회
	public List<HomeWorkVO> homeworkList();

	// 과제 등록
	public int homeworkInsert(HomeWorkVO homeworkVO);

}
