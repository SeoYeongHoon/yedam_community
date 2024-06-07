package com.yedam.app.yedam_curriculum.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yedam.app.yedam_user.service.UserVO;

public interface CurriculumService {
	// 수강 중인 과정 전체 조회
	public List<CurriculumVO> CurriculumList();
	
	// 수료한 과정 전체 조회
	public List<CurriculumVO> gradSelectAll();
	
	// 과정 추가
	public int insertCurriculum(CurriculumVO curriculumVO);
	
	// 과정별 유저 목록 출력
	public List<UserVO> showCurriculumStd(int curriculumId);

	// 과정 삭제
	public boolean removeCurriculum(int curriculumId);
	
	// 과정 선택 리스트(회원가입 때 사용)
	public List<CurriculumVO> allCurriculumList();
	
	//해당 과정 과목명 조회
	public List<CurriculumVO> subjectList(int userid);
	
	// 과정별 과목명 조회
	public List<CurriculumVO> classSubject(CurriculumVO curriculumVO);
}
