package com.grupolemon.ocarsionplus.service.exceptions;

public class CarServiceException extends Exception {

	private static final long serialVersionUID = -1706624542158619239L;

	public CarServiceException(Throwable cause) {
			super(cause);
		}

	public CarServiceException(String message) {
			super(message);
		}

	public CarServiceException(String message, Throwable cause) {
			super(message, cause);
		}

}
