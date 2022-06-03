package com.uams.exception;

public class UserConstraintViolationException extends RuntimeException {

    public UserConstraintViolationException() {
        super();
    }

    public UserConstraintViolationException(String message) {
        super(message);
    }

    public UserConstraintViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
