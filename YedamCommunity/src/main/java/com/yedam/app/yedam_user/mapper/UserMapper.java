package com.yedam.app.yedam_user.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yedam.app.yedam_user.service.RegisterVO;
import com.yedam.app.yedam_user.service.UserVO;

@Mapper
public interface UserMapper {

	// 회원가입 신청
	public void requestUser(RegisterVO registerVO);

	// 회원가입 승인
	public int insertUser(int registerId);

	// 체크된 다수의 회원가입 요청 승인
	public void insertCheckedUsers(@Param("registerIds") List<String> registerIds);

	// 로그인
	public UserVO userLogin(UserVO userVO);
//	UserVO selectUser(String id);
	
	// Spring Security용 메소드
	UserVO getByUserId(String id);

	// (회원가입 신청한) 수강생, 수료생 리스트 전체
	public List<UserVO> selectStdList();

	// 페이지네이션 카운트
	int getTotalCnt(String filter);

	public List<UserVO> getUsersByFilter(Map<String, Object> params);

	// 회원 상세 정보
	@Select("SELECT * FROM users WHERE user_id = #{userId}")
	UserVO getUserById(@Param("userId") String userId);

	// 회원가입 신청한 유저 상세 정보
	@Select("SELECT * FROM register WHERE id = #{id}")
	RegisterVO getReqById(@Param("id") String registerId);
	
	@Select("SELECT * FROM register WHERE register_id = #{registerId}")
	RegisterVO getReqInfoById(@Param("registerId") String registerId);
	
	// 아이디 찾기
	UserVO getUserId(UserVO userVO);
	
	// 비밀번호 찾기
	UserVO getUserPw(UserVO userVO);
	

//	회원가입 신청거절(삭제)
	public int refuseUser(int registerId);

//	유저 삭제
	public int removeUser(int userId);

}
