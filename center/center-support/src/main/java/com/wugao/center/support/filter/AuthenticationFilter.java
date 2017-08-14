package com.wugao.center.support.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wugao.center.infrastruture.constant.SessionConstant;
import com.wugao.center.support.spring.session.Context;

public class AuthenticationFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		HttpSession session = httpServletRequest.getSession();
		Context context = (Context)session.getAttribute(SessionConstant.CONTEXT);
		if(context == null) {
			request.getRequestDispatcher("/WEB-INF/view/error/403.jsp").forward(request, response);
		}else {
			chain.doFilter(httpServletRequest, httpServletResponse);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
