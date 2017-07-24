package com.wugao.center.infrastruture.spring.web.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;

public class LoginUrlEntryPoint implements org.springframework.security.web.AuthenticationEntryPoint {

	private String loginUrl = "/login";
	
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		request.getRequestDispatcher(loginUrl).forward(request, response);
	}

}
