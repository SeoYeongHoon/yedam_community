package com.yedam.app.yedam_user.service;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncoder {
	
	 // 비밀번호를 암호화하여 반환
    public String encodePassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // 암호화된 비밀번호와 사용자가 입력한 비밀번호를 비교하여 일치하는지 확인
    public boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
