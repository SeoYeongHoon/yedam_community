package com.yedam.app.yedam_user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.yedam_user.mapper.UserMapper;
import com.yedam.app.yedam_user.service.RegisterVO;
import com.yedam.app.yedam_user.service.UserService;
import com.yedam.app.yedam_user.service.UserVO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	@Override
	public int requestUser(RegisterVO registerVO) {
		if (userMapper.requestUser(registerVO) > 0) {
			return 1;
		} else {
			return -1;
		}
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

		if (registerIds == null || registerIds.isEmpty()) {
			throw new IllegalArgumentException("No register IDs provided");
		}

		userMapper.insertCheckedUsers(registerIds);
	}

	@Override
	public UserVO loginCheck(UserVO userVO) {
		return userMapper.userLogin(userVO);
	}

//	@Override
//	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
//		UserVO userVO = userMapper.getUserById(id);
//		
//		if (userVO == null) {
//			throw new UsernameNotFoundException("No User Found");
//		}
//		
//		return new LoginVO(userVO);
//	}

//	@Override
//	public UserVO userLogin(UserVO userVO) {
//		return userMapper.userLogin(userVO);
//	}

	@Override
	public List<UserVO> userList() {
		return userMapper.selectUserList();
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

}
