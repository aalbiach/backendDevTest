package com.devtest.productapi.config.feign;

import feign.Logger;
import feign.codec.ErrorDecoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {

    @Bean
    public ErrorDecoder customErrorDecoder() {
        return new CustomErrorDecoder();
    }

    @Bean
    public Logger customLogger() {
        return new Slf4jLogger();
    }

}
