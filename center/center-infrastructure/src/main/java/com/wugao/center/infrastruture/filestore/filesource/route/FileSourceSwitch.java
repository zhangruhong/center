package com.wugao.center.infrastruture.filestore.filesource.route;

public class FileSourceSwitch {

	public static final String FILESOURCE_TEMP = "fsTemp";
	
	public static final String FILESOURCE_CAPTURE = "fsCapture";
	
	private static String defaultFileSource;
	
	private static final ThreadLocal<String> localFileSource = new ThreadLocal<String>();
	
	public static String getDefaultFileSource() {
		return defaultFileSource;
	}
	
	public static void setDefaultFileSource(String fileSource) {
		defaultFileSource = fileSource;
	}
	
	public static void setFileSource(String fileSource) {
		localFileSource.set(fileSource);
	}

	public static String getFileSource() {
		String fileSource = localFileSource.get();
		if (fileSource == null) {
			localFileSource.set(defaultFileSource);
		}
		return localFileSource.get();
	}

	// 如果不调用此清除方法，则上一次的设置在同一线程中始终有效
	public static void clearFileSource() {
		localFileSource.remove();
	}

}
