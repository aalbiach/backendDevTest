package com.devtest.productapi.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.net.SocketTimeoutException;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundClientException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundClientException ex, WebRequest request, HttpServletRequest servletRequest) {
        log.error("Caught NotFoundClientException. Returning custom error");
        final var status    = HttpStatus.NOT_FOUND;
        final var jsonError = new CustomJsonError(status, status.getReasonPhrase(), servletRequest.getRequestURI());

        return handleExceptionInternal(ex, jsonError, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(InternalServerErrorClientException.class)
    public ResponseEntity<Object> handleInternalServerErrorClientException(InternalServerErrorClientException ex, WebRequest request, HttpServletRequest servletRequest) {
        log.error("Caught InternalServerErrorClientException. Returning custom error");
        final var status    = HttpStatus.INTERNAL_SERVER_ERROR;
        final var jsonError = new CustomJsonError(status, status.getReasonPhrase(), servletRequest.getRequestURI());

        return handleExceptionInternal(ex, jsonError, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(SocketTimeoutException.class)
    public ResponseEntity<Object> handleSocketTimeoutException(SocketTimeoutException ex, WebRequest request, HttpServletRequest servletRequest) {
        log.error("Caught SocketTimeoutException. Returning custom error");
        final var status = HttpStatus.REQUEST_TIMEOUT;
        final var jsonError = new CustomJsonError(status, status.getReasonPhrase(), servletRequest.getRequestURI());

        return handleExceptionInternal(ex, jsonError, new HttpHeaders(), status, request);
    }

}
