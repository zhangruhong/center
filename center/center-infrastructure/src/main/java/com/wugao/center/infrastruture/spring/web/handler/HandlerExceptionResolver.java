package com.wugao.center.infrastruture.spring.web.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ModelAndView;


public class HandlerExceptionResolver implements org.springframework.web.servlet.HandlerExceptionResolver, Ordered {

	private static final Log log = LogFactory.getLog(HandlerExceptionResolver.class);
	
	@Override
	public int getOrder() {
		return Ordered.HIGHEST_PRECEDENCE;
	}
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
		// 此方法不能返回null，如果返回了null，则Spring会继续寻找其他的HandlerExceptionResolver，直到返回了一个ModelAndView对象。
		return new ModelAndView();
	}
	
}
