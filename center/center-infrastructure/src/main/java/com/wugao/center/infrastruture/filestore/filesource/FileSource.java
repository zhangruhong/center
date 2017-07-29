package com.wugao.center.infrastruture.filestore.filesource;

import com.wugao.center.infrastruture.filestore.FileException;
import com.wugao.center.infrastruture.filestore.connection.Connection;

public interface FileSource {

	Connection openConnection() throws FileException;

	void closeConnection(Connection connection);
	
	void close();
	
}
