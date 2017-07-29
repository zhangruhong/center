package com.wugao.center.infrastruture.filestore.session;

public interface SessionManager {

	void openSessionIfNecessary();
	
	void closeSessionIfNecessary();
	
}
