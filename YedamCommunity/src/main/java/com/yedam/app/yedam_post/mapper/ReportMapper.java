package com.yedam.app.yedam_post.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yedam.app.yedam_post.service.Report;

@Mapper
public interface ReportMapper {
	
	//--------------------------------------------
	// 신고등록
	//--------------------------------------------
	public int insertReport(Report report);
	
	// 신고목록 관리자 계정에 출력
	public List<Report> selectReportList();
}
