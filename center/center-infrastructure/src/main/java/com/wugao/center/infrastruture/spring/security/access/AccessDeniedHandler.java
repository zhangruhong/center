package com.wugao.center.infrastruture.spring.security.access;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;

public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException, ServletException {
		if (!response.isCommitted()) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
		}
	}

}
