package com.yedam.app.yedam_admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.yedam_admin.service.AdminService;
import com.yedam.app.yedam_post.mapper.ReportMapper;
import com.yedam.app.yedam_post.service.Report;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	ReportMapper reportMapper;
	
	@Override
	public List<Report> getReportList() {
		return reportMapper.selectReportList();
	}
	
}
