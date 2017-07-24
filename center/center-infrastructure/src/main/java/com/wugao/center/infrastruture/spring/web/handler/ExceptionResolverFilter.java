package com.wugao.center.infrastruture.spring.web.handler;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 统一异常捕获过滤器，放在所有security的filter之前，捕获并转换发生在filter链的异常（不包括servlet的异常与认证异常）
 * 1.认证异常已由AuthenticationFailureHandler处理
 * （包括无法打开数据库连接也被security转换为了AuthenticationException）
 * 2.sevlert抛出的异常已由HandlerMethodExceptionResolver处理了。
 */
public class ExceptionResolverFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(ExceptionResolverFilter.class);

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		try {
			chain.doFilter(req, resp);
		} catch (Throwable throwable) {
			logger.error("安全控制发生异常", throwable);
			HttpServletResponse response = (HttpServletResponse) resp;
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

}
