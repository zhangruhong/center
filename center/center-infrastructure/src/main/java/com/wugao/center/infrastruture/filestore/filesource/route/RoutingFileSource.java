package com.wugao.center.infrastruture.filestore.filesource.route;


import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.wugao.center.infrastruture.filestore.connection.Connection;
import com.wugao.center.infrastruture.filestore.filesource.FileSource;

public abstract class RoutingFileSource implements FileSource {

	private Map<String, FileSource> targetFileSources;

	private FileSource defaultTargetFileSource;

	public void setTargetFileSources(Map<String, FileSource> targetFileSources) {
		this.targetFileSources = targetFileSources;
	}

	public void setDefaultTargetFileSource(FileSource defaultTargetFileSource) {
		this.defaultTargetFileSource = defaultTargetFileSource;
	}

	@Override
	public Connection openConnection() {
		return determineTargetFileSource().openConnection();
	}
	
	@Override
	public void closeConnection(Connection connection) {
		determineTargetFileSource().closeConnection(connection);
	}
	
	@Override
	public void close() {
		defaultTargetFileSource.close();
		for (FileSource fileSource : targetFileSources.values()) {
			fileSource.close();
		}
	}

	public FileSource determineTargetFileSource() {
		String lookupKey = determineCurrentLookupKey();
		FileSource fileSource = this.targetFileSources.get(lookupKey);
		if (fileSource == null) {
			throw new IllegalStateException("Cannot determine target FileSource for lookup key [" + lookupKey + "]");
		}
		return fileSource;
	}
	
	protected String getDefaultFileSourceKey() {
		Iterator<Entry<String, FileSource>> it = targetFileSources.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, FileSource> entry = it.next();
			if (entry.getValue().equals(defaultTargetFileSource)) {
				return entry.getKey();
			}
		}
		return null;
	}

	protected abstract String determineCurrentLookupKey();

}
