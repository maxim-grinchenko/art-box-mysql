package com.exeption;

public abstract class AppException extends RuntimeException{

	private static final long serialVersionUID = -8425791063561407989L;

	public AppException(Throwable throwable) {
        super(throwable);
    }
}
