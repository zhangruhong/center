package com.wugao.center.infrastruture.filestore.spring;


import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import com.wugao.center.infrastruture.filestore.filesource.FileSource;
import com.wugao.center.infrastruture.filestore.session.SessionFactory;
import com.wugao.center.infrastruture.filestore.session.SessionFactoryImpl;

public class SessionFactoryBean implements FactoryBean<SessionFactory>, InitializingBean, DisposableBean {

	private FileSource fileSource;

	private SessionFactory sessionFactory;

	public void setFileSource(FileSource fileSource) {
		this.fileSource = fileSource;
	}

	@Override
	public SessionFactory getObject() throws Exception {
		return this.sessionFactory;
	}

	@Override
	public Class<?> getObjectType() {
		return SessionFactory.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.sessionFactory = new SessionFactoryImpl(fileSource);
	}

	@Override
	public void destroy() throws Exception {
		sessionFactory.close();
	}

}
