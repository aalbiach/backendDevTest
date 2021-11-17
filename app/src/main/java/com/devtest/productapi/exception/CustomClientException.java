package com.devtest.productapi.exception;

public class CustomClientException extends RuntimeException {

    private final int status;
    private final String url;

    public CustomClientException(int status, String url, String message) {
        super(message);
        this.status = status;
        this.url = url;
    }

    public int getStatus() {
        return status;
    }

    public String getUrl() {
        return url;
    }

}
