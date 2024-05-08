package com.yedam.app.yedam_examteacher.mapper;

import java.util.List;

import com.yedam.app.yedam_examteacher.service.teacherVO;

public interface ExamMapper {
	// 전체조회
	public List<teacherVO> selectExamAll();
}
