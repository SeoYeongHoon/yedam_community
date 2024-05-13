package com.yedam.app.yedam_user.service;

import java.util.List;

public interface UserService {

	// 회원가입
	public int insertUser(UserVO userVO);
	
	// 로그인
//	public UserVO userLogin(UserVO userVO);
	UserVO loginCheck(UserVO userVO);
	
	// 어드민페이지 유저 리스트 전체
	public List<UserVO> userList(int boardCountInPage);
	
	// 페이지네이션 카운트
	int userTotalCnt();
	
}
