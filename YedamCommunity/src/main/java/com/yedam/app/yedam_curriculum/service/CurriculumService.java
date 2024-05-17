package com.yedam.app.yedam_curriculum.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CurriculumService {
	//과정 전체 조회
	public List<CurriculumVO> CurriculumList();
	
	// 과정 추가
	public int insertCurriculum(CurriculumVO curriculumVO);
}
