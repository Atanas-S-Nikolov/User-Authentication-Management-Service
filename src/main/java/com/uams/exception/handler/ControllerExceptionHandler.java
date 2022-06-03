package com.uams.exception.handler;

import com.uams.controller.UserController;
import com.uams.exception.InvalidCredentialsException;
import com.uams.exception.UserConstraintViolationException;
import com.uams.exception.UserLoginStatusException;
import com.uams.model.response.ErrorResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.uams.model.response.builder.ErrorResponseBuilder.buildErrorResponse;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice(assignableTypes = { UserController.class })
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger LOGGER = LogManager.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(value = { UserConstraintViolationException.class })
    public ResponseEntity<ErrorResponse> handleUserConstraintViolation(UserConstraintViolationException exception) {
        logError(exception);
        return buildErrorResponse(CONFLICT, exception);
    }

    @ExceptionHandler(value = { InvalidCredentialsException.class })
    public ResponseEntity<ErrorResponse> handleInvalidCredentials(RuntimeException exception) {
        logError(exception);
        return buildErrorResponse(UNAUTHORIZED, exception);
    }

    @ExceptionHandler(value = { UserLoginStatusException.class })
    public ResponseEntity<ErrorResponse> handleUserLoginStatusException(RuntimeException exception) {
        logError(exception);
        return buildErrorResponse(BAD_REQUEST, exception);
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<ErrorResponse> handleUnknownException(Exception exception) {
        logError(exception);
        return buildErrorResponse(INTERNAL_SERVER_ERROR, "Something went wrong. Try again later");
    }

    private void logError(Exception exception) {
        LOGGER.error(exception);
    }
}
