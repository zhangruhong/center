package com.wugao.center.infrastruture.filestore.spring;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.wugao.center.infrastruture.filestore.session.SessionManager;

public class SessionInterceptor implements MethodInterceptor {

	private SessionManager sessionManager;

	public void setSessionManager(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		sessionManager.openSessionIfNecessary();
		try {
			return invocation.proceed();
		} finally {
			sessionManager.closeSessionIfNecessary();
		}
	}

}
