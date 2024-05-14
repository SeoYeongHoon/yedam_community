package com.yedam.app.yedam_subjects.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.yedam_subjects.mapper.SubjectMapper;
import com.yedam.app.yedam_subjects.service.SubjectService;
import com.yedam.app.yedam_subjects.service.SubjectsVO;
@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	SubjectMapper subjectMapper;
	@Override
	public List<SubjectsVO> SubjectList(SubjectsVO subjectsVO) {
		return subjectMapper.selectSubjectAll(subjectsVO);
	}

}
