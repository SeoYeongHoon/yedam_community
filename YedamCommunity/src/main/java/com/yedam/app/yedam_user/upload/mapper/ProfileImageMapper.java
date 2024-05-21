package com.yedam.app.yedam_user.upload.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.yedam.app.yedam_user.service.UserVO;
import com.yedam.app.yedam_user.upload.service.ProfileImageVO;

@Mapper
public interface ProfileImageMapper {

	// 프로필 사진 저장
	public int insertProfileImage(ProfileImageVO profileImageVO);
	
    ProfileImageVO getProfileImageByLogId(String id);
}
