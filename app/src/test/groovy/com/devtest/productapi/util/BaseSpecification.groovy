package com.devtest.productapi.util

import com.devtest.productapi.client.payload.response.ProductDto
import org.jeasy.random.EasyRandom
import spock.lang.Shared
import spock.lang.Specification

import java.util.concurrent.ThreadLocalRandom

abstract class BaseSpecification extends Specification {

    @Shared
    def random = new EasyRandom()

    protected String randomProductId() {
        return random.nextObject(String.class)
    }

    protected static int randomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1)
    }

    protected ProductDto randomProductDto() {
        return random.nextObject(ProductDto.class)
    }

    protected ProductDto randomProductDto(String productId) {
        return randomProductDto()
                .toBuilder()
                .id(productId)
                .build()
    }
}
