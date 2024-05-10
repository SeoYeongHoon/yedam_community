package com.yedam.app.yedam_examstudent.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yedam.app.yedam_examstudent.service.TestVO;

@Mapper
public interface CbtStudentMapper {
	//전체시험리스트 출력
	public List<TestVO> selectTestListAll();
}
