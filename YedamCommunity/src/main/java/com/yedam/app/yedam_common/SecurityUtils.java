package com.yedam.app.yedam_common;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
	public static boolean hasRole(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }
        return authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(role));
    }

    public static LoginUserVO getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof LoginUserVO) {
            return (LoginUserVO) authentication.getPrincipal();
        }
        return null;
    }

    public static String getCurrentUserId() {
        LoginUserVO currentUser = getCurrentUser();
        return currentUser != null ? currentUser.getUserId() : null;
    }
}
