package com.yedam.app.yedam_user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yedam.app.yedam_user.mapper.UserMapper;
import com.yedam.app.yedam_user.service.RegisterVO;
import com.yedam.app.yedam_user.service.UserService;
import com.yedam.app.yedam_user.service.UserVO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	// 회원가입 신청
	@Override
	public void requestUser(RegisterVO registerVO) throws Exception {
		String encodedPw = passwordEncoder.encode(registerVO.getPassword());
		registerVO.setPassword(encodedPw);
		
		userMapper.requestUser(registerVO);
	}

	// 회원가입 승인
	@Override
	public int insertUser(int registerId) {
		if (userMapper.insertUser(registerId) > 0) {
			return 1;
		} else {
			return -1;
		}
	}

	// 체크된 다수의 회원가입 요청 승인
	@Override
	public void insertCheckedUsers(List<String> registerIds) {
		// 화면단에서 체크 여부 확인
		userMapper.insertCheckedUsers(registerIds);
	}

	@Override
	public UserVO loginCheck(UserVO userVO) {
		return userMapper.userLogin(userVO);
	}

	@Override
	public int userTotalCnt() {
		return userMapper.getTotalCnt();
	}

	@Override
	public List<UserVO> getUsersByFilter(String filter) {
		return userMapper.getUsersByFilter(filter);
	}

	@Override
	public List<UserVO> stdList() {
		return userMapper.selectStdList();
	}

	@Override
	public RegisterVO getReqById(String registerId) {
		return userMapper.getReqById(registerId);
	}

	@Override
	public UserVO getUserById(String userId) {
		return userMapper.getUserById(userId);
	}

//	회원가입 신청 거절(삭제)
	@Override
	public boolean refuseUser(int registerId) {
		return userMapper.refuseUser(registerId) == 1;
	}

//	유저 삭제
	@Override
	public boolean removeUser(int userId) {
		return userMapper.removeUser(userId) == 1;
	}

	@Override
	public boolean isUserExist(String id) {
		RegisterVO registerVO = userMapper.getReqById(id);
		return registerVO != null;
	}

	// 아이디 찾기
	@Override
	public UserVO findUserId(UserVO userVO) {
		return userMapper.getUserId(userVO);
	}

	// 비밀번호 찾기
	@Override
	public UserVO findUserPw(UserVO userVO) {
		return userMapper.getUserPw(userVO);
	}

	
	@Override
	public RegisterVO getReqInfoById(String registerId) {
		return userMapper.getReqInfoById(registerId);
	}
	
}
