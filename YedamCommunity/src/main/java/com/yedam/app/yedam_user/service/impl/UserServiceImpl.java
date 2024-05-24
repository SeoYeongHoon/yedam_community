package com.yedam.app.yedam_user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	// 회원가입 프로세스
	@Override
	public void registerUser(UserVO userVO) {
		UserVO tempUser = userMapper.checkTempUser(userVO);
        if (tempUser != null) {
        	String encodedPw = passwordEncoder.encode(userVO.getPassword());
    		userVO.setPassword(encodedPw);
            // tempusers 테이블에 일치하는 사용자가 있을 경우 users 테이블에 삽입
            userMapper.insertUser(userVO);
        } else {
        	String encodedPw = passwordEncoder.encode(userVO.getPassword());
    		userVO.setPassword(encodedPw);
            // 정보가 일치하지 않을 경우 register 테이블에 삽입
            userMapper.insertRegister(userVO);
        }
	}

	// 회원가입 수동 승인
	@Override
	public int approveUser(int id) {
		return userMapper.approveUser(id);
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
	public int userTotalCnt(String filter, String searchQuery) {
		return userMapper.getTotalCnt(filter, searchQuery);
	}

	@Override
    public List<UserVO> getUsersByFilter(String filter, int page, String searchQuery) {
        Map<String, Object> params = new HashMap<>();
        params.put("filter", filter);
        params.put("page", page);
        params.put("searchQuery", searchQuery);
        return userMapper.getUsersByFilter(params);
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

	@Override
	public void insertTempUsers(Map<String, String> row) {
		userMapper.insertTempUsers(row);
	}
	
}
