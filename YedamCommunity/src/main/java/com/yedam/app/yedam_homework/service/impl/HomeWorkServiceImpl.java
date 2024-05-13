package com.yedam.app.yedam_homework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.yedam_homework.mapper.HomeWorkMapper;
import com.yedam.app.yedam_homework.service.HomeWorkVO;
import com.yedam.app.yedam_homework.service.HomeWorkService;

@Service
public class HomeWorkServiceImpl implements HomeWorkService{

	@Autowired
	HomeWorkMapper homeworkMapper; //mapper 불러옴

	// 과제 목록 조회
	@Override
	public List<HomeWorkVO> homeworkList() {
		return homeworkMapper.hselectAll();
	}
	
	// 과제 등록
	@Override
	public int homeworkInsert(HomeWorkVO homeworkVO) {
		return homeworkMapper.insertHomework(homeworkVO);
	}

}
