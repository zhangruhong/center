package com.wugao.center.infrastruture.filestore.filesource.pool;

import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.pool.PoolableObjectFactory;

import com.wugao.center.infrastruture.filestore.connection.Connection;
import com.wugao.center.infrastruture.filestore.connection.ConnectionImpl;

public class Buider implements PoolableObjectFactory<Connection> {

	// 服务器ip
	private String ip;

	// 端口，默认值21
	private Integer port = 21;

	// 登录用户名
	private String username;

	// 登录密码
	private String password;

	// 编码
	private String controlEncoding = "GBK";

	// 服务器操作系统类型，默认unix
	private String serverSystemKey = FTPClientConfig.SYST_UNIX; // FTPClientConfig.SYST_NT

	// 服务器操作系统语言, 默认zh
	private String serverLanguageCode = "zh";

	public Buider(String ip, Integer port, String username, String password, String controlEncoding, String serverSystemKey, String serverLanguageCode) {
		this.ip = ip;
		this.port = port;
		this.username = username;
		this.password = password;
		this.controlEncoding = controlEncoding;
		this.serverSystemKey = serverSystemKey;
		this.serverLanguageCode = serverLanguageCode;
	}

	@Override
	public Connection makeObject() throws Exception {
		return new ConnectionImpl(ip, port, username, password, controlEncoding, serverSystemKey, serverLanguageCode);
	}

	@Override
	public void destroyObject(Connection connection) {
		connection.close();
	}

	@Override
	public boolean validateObject(Connection connection) {
		return connection.isValid();
	}

	@Override
	public void activateObject(Connection connection) throws Exception {
		// 将对象激活，这里不需要做任何工作
	}

	@Override
	public void passivateObject(Connection connection) throws Exception {
		// 将对象钝化，这里不需要做任何工作
	}

}
