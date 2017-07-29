package com.wugao.center.infrastruture.filestore.filesource.pool;

import java.io.Serializable;

import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.wugao.center.infrastruture.filestore.connection.Connection;
import com.wugao.center.infrastruture.filestore.filesource.FileSource;

public class PooledFileSource implements FileSource, InitializingBean, Serializable {

	private static final long serialVersionUID = 1L;
	
	private Logger logger = LoggerFactory.getLogger(PooledFileSource.class);

	private GenericObjectPool<Connection> pool;

	// 服务器ip
	private String ip;

	// 端口，默认值21
	private Integer port = 21;

	// 登录用户名
	private String username;

	// 登录密码
	private String password;

	// 编码
	private String controlEncoding = "utf-8";

	// 服务器操作系统类型，默认unix
	private String serverSystemKey = FTPClientConfig.SYST_NT; // FTPClientConfig.SYST_UNIX

	// 服务器操作系统语言, 默认zh
	private String serverLanguageCode = "zh";

	// 允许最大活动对象数
	private Integer maxActive = 10;

	// 允许最大空闲对象数，超过该值的 idle 对象会被destory
	private Integer maxIdle = 5;

	// 最小空闲对象数，如果不够会自动创建来补足
	private Integer minIdle = 1;

	// 允许最大等待时间毫秒数
	private Integer maxWait = 10 * 1000;
	
	
	// 警告日志打印次数
	private int count;

	@Override
	public void afterPropertiesSet() {
		GenericObjectPool.Config config = new GenericObjectPool.Config();
		config.maxActive = maxActive;
		config.maxIdle = maxIdle;
		config.minIdle = minIdle;
		config.maxWait = maxWait;
		// 后进先出
		config.lifo = true;
		// 代表每次检查链接的数量，建议设置和maxActive一样大
		config.numTestsPerEvictionRun = maxActive;
		// 被空闲对象回收器回收(destory)前在池中保持空闲状态的最小时间毫秒数
		config.minEvictableIdleTimeMillis = 60 * 60 * 1000;
		// 指明是否在从池中取出对象前进行检验PoolableObjectFactory.validateObject,如果检验失败,则从池中去除连接并尝试取出另一个.
		config.testOnBorrow = true;
		// 指明是否在归还到池中前进行检验
		config.testOnReturn = false;
		// 指明连接是否被空闲连接回收器(如果有)进行检验.如果检测失败,则连接将被从池中去除. 是否启动检查线程
		config.testWhileIdle = true;
		// 空闲时间超过且空闲数大于minIdle则移除池。如果minEvictableIdleTimeMillis设置为整数则此参数无效。
		config.softMinEvictableIdleTimeMillis = 60 * 60 * 1000;
		// 启动evictor thread的间隔，如果设置为<1不会启动evictor
		// thread。检查期间会阻塞borrow/return因此不宜设太小
		config.timeBetweenEvictionRunsMillis = 30 * 60 * 1000;
		// 当池中对象用完时，阻塞等待空闲的连接，最长等待maxWait
//		config.whenExhaustedAction = GenericObjectPool.WHEN_EXHAUSTED_BLOCK;
		// 当池中对象用完时，活动连接数到达maxActive是抛出NoSuchElementException异常，否则返回null
		config.whenExhaustedAction = GenericObjectPool.WHEN_EXHAUSTED_FAIL;
		// 构造对象池
		pool = new GenericObjectPool<Connection>(new Buider(ip, port, username, password, controlEncoding, serverSystemKey, serverLanguageCode), config);
	}

	@Override
	public Connection openConnection() {
		try {
			return pool.borrowObject();
		} catch (Exception e) {
			if (count < 100) {
				count ++;
			} else {
				count = 0;
				logger.warn(count + " 获取ftp连接异常，没有空闲的ftp连接。原因：连接池过小或者ftp服务器掉线，请检查！");
			}
			return null;
		}
	}

	@Override
	public void closeConnection(Connection connection) {
		try {
			pool.returnObject(connection);
		} catch (Exception e) {
			logger.error("归还连接到连接池异常", e);
		}
	}

	@Override
	public void close() {
		if (!pool.isClosed()) {
			try {
				pool.close();
			} catch (Exception e) {
				logger.error("关闭连接池异常", e);
			}
		}
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setControlEncoding(String controlEncoding) {
		this.controlEncoding = controlEncoding;
	}

	public void setServerSystemKey(String serverSystemKey) {
		this.serverSystemKey = serverSystemKey;
	}

	public void setServerLanguageCode(String serverLanguageCode) {
		this.serverLanguageCode = serverLanguageCode;
	}

	public void setMaxActive(Integer maxActive) {
		this.maxActive = maxActive;
	}

	public void setMaxIdle(Integer maxIdle) {
		this.maxIdle = maxIdle;
	}

	public void setMinIdle(Integer minIdle) {
		this.minIdle = minIdle;
	}

	public void setMaxWait(Integer maxWait) {
		this.maxWait = maxWait;
	}

}
