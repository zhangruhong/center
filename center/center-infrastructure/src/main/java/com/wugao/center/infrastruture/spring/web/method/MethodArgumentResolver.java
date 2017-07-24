package com.wugao.center.infrastruture.spring.web.method;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class MethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		return null;
	}

	@Override
	public boolean supportsParameter(MethodParameter methodParameter) {
		return false;
	}

}
