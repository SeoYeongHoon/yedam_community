package com.yedam.app.yedam_admin.service;

import java.util.List;

import com.yedam.app.yedam_post.service.Report;

public interface AdminService {

	// 신청목록 출력
	public List<Report> getReportList();
}
