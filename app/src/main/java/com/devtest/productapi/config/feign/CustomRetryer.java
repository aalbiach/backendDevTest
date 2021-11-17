package com.devtest.productapi.config.feign;

import feign.Retryer;

public class CustomRetryer extends Retryer.Default implements Retryer {

    public static final int PERIOD = 100;
    public static final int MAX_PERIOD = 500;
    public static final int MAX_ATTEMPTS = 2;

    public CustomRetryer() {
        super(PERIOD, MAX_PERIOD, MAX_ATTEMPTS);
    }

}
