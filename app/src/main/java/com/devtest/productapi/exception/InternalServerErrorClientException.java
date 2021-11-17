package com.devtest.productapi.exception;

public class InternalServerErrorClientException extends CustomClientException {

    public InternalServerErrorClientException(int status, String url, String message) {
        super(status, url, message);
    }

}
