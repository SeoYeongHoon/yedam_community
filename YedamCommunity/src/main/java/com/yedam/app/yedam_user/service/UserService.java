package com.yedam.app.yedam_user.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

	// 회원가입 신청
	public int requestUser(RegisterVO registerVO);
	// 회원가입 승인
	public int insertUser(int registerId);
	// 다수 회원가입 요청 승인(체크된)
	@Transactional
	public void insertCheckedUsers(List<String> registerIds);
	
	// 로그인
//	public UserVO userLogin(UserVO userVO);
	UserVO loginCheck(UserVO userVO);
//	public UserDetails loadUserByUsername(String id);
	
	
	public List<UserVO> stdList();
	
	// 어드민페이지 유저 리스트 전체(페이징)
	public List<UserVO> userList();
	
	// 페이지네이션 카운트
	int userTotalCnt();

	public List<UserVO> getUsersByFilter(String filter);

	// 회원가입 신청 유저 상세정보
	public RegisterVO getReqById(String registerId);
	
	// 유저 상세정보
	public UserVO getUserById(String userId);

//	회원가입 신청거절(삭제)
	public boolean refuseUser(int registerId);
	
//	유저 삭제
	public boolean removeUser(int userId);
	
	
}
