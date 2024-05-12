package com.yedam.app.yedam_examstudent.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.yedam_examstudent.mapper.CbtStudentMapper;
import com.yedam.app.yedam_examstudent.service.CbtStudentService;
import com.yedam.app.yedam_examstudent.service.TestVO;

@Service
public class CbtStudentServiceImpl implements CbtStudentService{

	// mapper 주입
	@Autowired
	CbtStudentMapper cbtStudentMapper;
	
	// mapper 메소드 호출
	@Override
	public List<TestVO> testListAll() {
		return cbtStudentMapper.selectTestListAll();
	}

}
