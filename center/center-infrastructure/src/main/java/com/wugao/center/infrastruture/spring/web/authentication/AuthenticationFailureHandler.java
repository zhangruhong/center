package com.wugao.center.infrastruture.spring.web.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthenticationFailureHandler implements org.springframework.security.web.authentication.AuthenticationFailureHandler {

	private Logger logger = LoggerFactory.getLogger(AuthenticationFailureHandler.class);

	@Override
	public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException exception) throws IOException, ServletException {
		String msg = "";
		if (exception instanceof BadCredentialsException) {
			msg = "帐号或密码错误";
		} else if (exception instanceof UsernameNotFoundException) {
			msg = "帐号不存在";
		} else if (exception instanceof LockedException) {
			msg = "帐号已锁定";
		} else if (exception instanceof DisabledException) {
			msg = "帐号已禁用";
		} else if (exception instanceof CredentialsExpiredException) {
			msg = "证书已过期";
		} else if (exception instanceof AccountExpiredException) {
			msg = "帐号已过期";
		} else if (exception instanceof AuthenticationServiceException) {
			msg = "认证服务异常";
			logger.error(msg, exception);
		}  else {
			msg = "认证异常，未知错误";
			logger.error(msg, exception);
		}
		resp.getWriter().println(msg);
	}

}
