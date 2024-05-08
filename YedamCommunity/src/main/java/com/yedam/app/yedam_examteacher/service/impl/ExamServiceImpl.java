package com.yedam.app.yedam_examteacher.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.yedam_examteacher.mapper.ExamMapper;
import com.yedam.app.yedam_examteacher.service.ExamService;
import com.yedam.app.yedam_examteacher.service.teacherVO;

@Service
public class ExamServiceImpl implements ExamService{
	@Autowired
	ExamMapper examMapper;
	
	@Override
	public List<teacherVO> examList() {
		return examMapper.selectExamAll();
	}
	
	
}
