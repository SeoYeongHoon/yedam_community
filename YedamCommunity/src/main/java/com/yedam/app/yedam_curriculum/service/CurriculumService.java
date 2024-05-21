package com.yedam.app.yedam_curriculum.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yedam.app.yedam_user.service.UserVO;

public interface CurriculumService {
	//과정 전체 조회
	public List<CurriculumVO> CurriculumList();
	
	// 과정 추가
	public int insertCurriculum(CurriculumVO curriculumVO);
	
	// 과정별 유저 목록 출력
	public List<UserVO> showCurriculumStd(int curriculumId);

	// 과정 삭제
	public boolean removeCurriculum(int curriculumId);
}
