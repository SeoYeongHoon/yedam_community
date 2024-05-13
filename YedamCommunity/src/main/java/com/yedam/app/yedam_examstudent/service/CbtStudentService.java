package com.yedam.app.yedam_examstudent.service;

import java.util.List;

public interface CbtStudentService {
	//전체시험목록 조회
	public List<TestVO> testListAll();
	//시험상세정보 조회
	public TestVO testDetail(TestVO testVO);
	//시험시작정보 조회
	public TestVO testStart(TestVO testVO);
}
