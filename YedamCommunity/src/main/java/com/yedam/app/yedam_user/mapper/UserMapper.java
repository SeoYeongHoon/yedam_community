package com.yedam.app.yedam_user.mapper;

import com.yedam.app.yedam_user.service.UserVO;

public interface UserMapper {

	// 회원가입
	public int insertUser(UserVO userVO);
	
	// 로그인
//	public UserVO userLogin(UserVO userVO);
	UserVO selectUser(UserVO userVO);
	
}
