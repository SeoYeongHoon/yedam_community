package com.yedam.app.yedam_user.mapper;

import java.util.List;

import com.yedam.app.yedam_user.service.UserVO;

public interface UserMapper {

	// 회원가입
	public int insertUser(UserVO userVO);
	
	// 로그인
//	public UserVO userLogin(UserVO userVO);
	UserVO selectUser(UserVO userVO);
	
	// 어드민페이지 유저 리스트 전체
	public List<UserVO> selectAllList(int page);
	
	// 페이지네이션 카운트
	int getTotalCnt();
	
}
