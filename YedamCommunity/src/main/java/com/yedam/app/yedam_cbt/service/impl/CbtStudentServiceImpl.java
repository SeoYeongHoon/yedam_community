package com.yedam.app.yedam_cbt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.yedam_cbt.mapper.CbtStudentMapper;
import com.yedam.app.yedam_cbt.service.CbtStudentService;
import com.yedam.app.yedam_cbt.service.TestVO;

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
