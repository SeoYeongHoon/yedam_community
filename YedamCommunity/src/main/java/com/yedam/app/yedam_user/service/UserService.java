package com.yedam.app.yedam_user.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

public interface UserService {

	// CSV 학생 데이터 tempusers 테이블에 저장
	public void insertTempUsers(Map<String, String> row);
	
	// 회원가입 프로세스
	@Transactional
	public void registerUser(UserVO userVO);

//	// 회원가입 신청
//	public void requestUser(RegisterVO registerVO);
//	
//	// 회원가입 승인
//	public void insertUser(UserVO userVO);
	
	// 회원가입 수동 승인
	public int approveUser(int id);
	
	// 다수 회원가입 요청 승인(체크된)
	@Transactional
	public void insertCheckedUsers(List<String> registerIds);
	
	// 로그인
	UserVO loginCheck(UserVO userVO);
	
	// 아이디 찾기
	UserVO findUserId(UserVO userVO);
	
	// 비밀번호 찾기
	UserVO findUserPw(UserVO userVO);
	
	// Spring Security용 메소드
//	UserVO findByUsername(String id);
	
	public List<UserVO> stdList();
	
	// 페이지네이션 카운트
	int userTotalCnt(String filter, String searchQuery);

	// 회원가입 완료된 유저들 목록 출력
	public List<UserVO> getUsersByFilter(String filter, int page, String searchQuery);

	// 회원가입 신청 유저 상세정보
	public RegisterVO getReqById(String registerId);
	
	// 유저 상세정보
	public UserVO getUserById(String userId);
	
	public RegisterVO getReqInfoById(String registerId);

//	회원가입 신청거절(삭제)
	public boolean refuseUser(int registerId);
	
//	유저 삭제
	public boolean removeUser(int userId);

	public boolean isUserExist(String id);
}
