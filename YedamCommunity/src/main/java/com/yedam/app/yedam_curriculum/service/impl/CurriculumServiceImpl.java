package com.yedam.app.yedam_curriculum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.yedam_curriculum.mapper.CurriculumMapper;
import com.yedam.app.yedam_curriculum.service.CurriculumService;
import com.yedam.app.yedam_curriculum.service.CurriculumVO;
import com.yedam.app.yedam_user.service.UserVO;

@Service
public class CurriculumServiceImpl implements CurriculumService {

	@Autowired
	CurriculumMapper curriculumMapper;
	
	@Override
	public List<CurriculumVO> CurriculumList() {
		return curriculumMapper.cSelectAll();
	}

	@Override
	public int insertCurriculum(CurriculumVO curriculumVO) {
		if (curriculumMapper.insertCurriculum(curriculumVO) > 0) {
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public List<UserVO> showCurriculumStd(int curriculumId) {
		return curriculumMapper.selectCurriculumStd(curriculumId);
	}

}
