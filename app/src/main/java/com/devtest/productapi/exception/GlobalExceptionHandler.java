package com.devtest.productapi.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundClientException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> notFoundException(NotFoundClientException ex, WebRequest request, HttpServletRequest servletRequest) {
        log.trace("Caught NotFoundClientException. Returning custom error");
        final var status    = HttpStatus.NOT_FOUND;
        final var jsonError = new CustomJsonError(status, status.getReasonPhrase(), servletRequest.getRequestURI());

        return handleExceptionInternal(ex, jsonError, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(InternalServerErrorClientException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> internalServerErrorClientException(InternalServerErrorClientException ex, WebRequest request, HttpServletRequest servletRequest) {
        log.trace("Caught InternalServerErrorClientException. Returning custom error");
        final var status    = HttpStatus.INTERNAL_SERVER_ERROR;
        final var jsonError = new CustomJsonError(status, status.getReasonPhrase(), servletRequest.getRequestURI());

        return handleExceptionInternal(ex, jsonError, new HttpHeaders(), status, request);
    }

}
