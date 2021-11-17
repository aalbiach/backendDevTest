package com.devtest.productapi.config.feign;

import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {

    @Bean
    public ErrorDecoder customErrorDecoder() {
        return new CustomErrorDecoder();
    }

    @Bean
    public Retryer customRetryer() {
        return new CustomRetryer();
    }
}
