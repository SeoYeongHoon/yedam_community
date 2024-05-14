package com.yedam.app.yedam_user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yedam.app.yedam_user.service.RegisterVO;
import com.yedam.app.yedam_user.service.UserVO;

@Mapper
public interface UserMapper {

	// 회원가입 신청
	public int requestUser(RegisterVO registerVO);
	// 회원가입 승인
	public int insertUser(int registerId);
	
	// 로그인
//	public UserVO userLogin(UserVO userVO);
	UserVO selectUser(UserVO userVO);
	
	// 수강생, 수료생 리스트 전체
	public List<UserVO> selectStdList();
	
	// 어드민페이지 유저 리스트 전체(페이징)
	public List<UserVO> selectAllList(int page);
	
	// 페이지네이션 카운트
	int getTotalCnt();

	public List<UserVO> getUsersByFilter(@Param("filter") String filter);
	
	@Select("SELECT * FROM register WHERE register_id = #{registerId}")
    RegisterVO getUserById(@Param("registerId") String registerId);
	
	public int removeUser(int registerId);
	
}
