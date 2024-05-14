package com.yedam.app.yedam_user.service;

import java.util.List;

public interface UserService {

	// 회원가입 신청
	public int requestUser(RegisterVO registerVO);
	
	// 회원가입 승인
	public int insertUser(int registerId);
	
	// 로그인
//	public UserVO userLogin(UserVO userVO);
	UserVO loginCheck(UserVO userVO);
	
	public List<UserVO> stdList();
	
	// 어드민페이지 유저 리스트 전체(페이징)
	public List<UserVO> userList(int boardCountInPage);
	
	// 페이지네이션 카운트
	int userTotalCnt();

	public List<UserVO> getUsersByFilter(String filter);

	public RegisterVO getUserById(String registerId);

	public boolean removeUser(int registerId);
	
}
