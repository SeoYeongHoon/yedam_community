package com.yedam.app.yedam_user.service;

import lombok.Data;

@Data
public class UserSearchVO {
	private String userFilter;
	private String showGrad;
	private String showLearn;
	public String getUserFilter() {
		return userFilter;
	}
	public void setUserFilter(String userFilter) {
		this.userFilter = userFilter;
	}
	public String getShowGrad() {
		return showGrad;
	}
	public void setShowGrad(String showGrad) {
		this.showGrad = showGrad;
	}
	public String getShowLearn() {
		return showLearn;
	}
	public void setShowLearn(String showLearn) {
		this.showLearn = showLearn;
	}
	
	
	
}
