package com.yedam.app.yedam_user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.yedam_user.mapper.UserMapper;
import com.yedam.app.yedam_user.service.UserService;
import com.yedam.app.yedam_user.service.UserVO;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserMapper userMapper;

	@Override
	public int insertUser(UserVO userVO) {
		if (userMapper.insertUser(userVO) > 0) {
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

	
}
