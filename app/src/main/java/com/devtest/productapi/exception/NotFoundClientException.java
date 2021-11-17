package com.devtest.productapi.exception;

public class NotFoundClientException extends CustomClientException {

    public NotFoundClientException(int status, String url, String message) {
        super(status, url, message);
    }

}
