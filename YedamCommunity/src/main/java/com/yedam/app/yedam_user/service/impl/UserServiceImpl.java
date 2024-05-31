package com.yedam.app.yedam_user.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yedam.app.yedam_common.LoginUserVO;
import com.yedam.app.yedam_curriculum.service.CurriculumVO;
import com.yedam.app.yedam_homework.service.HomeWorkVO;
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
	
	@Autowired
	JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String emailFrom;
	
	private final Map<String, String> verificationCodes = new HashMap<>();
	
	
	
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

	@Override
	public void updateUserInfo(UserVO userVO) {
		userMapper.updateUserInfo(userVO);
		
		UserVO updatedUserVO = userMapper.getByUserId(userVO.getId());
	    
	    // 새로운 인증 토큰을 생성
	    LoginUserVO updatedLoginUser = new LoginUserVO(updatedUserVO);
	    UsernamePasswordAuthenticationToken newAuthentication = new UsernamePasswordAuthenticationToken(updatedLoginUser, null, updatedLoginUser.getAuthorities());
	    
	    // SecurityContextHolder를 사용하여 인증 정보를 업데이트
	    SecurityContextHolder.getContext().setAuthentication(newAuthentication);
	}

	@Override
	public UserVO getByUserId(String userId) {
		return userMapper.getByUserId(userId);
	}
	
	// 비밀번호 초기화용 메일 전송 
	@Override
	public void sendPasswordResetEmail(String id, String name, String email) {
		Optional<UserVO> userOptional = userMapper.findPassword(id, name, email);
		
		if (userOptional.isPresent()) {
			UserVO userVO = userOptional.get();
			String resetToken = UUID.randomUUID().toString();
			userMapper.updateResetToken(userVO.getUserId(), resetToken);
			
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(userVO.getEmail());
			mailMessage.setSubject("패스워드 초기화 요청");
			mailMessage.setText("패스워드 초기화를 위해, 링크를 클릭해주세요: \n" + " http://localhost:8080/all/resetPw?token=" + resetToken);
			mailMessage.setFrom(emailFrom);
			
			javaMailSender.send(mailMessage);
		} else {
			throw new RuntimeException("회원님의 이메일: " + email + " 을 찾을 수 없습니다.");
		}
	}

	@Override
	public Optional<UserVO> findByResetToken(String resetToken) {
		return userMapper.findByResetToken(resetToken);
	}

	@Override
	public void updatePassword(String token, String newPassword) {
		Optional<UserVO> userOptional = userMapper.findByResetToken(token);
		if (userOptional.isPresent()) {
			String encodedPassword = passwordEncoder.encode(newPassword);
			userMapper.updatePassword(userOptional.get().getId(), encodedPassword);
		} else {
			throw new RuntimeException("유효하지 않은 토큰입니다.");
		}
	}

	// 회원가입 시 이메일 인증 코드 전송
	@Override
	public void sendVerificationEmail(String email) {
		String verificationCode = generateVerificationCode();
		verificationCodes.put(email, verificationCode);
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(email);
		mailMessage.setSubject("이메일 인증");
		mailMessage.setText("인증코드를 입력해주세요: " + verificationCode);
		mailMessage.setFrom(emailFrom);
		
		javaMailSender.send(mailMessage);
	}
	
	// 인증코드 확인
	@Override
	public boolean verifyCode(String email, String code) {
		System.out.println("verification codes: " + verificationCodes);
		String storedCode = verificationCodes.get(email);
		System.out.println("저장된 인증코드: " + storedCode);
		
		return storedCode != null && storedCode.equals(code);
	}
	
	// 코드 생성
	private String generateVerificationCode() {
		Random random = new Random();
		int verificationCode = 1000 + random.nextInt(9000); // 1000부터 9999 사이의 숫자 생성
		return String.valueOf(verificationCode);
	}

	// 수강생 => 수료생 자동 변경
	@Override
	public void updateUserType() {
		userMapper.updateUserType();
	}
}
