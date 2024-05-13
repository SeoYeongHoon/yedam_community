package com.yedam.app.yedam_examstudent.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yedam.app.yedam_examstudent.service.TestVO;

@Mapper
public interface CbtStudentMapper {
	//전체시험목록 조회
	public List<TestVO> selectTestListAll();
	//시험상세정보 조회
	public TestVO selectTestDetail(TestVO testVO);
	//시험시작정보 조회
	public TestVO selectTestStart(TestVO testVO);
}
