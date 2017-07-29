package com.wugao.center.infrastruture.filestore.session;

import com.wugao.center.infrastruture.filestore.connection.Connection;
import com.wugao.center.infrastruture.filestore.filesource.FileSource;
import com.wugao.center.infrastruture.filestore.session.context.SessionHolder;

public class SessionFactoryImpl implements SessionFactory {

	private FileSource fileSource;

	public SessionFactoryImpl(FileSource fileSource) {
		this.fileSource = fileSource;
	}

	@Override
	public Session openSession() {
		return fileSource.openConnection();
	}

	@Override
	public void closeSession(Session session) {
		fileSource.closeConnection((Connection) session);
	}

	@Override
	public Session getCurrentSession() {
		return SessionHolder.getCurrentSession();
	}

	@Override
	public void close() {
		fileSource.close();
	}

}
