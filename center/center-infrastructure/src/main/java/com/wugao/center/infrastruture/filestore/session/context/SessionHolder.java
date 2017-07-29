package com.wugao.center.infrastruture.filestore.session.context;

import com.wugao.center.infrastruture.filestore.session.Session;

public class SessionHolder {

	private static final ThreadLocal<Session> sessionHolder = new ThreadLocal<Session>();

	public static void bind(Session session) {
		sessionHolder.set(session);
	}

	public static void unbind() {
		sessionHolder.remove();
	}

	public static Session getCurrentSession() {
		return sessionHolder.get();
	}

}
