package com.backinfile.support;

public class SysException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SysException(String message) {
		super(message);
	}

	public SysException(Throwable cause) {
		super(cause);
	}
}
