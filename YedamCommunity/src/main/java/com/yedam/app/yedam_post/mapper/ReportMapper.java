package com.yedam.app.yedam_post.mapper;

import com.yedam.app.yedam_post.service.ReportVO;

public interface ReportMapper {
	
	//--------------------------------------------
	// 신고등록
	//--------------------------------------------
	public int insertReport(ReportVO reportVO);
}
