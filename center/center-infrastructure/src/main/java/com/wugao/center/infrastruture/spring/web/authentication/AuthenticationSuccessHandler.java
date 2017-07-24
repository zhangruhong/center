package com.wugao.center.infrastruture.spring.web.authentication;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

public class AuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {
	
	private static final String SUCCESSMESSAGE = "authentication success";

	@SuppressWarnings("unchecked")
	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException {
		Object principal = authentication.getPrincipal();
		// 返回认证成功消息
		resp.getWriter().println(SUCCESSMESSAGE);
	}

}
