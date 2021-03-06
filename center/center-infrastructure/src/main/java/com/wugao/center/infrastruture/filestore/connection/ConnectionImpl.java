package com.wugao.center.infrastruture.filestore.connection;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wugao.center.infrastruture.filestore.FileException;
import com.wugao.center.infrastruture.filestore.FileInfo;

public class ConnectionImpl implements Connection {

	private Logger logger = LoggerFactory.getLogger(ConnectionImpl.class);

	private FTPClient ftpClient = new FTPClient();

	public ConnectionImpl(String ip, Integer port, String username, String password, String controlEncoding, String systemKey, String serverLanguageCode) throws FileException {
		FTPClientConfig conf = new FTPClientConfig(systemKey);
		conf.setServerLanguageCode(serverLanguageCode);
		ftpClient.configure(conf);
		try {
			int reply;
			// 连接
			ftpClient.connect(ip, port);
			reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				throw new FileException("连接失败，reply=" + reply);
			}
			// 登录
			if (ftpClient.login(username, password)) {
				reply = ftpClient.getReplyCode();
				if (!FTPReply.isPositiveCompletion(reply)) {
					throw new FileException("登录失败，reply=" + reply);
				} else {
					ftpClient.setControlKeepAliveTimeout(1 * 60);
					ftpClient.setControlEncoding(controlEncoding);
					ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
					ftpClient.enterLocalPassiveMode();
				}
			} else {
				throw new FileException("登录失败，ftpClient.login(username, password) 返回 false");
			}
		} catch (Exception e) {
			close();
			throw new FileException("连接FTP服务器异常", e);
		}
	}

	@Override
	public void close() {
		try {
			ftpClient.logout();
		} catch (IOException e) {
		}
		try {
			ftpClient.disconnect();
		} catch (IOException e) {
		}
	}

	@Override
	public boolean isValid() {
		try {
			return ftpClient.isConnected() && ftpClient.sendNoOp();
		} catch (IOException e) {
			return false;
		}
	}

	@Override
	public FileInfo getFileInfo(String fileName) {
		try {
			FTPFile ftpFile = ftpClient.mlistFile(encode(fileName));
			return ftpFile == null ? null : new FileInfo(getName(decode(ftpFile.getName())), (int) ftpFile.getSize(), ftpFile.getTimestamp().getTime(), ftpFile.isDirectory());
		} catch (IOException e) {
			throw new FileException("获取文件信息异常", e);
		}
	}

	// 只返回path下的目录信息，不包括文件
	@Override
	public List<FileInfo> getFileInfos(String directoryPath) {
		try {
			List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
			for (FTPFile f : ftpClient.mlistDir(encode(directoryPath))) {
				fileInfoList.add(new FileInfo(getName(f.getName()), (int) f.getSize(), f.getTimestamp().getTime(), f.isDirectory()));
			}
			return fileInfoList;
		} catch (IOException e) {
			throw new FileException("获取文件列表异常", e);
		}
	}

	@Override
	public void uploadFile(String fileName, InputStream inputStream) {
		try {
			String dir = getDir(fileName);
			String name = getName(fileName);
			// 创建目录，如果目录已存在会返回false
			ftpClient.makeDirectory(encode(dir));
			if (!ftpClient.changeWorkingDirectory(encode(dir))) {
				throw new FileException("上传文件" + fileName + "异常, changeWorkingDirectory()返回false");
			}
			// 上传文件
			if (!ftpClient.storeFile(encode(name), new BufferedInputStream(inputStream))) {
				throw new FileException("上传文件" + fileName + "异常, storeFile()返回false");
			}
		} catch (IOException e) {
			throw new FileException("上传文件异常", e);
		}
	}

	@Override
	public void appendFile(String fileName, InputStream inputStream) {
		try {
			String dir = getDir(fileName);
			String name = getName(fileName);
			if (!ftpClient.changeWorkingDirectory(encode(dir))) {
				throw new FileException("续传文件" + fileName + "异常, changeWorkingDirectory()返回false");
			}
			// 上传文件
			if (!ftpClient.appendFile(encode(name), new BufferedInputStream(inputStream))) {
				throw new FileException("续传文件" + fileName + "异常, appendFile()返回false");
			}
		} catch (IOException e) {
			throw new FileException("续传文件异常", e);
		}
	}

	@Override
	public void downloadFile(String fileName, OutputStream outputStream) {
		try {
			ftpClient.retrieveFile(encode(fileName), outputStream);

			// String dir = getDir(fileName);
			// String name = getName(fileName);
			// // 获取文件信息
			// FileInfo info = getFileInfo(fileName);
			// if (info == null) {
			// throw new FileException("下载文件" + fileName +
			// "异常，获取文件信息失败，getFileInfo()返回null");
			// }
			// // 跳转目录
			// if (!ftpClient.changeWorkingDirectory(encode(dir))) {
			// throw new FileException("下载文件" + fileName +
			// "异常，所在目录不存在，changeWorkingDirectory()返回false");
			// }
			// // 下载文件
			// BufferedOutputStream bos = new
			// BufferedOutputStream(outputStream);
			// if (!ftpClient.retrieveFile(encode(name), bos)) {
			// throw new FileException("下载文件" + fileName +
			// "异常，获取输入流异常，retrieveFile()返回false");
			// } else {
			// bos.flush();
			// }
		} catch (IOException e) {
			throw new FileException("文件下载异常", e);
		}
	}

	@Override
	public void deleteFile(String fileName) {
		try {
			String dir = getDir(fileName);
			String name = getName(fileName);
			// 跳转目录
			if (!ftpClient.changeWorkingDirectory(encode(dir))) {
				throw new FileException("删除文件" + fileName + "异常，changeWorkingDirectory()返回false");
			}
			// 删除文件
			if (!ftpClient.deleteFile(encode(name))) {
				throw new FileException("删除文件" + fileName + "异常，deleteFile()返回false");
			}
		} catch (IOException e) {
			throw new FileException("删除文件异常", e);
		}
	}

	@Override
	public void deleteDir(String path) {
		try {
			// 必须以"/"结束，否则getDir(path)会取到path的上级
			if (!path.endsWith("/")) {
				path = path + "/";
			}
			// 要删除的目录
			String dir = getDir(path);
			// 跳转到要删除的目录
			if (!ftpClient.changeWorkingDirectory(encode(dir))) {
				return;
			}
			// 判断目录是否为空
			FTPFile[] files = ftpClient.listFiles();
			if (files.length == 0) {
				files = ftpClient.mlistDir();
			}
			for (int i = 0; i < files.length; i++) {
				FTPFile f = files[i];
				if (f.isFile()) {
					deleteFile(dir + f.getName());
				} else if (f.isDirectory()) {
					deleteDir(dir + f.getName());
				}
			}
			// 删除目录
			if (!ftpClient.removeDirectory(encode(dir))) {
				logger.error("删除目录" + dir + "异常，removeDirectory()返回false");
			}
		} catch (IOException e) {
			throw new FileException("删除目录异常", e);
		}
	}

	@Override
	public void makeDir(String path) {
		try {
			// 必须以"/"结束，否则getDir(path)会取到path的上级
			if (!path.endsWith("/")) {
				path = path + "/";
			}
			String dir = getDir(path);
			// 创建目录，如果目录已存在会返回false
			ftpClient.makeDirectory(encode(dir));
		} catch (IOException e) {
			throw new FileException("创建目录异常", e);
		}
	}

	private String getDir(String fileName) {
		return "/" + FilenameUtils.getPath(fileName);
	}

	private String getName(String fileName) {
		return FilenameUtils.getName(fileName);
	}

	// ftp服务端使用的编码为ISO-8859-1，发送命令到服务端必须将参数转换为此编码
	private String encode(String str) {
		try {
			return new String(str.getBytes(), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			logger.error("转换编码异常", e);
			return str;
		}
	}

	private String decode(String str) {
		try {
			return new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("转换编码异常", e);
			return str;
		}
	}

	@Override
	public OutputStream getStoreFileStream(String fileName) {
		if (StringUtils.isEmpty(fileName)) {
			return null;
		}
		try {
			String dir = getDir(fileName);
			ftpClient.makeDirectory(encode(dir));
			if (!ftpClient.changeWorkingDirectory(encode(dir))) {
				throw new FileException("上传文件" + fileName + "异常, changeWorkingDirectory()返回false");
			}
			return ftpClient.storeFileStream(fileName);
		} catch (IOException e) {
			throw new FileException("上传文件异常", e);
		}
	}

}
