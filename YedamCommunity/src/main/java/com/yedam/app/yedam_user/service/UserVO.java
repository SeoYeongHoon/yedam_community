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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(String companyInfo) {
		this.companyInfo = companyInfo;
	}

	public Integer getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(Integer isComplete) {
		this.isComplete = isComplete;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Integer getCurriculumId() {
		return curriculumId;
	}

	public void setCurriculumId(Integer curriculumId) {
		this.curriculumId = curriculumId;
	}

	public String getCurriculumName() {
		return curriculumName;
	}

	public void setCurriculumName(String curriculumName) {
		this.curriculumName = curriculumName;
	}

	public String getProfileImageName() {
		return profileImageName;
	}

	public void setProfileImageName(String profileImageName) {
		this.profileImageName = profileImageName;
	}

	public double getProfileImageSize() {
		return profileImageSize;
	}

	public void setProfileImageSize(double profileImageSize) {
		this.profileImageSize = profileImageSize;
	}

	public String getProfileImageLocation() {
		return profileImageLocation;
	}

	public void setProfileImageLocation(String profileImageLocation) {
		this.profileImageLocation = profileImageLocation;
	}

	public String getDownloadLocation() {
		return downloadLocation;
	}

	public void setDownloadLocation(String downloadLocation) {
		this.downloadLocation = downloadLocation;
	}

	public String getProfileImageExt() {
		return profileImageExt;
	}

	public void setProfileImageExt(String profileImageExt) {
		this.profileImageExt = profileImageExt;
	}

	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
