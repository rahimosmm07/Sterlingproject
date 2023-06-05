package com.example.Exception;

public class AppointmentNotFoundException extends Exception {

	public AppointmentNotFoundException() {
		super();

	}

	public AppointmentNotFoundException(String message, Throwable cause) {
		super(message, cause);

	}

	public AppointmentNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public AppointmentNotFoundException(String message) {
		super(message);

	}

	public AppointmentNotFoundException(Throwable cause) {
		super(cause);

	}

}
