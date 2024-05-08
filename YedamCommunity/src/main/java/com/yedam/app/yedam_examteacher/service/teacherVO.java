package com.yedam.app.yedam_examteacher.service;

import java.util.Date;

import lombok.Data;

@Data
public class teacherVO {
	private int examNo;
	private String examName;
	private Date examEnd;
	private Date examTime;
}
