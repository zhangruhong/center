package com.wugao.center.infrastruture.filestore.session;

import com.wugao.center.infrastruture.filestore.session.context.SessionHolder;

public class SessionManagerImpl implements SessionManager {

	private static final ThreadLocal<Integer> invokingLayer = new ThreadLocal<Integer>();

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void openSessionIfNecessary() {
		if (invokingLayer.get() == null) {
			invokingLayer.set(1);
			SessionHolder.bind(sessionFactory.openSession());
		} else {
			invokingLayer.set(invokingLayer.get() + 1);
		}
	}

	@Override
	public void closeSessionIfNecessary() {
		if (invokingLayer.get() != null) {
			Integer layer = invokingLayer.get() - 1;
			if (layer <= 0) {
				// 到达最外层，释放会话
				sessionFactory.closeSession(SessionHolder.getCurrentSession());
				// 从本地线程移除会话
				SessionHolder.unbind();
				// 清除invokingLayer
				invokingLayer.remove();
			} else {
				invokingLayer.set(layer);
			}
		}
	}

}
