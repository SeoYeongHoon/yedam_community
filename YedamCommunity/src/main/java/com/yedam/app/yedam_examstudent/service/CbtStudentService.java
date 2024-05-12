package com.yedam.app.yedam_examstudent.service;

import java.util.List;

public interface CbtStudentService {
	//전체시험리스트 출력
	public List<TestVO> testListAll();
	//시험상세정보 출력
	public TestVO testDetail(TestVO testVO);
}
