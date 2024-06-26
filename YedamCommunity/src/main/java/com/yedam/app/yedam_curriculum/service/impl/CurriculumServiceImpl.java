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
	
	// 수강 중인 과정 전체조회
	@Override
	public List<CurriculumVO> CurriculumList() {
		return curriculumMapper.cSelectAll();
	}

	// 수료한 과정 전체 조회
	@Override
	public List<CurriculumVO> gradSelectAll() {
		return curriculumMapper.gradSelectAll();
	}

	// 과정 선택 리스트(회원가입 때 사용)
	@Override
	public List<CurriculumVO> allCurriculumList() {
		return curriculumMapper.selectAllCurriculums();
	}

	@Override
	public int insertCurriculum(CurriculumVO curriculumVO) {
		if (curriculumMapper.insertCurriculum(curriculumVO) > 0) {
			return 1;
		} else {
			return -1;
		}
	}

	// 과정클릭 시 모달창 띄우고 그 과정의 학생 출력
	@Override
	public List<UserVO> showCurriculumStd(int curriculumId) {
		return curriculumMapper.selectCurriculumStd(curriculumId);
	}

	// 과정 삭제
	@Override
	public boolean removeCurriculum(int curriculumId) {
		return curriculumMapper.removeCurriculum(curriculumId) == 1;
	}

	@Override
	public List<CurriculumVO> subjectList(int userid) {
		return curriculumMapper.subjectSelectAll(userid);
	}
	
	// 과정별 과목명 조회
	@Override
	public List<CurriculumVO> classSubject(CurriculumVO curriculumVO) {
		return curriculumMapper.selectSubject(curriculumVO);
	}

}
