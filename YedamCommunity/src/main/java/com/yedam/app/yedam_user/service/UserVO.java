package com.yedam.app.yedam_user.service;

import lombok.Data;

@Data
public class UserVO {
	private Integer registerId;
	private String id;
	private String password;
	private String name;
	private String tel;
	private String email;
	private String address;
	private String img;
	private String companyInfo;
//	private Integer isComplete;
	private Integer curriculumId;
}