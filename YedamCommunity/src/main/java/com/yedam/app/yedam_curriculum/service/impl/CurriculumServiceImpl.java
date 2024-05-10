package com.yedam.app.yedam_curriculum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yedam.app.yedam_curriculum.mapper.CurriculumMapper;
import com.yedam.app.yedam_curriculum.service.CurriculumService;
import com.yedam.app.yedam_curriculum.service.CurriculumVO;

public class CurriculumServiceImpl implements CurriculumService {

	@Autowired
	CurriculumMapper cMapper;
	
	@Override
	public List<CurriculumVO> CurriculumList() {
		return cMapper.cSelectAll();
	}

}
