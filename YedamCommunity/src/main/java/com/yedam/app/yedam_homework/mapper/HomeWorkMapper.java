package com.yedam.app.yedam_homework.mapper;

import java.util.List;

import com.yedam.app.yedam_homework.service.HomeWorkVO;

public interface HomeWorkMapper {

	// 과제전체조회
	public List<HomeWorkVO> AllList();
}
