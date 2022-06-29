package com.uams.exception.handler;

import com.uams.controller.UserController;
import com.uams.exception.InvalidCredentialsException;
import com.uams.exception.UserConstraintViolationException;
import com.uams.exception.UserLoginStatusException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

import static com.uams.model.response.builder.ErrorResponseBuilder.buildErrorResponse;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestControllerAdvice(assignableTypes = { UserController.class })
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger LOGGER = LogManager.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(value = { UserConstraintViolationException.class })
    public ResponseEntity<Object> handleUserConstraintViolation(UserConstraintViolationException exception) {
        logError(exception);
        return buildErrorResponse(CONFLICT, exception);
    }

    @ExceptionHandler(value = { InvalidCredentialsException.class })
    public ResponseEntity<Object> handleInvalidCredentials(InvalidCredentialsException exception) {
        logError(exception);
        return buildErrorResponse(UNAUTHORIZED, exception);
    }

    @ExceptionHandler(value = { UserLoginStatusException.class, ConstraintViolationException.class })
    public ResponseEntity<Object> handleBadRequest(RuntimeException exception) {
        logError(exception);
        return buildErrorResponse(BAD_REQUEST, exception);
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleUnknownException(Exception exception) {
        logError(exception);
        return buildErrorResponse(INTERNAL_SERVER_ERROR, "Something went wrong. Try again later");
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        return buildErrorResponse(status, ex);
    }

    private void logError(Exception exception) {
        LOGGER.error("Error occurred", exception);
    }
}
