package com.yedam.app.yedam_common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// 커스텀 로그인 실패 핸들러
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

//	@Override
//	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
//			AuthenticationException exception) throws IOException, ServletException {
//		request.setAttribute("errorMessage", "아이디나 비밀번호를 확인하세요.");
//		request.getRequestDispatcher("/all/login?error=true").forward(request, response);
//	}
	
	// 로그인 실패 시 401 상태 코드를 반환
	@Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

}
