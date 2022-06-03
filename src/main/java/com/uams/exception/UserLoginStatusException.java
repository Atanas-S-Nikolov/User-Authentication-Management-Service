package com.uams.exception;

public class UserLoginStatusException extends RuntimeException {
    public UserLoginStatusException() {
        super();
    }

    public UserLoginStatusException(String message) {
        super(message);
    }

    public UserLoginStatusException(String message, Throwable cause) {
        super(message, cause);
    }
}
