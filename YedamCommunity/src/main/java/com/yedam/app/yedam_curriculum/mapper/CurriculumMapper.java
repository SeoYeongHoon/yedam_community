package com.yedam.app.yedam_curriculum.mapper;

import java.util.List;

import com.yedam.app.yedam_curriculum.service.CurriculumVO;

public interface CurriculumMapper {

	// 수강과정 리스트
	public List<CurriculumVO> cSelectAll();
	
	// 수강과정 등록
	public int insertCurriculum(CurriculumVO curriculumVO);
}
