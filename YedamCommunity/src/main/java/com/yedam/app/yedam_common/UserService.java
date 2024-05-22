package com.yedam.app.yedam_common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yedam.app.yedam_user.mapper.UserMapper;
import com.yedam.app.yedam_user.service.UserVO;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVO userVO = userMapper.getByUserId(username);
		
		if (userVO == null) {
			throw new UsernameNotFoundException("No User Found");
		}
		return new LoginUserVO(userVO);
	}
}
