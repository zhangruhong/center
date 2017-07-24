package com.wugao.center.infrastruture.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

/**
 * 设置 spring.profiles.default，必须配置在
 * org.springframework.web.context.ContextLoaderListener 之前。
 * 
 * @author Administrator
 *
 */
public class DefaultProfileListener implements ServletContextListener {

	private static final Log log = LogFactory.getLog(DefaultProfileListener.class);

	private final static String PROFILE_DEFAULT = "development";
	private final static String PROFILE_DEFAULT_NAME = "spring.profiles.default";

	@Override
	public void contextInitialized(ServletContextEvent e) {
		String profileDefault = System.getProperty(PROFILE_DEFAULT_NAME);
		if (profileDefault == null) {
			log.info("未获取到系统变量 " + PROFILE_DEFAULT_NAME + " ，将采用默认值 " + PROFILE_DEFAULT);
			profileDefault = PROFILE_DEFAULT;
		} else {
			log.info("获取到系统变量 " + PROFILE_DEFAULT_NAME + " = " + profileDefault);
		}
		e.getServletContext().setInitParameter(PROFILE_DEFAULT_NAME, profileDefault);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
