package com.wugao.center.infrastruture.filestore.repository;


import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.wugao.center.infrastruture.filestore.FileInfo;
import com.wugao.center.infrastruture.filestore.session.SessionFactory;
import com.wugao.center.infrastruture.ftp.FtpTemplate;

public class FileRepositoryImpl implements FileRepository {

	private SessionFactory fileSessionFactory;
	
	@Resource
	HttpServletRequest request;
	
	@Resource
	FtpTemplate ftpTemplate;

	public void setFileSessionFactory(SessionFactory fileSessionFactory) {
		this.fileSessionFactory = fileSessionFactory;
	}

	public FileInfo getFileInfo(String fileName) {
		return ftpTemplate.getFileInfo(fileName);
//		return fileSessionFactory.getCurrentSession().getFileInfo(fileName);
	}

	public List<FileInfo> getFileInfos(String directoryPath) {
		return ftpTemplate.getFileInfos(directoryPath);
//		return fileSessionFactory.getCurrentSession().getFileInfos(directoryPath);
	}

	public void getFile(String fileName, OutputStream outputStream) {
		ftpTemplate.getFile(fileName, outputStream);
//		fileSessionFactory.getCurrentSession().downloadFile(fileName, outputStream);
	}
	
	public void saveFile(String fileName, InputStream inputStream) {
		ftpTemplate.saveFile(fileName, inputStream);
//		ImageCompressUtil.saveMinPhoto(fileName, fileName, 1000, 1, true);
//		fileSessionFactory.getCurrentSession().uploadFile(fileName, inputStream);
	}

	public void appendFile(String fileName, InputStream inputStream) {
		ftpTemplate.appendFile(fileName, inputStream);
//		fileSessionFactory.getCurrentSession().appendFile(fileName, inputStream);
	}

	public void removeFile(String fileName) {
		ftpTemplate.removeFile(fileName);
//		fileSessionFactory.getCurrentSession().deleteFile(fileName);
	}

	public void removeDir(String path) {
		ftpTemplate.removeDir(path);
//		fileSessionFactory.getCurrentSession().deleteDir(path);
	}

	public void makeDir(String path) {
		ftpTemplate.makeDir(path);
//		fileSessionFactory.getCurrentSession().makeDir(path);
	}

	public OutputStream getStoreFileStream(String fileName) {
		return fileSessionFactory.getCurrentSession().getStoreFileStream(fileName);
	}
}
