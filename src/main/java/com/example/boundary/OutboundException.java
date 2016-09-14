package com.example.boundary;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class OutboundException extends RuntimeException {

	private static final long serialVersionUID = 8603606164096376934L;

	public OutboundException() {
		super();
	}

	public OutboundException(String message) {
		super(message);
	}

	public OutboundException(Throwable cause) {
		super(cause);
	}

	public OutboundException(String message, Throwable cause) {
		super(message, cause);
	}

	public OutboundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
