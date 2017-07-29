package com.wugao.center.infrastruture.utils;

import javax.activation.MimetypesFileTypeMap;

public class ContentTypeUtil {

	private static MimetypesFileTypeMap mimeTypesMap;

	public static String getContentType(String filename) {
		if (mimeTypesMap == null) {
			mimeTypesMap = new MimetypesFileTypeMap(ContentTypeUtil.class.getResourceAsStream("mime.types"));
		}
		return mimeTypesMap.getContentType(filename);
	}

}
