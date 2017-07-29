package com.wugao.center.infrastruture.validation;

import java.util.Map;

import com.wugao.center.infrastruture.exception.AppException;

public class ValidateException extends AppException {

	private static final long serialVersionUID = 1L;

	private Map<String, String> errors;

	public ValidateException(Map<String, String> errors) {
		super("未通过数据校验");
		this.errors = errors;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

}
