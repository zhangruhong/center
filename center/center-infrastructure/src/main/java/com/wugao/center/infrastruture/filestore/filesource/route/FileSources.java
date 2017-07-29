package com.wugao.center.infrastruture.filestore.filesource.route;

import org.springframework.beans.factory.InitializingBean;

public class FileSources extends RoutingFileSource implements InitializingBean {

	@Override
	protected String determineCurrentLookupKey() {
		return FileSourceSwitch.getFileSource();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		FileSourceSwitch.setDefaultFileSource(getDefaultFileSourceKey());
	}

}
