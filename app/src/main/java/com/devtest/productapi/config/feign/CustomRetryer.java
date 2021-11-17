package com.devtest.productapi.config.feign;

import feign.Retryer;

public class CustomRetryer extends Retryer.Default implements Retryer {

    public CustomRetryer() {
        super(100, 500, 2);
    }

}
