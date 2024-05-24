package com.yedam.app.yedam_common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.yedam.app.yedam_user.service.UserVO;

public class LoginUserVO implements UserDetails {
	@Autowired
	UserVO userVO;
	
	public LoginUserVO(UserVO userVO) {
		this.userVO = userVO;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auth = new ArrayList<>();
		auth.add(new SimpleGrantedAuthority(userVO.getUserType())); // userType의 값에 따라 권한을 부여한다.
//		auth.add(new SimpleGrantedAuthority(userVO.getProfileImageLocation()));
		
		return auth;
	}

	@Override
	public String getPassword() {
		return userVO.getPassword();
	}

	@Override
	public String getUsername() {
		return userVO.getName();
	}
	
	public String getProfileImageLocation() {
		return userVO.getProfileImageLocation();
	}
	
	public String getUserType() {
		return userVO.getUserType();
	}
	
	public String getUserEmail() {
		return userVO.getEmail();
	}
	
	public String getUserTel() {
		return userVO.getTel();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	

}
