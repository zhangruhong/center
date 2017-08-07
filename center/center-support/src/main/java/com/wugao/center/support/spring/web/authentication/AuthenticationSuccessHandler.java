package com.wugao.center.support.spring.web.authentication;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import com.wugao.center.domain.user.User;
import com.wugao.center.domain.user.UserRepo;

public class AuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {
	
	private static final String SUCCESSMESSAGE = "authentication success";
	
	@Autowired
	UserRepo userRepo;

	@SuppressWarnings("unchecked")
	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException {
		Object principal = authentication.getPrincipal();
		UserDetails userDetails = (UserDetails)principal;
		String username = userDetails.getUsername();
		
		
		// 返回认证成功消息
		resp.getWriter().println(SUCCESSMESSAGE);
	}
	
	
	public void buildContextSession(String username, HttpServletRequest request) {
		User user = userRepo.getByUsername(username);
	}
}
