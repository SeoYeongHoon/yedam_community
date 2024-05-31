package com.yedam.app.yedam_user.service;

import lombok.Data;

@Data
public class UserVO {
	private static final long serialVersionUID = 1L;
	
	private Integer userId;
	private String id;
	private String password;
	private String encryptedPassword;
	private String name;
	private String tel;
	private String email;
	private String address;
	private String companyInfo;
	private Integer isComplete;
	private String userType;
	private Integer curriculumId;
	private String curriculumName;

	// 이미지처리
	private String profileImageName;
	private double profileImageSize;
	private String profileImageLocation;
	private String downloadLocation;
	private String profileImageExt;
	
	// 검색필드
	private String searchQuery;
	
	// 비밀번호 초기화용 메일 토큰
	private String resetToken;
	
}
