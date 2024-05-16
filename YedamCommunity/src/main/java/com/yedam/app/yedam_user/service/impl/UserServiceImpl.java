package com.yedam.app.yedam_user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.yedam_user.mapper.UserMapper;
import com.yedam.app.yedam_user.service.RegisterVO;
import com.yedam.app.yedam_user.service.UserSearchVO;
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

	@Override
	public UserVO loginCheck(UserVO userVO) {
		return userMapper.selectUser(userVO);
	}

//	@Override
//	public UserVO userLogin(UserVO userVO) {
//		return userMapper.userLogin(userVO);
//	}

	@Override
	public List<UserVO> userList(int page) {
		return userMapper.selectAllList(page);
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
	public RegisterVO getUserById(String registerId) {
		return userMapper.getUserById(registerId);
	}

	@Override
	public boolean removeUser(int registerId) {
		return userMapper.removeUser(registerId) == 1;
	}
}
