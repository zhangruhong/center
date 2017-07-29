package com.wugao.center.infrastruture.filestore.session;

public interface SessionFactory {

	Session openSession();

	void closeSession(Session session);

	Session getCurrentSession();

	void close();

}
