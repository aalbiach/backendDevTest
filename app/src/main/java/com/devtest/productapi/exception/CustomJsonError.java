package com.devtest.productapi.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Value
public class CustomJsonError {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssSZ", timezone = "UTC")
    Instant timestamp;

    Integer status;

    String error;

    String path;

    public CustomJsonError(HttpStatus status, String error, String path) {
        this.timestamp = Instant.now();
        this.status    = status.value();
        this.error     = error;
        this.path      = path;
    }

}
