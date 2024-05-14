package com.yedam.app.yedam_subjects.mapper;

import java.util.List;

import com.yedam.app.yedam_subjects.service.SubjectsVO;

public interface SubjectMapper {
	
	//과목 전체 조회
	public List<SubjectsVO> selectSubjectAll(SubjectsVO subjectsVO);
}
