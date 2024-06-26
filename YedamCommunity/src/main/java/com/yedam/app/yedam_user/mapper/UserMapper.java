package com.yedam.app.yedam_user.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yedam.app.yedam_curriculum.service.CurriculumVO;
import com.yedam.app.yedam_homework.service.HomeWorkVO;
import com.yedam.app.yedam_user.service.RegisterVO;
import com.yedam.app.yedam_user.service.UserVO;

@Mapper
public interface UserMapper {
	// CSV 학생 데이터 tempusers 테이블에 저장
	public void insertTempUsers(Map<String, String> row);

	// 회원가입 정보 비교를 위한 조회
	UserVO checkTempUser(UserVO userVO);
	
	// 회원가입 승인
	public void insertUser(UserVO userVO);
	
	// 회원가입 수동 승인
	public int approveUser(int id);
	
	// 회원가입 보류 -> 요청 테이블에 저장
	public void insertRegister(UserVO userVO);

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
	int getTotalCnt(String filter, String searchQuery);

	// 유저리스트 페이징 및 필터링
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
	
	// 회원정보 수정
	public void updateUserInfo(UserVO userVO);
	
	// 비밀번호 초기화용 메일 전송 
	@Select("SELECT * FROM users WHERE id = #{id} AND name = #{name} AND email = #{email}")
	Optional<UserVO> findPassword(String id, String name, String email);
	
	// 비밀번호 초기화 후 변경
	@Update("UPDATE users SET reset_token = #{resetToken} where user_id = #{userId}")
	public void updateResetToken(int userId, String resetToken);
	
	// 초기화용 Token 찾기
	@Select("SELECT * FROM users WHERE reset_token = #{resetToken}")
	public Optional<UserVO> findByResetToken(@Param("resetToken") String resetToken);
	
	// 비밀번호 초기화
	@Update("UPDATE users SET password = #{password}, reset_token = NULL WHERE id = #{id}")
	public void updatePassword(@Param("id") String id, @Param("password") String password);

	// 회원가입 시 이메일 인증 확인
	
	// 수강생 => 수료생 자동 변경
	public void updateUserType();
}
