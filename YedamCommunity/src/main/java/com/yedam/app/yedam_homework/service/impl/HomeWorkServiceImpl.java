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
	HomeWorkMapper hMapper; //mapper 불러옴
	
	@Override
	public List<HomeWorkVO> hList() {
		return hMapper.AllList();
	}

}
