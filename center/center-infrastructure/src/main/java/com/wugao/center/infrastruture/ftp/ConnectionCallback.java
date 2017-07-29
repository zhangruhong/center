package com.wugao.center.infrastruture.ftp;


public interface ConnectionCallback {

	<T> T doInConnection(Connection connection);

}
