package com.yedam.app.yedam_post.mapper;

import com.yedam.app.yedam_post.service.Report;

public interface ReportMapper {
	
	//--------------------------------------------
	// 신고등록
	//--------------------------------------------
	public int insertReport(Report report);
}
