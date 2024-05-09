package com.yedam.app.yedam_user.service;

public interface UserService {

	// 회원가입
	public int insertUser(UserVO userVO);
	
	// 로그인
//	public UserVO userLogin(UserVO userVO);
	UserVO loginCheck(UserVO userVO);
}
