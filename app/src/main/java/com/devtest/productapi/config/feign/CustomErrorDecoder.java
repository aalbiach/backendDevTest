package com.devtest.productapi.config.feign;

import com.devtest.productapi.exception.InternalServerErrorClientException;
import com.devtest.productapi.exception.NotFoundClientException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Log4j2
public class CustomErrorDecoder extends ErrorDecoder.Default implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        final var status = response.status();
        final var uri = getUrl(response);

        log.error("An error occurred when using feign client. Status='{}', URI='{}', Method='{}'", status, uri, methodKey);

        if (HttpStatus.NOT_FOUND.value() == status) {
            return new NotFoundClientException(status, uri, readBody(response));
        } else if (HttpStatus.INTERNAL_SERVER_ERROR.value() == status) {
            return new InternalServerErrorClientException(status, uri, readBody(response));
        }

        return super.decode(methodKey, response);
    }

    private String getUrl(Response response) {
        return response.request().url();
    }

    private String readBody(Response response) {
        if (Objects.isNull(response.body())) {
            return null;
        }

        try {
            return Util.toString(response.body().asReader(StandardCharsets.UTF_8));
        } catch (IOException e) {
            log.error(e.getMessage());
            return "";
        }
    }

}
