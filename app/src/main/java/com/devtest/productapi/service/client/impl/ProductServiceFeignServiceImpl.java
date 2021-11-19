package com.devtest.productapi.service.client.impl;

import com.devtest.productapi.client.ProductServiceFeign;
import com.devtest.productapi.client.payload.response.ProductDto;
import com.devtest.productapi.service.client.ProductServiceFeignService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductServiceFeignServiceImpl implements ProductServiceFeignService {

    private final ProductServiceFeign productServiceFeign;

    @Override
    @CircuitBreaker(name = "product-service", fallbackMethod = "retrieveSimilarProductIdsFallback")
    @RateLimiter(name = "product-service", fallbackMethod = "retrieveSimilarProductIdsFallback")
    @Retry(name = "product-service", fallbackMethod = "retrieveSimilarProductIdsFallback")
    public List<String> retrieveSimilarProductIds(String productId) {
        final var similarProducts = productServiceFeign.retrieveSimilarProductIds(productId);

        log.info("Found products '{}' similar to '{}'.", similarProducts, productId);

        return similarProducts;
    }

    @Override
    @CircuitBreaker(name = "product-service", fallbackMethod = "retrieveProductFallback")
    @RateLimiter(name = "product-service", fallbackMethod = "retrieveProductFallback")
    @Retry(name = "product-service", fallbackMethod = "retrieveProductFallback")
    public ProductDto retrieveProduct(String productId) {
        final var product = productServiceFeign.retrieveProduct(productId);

        log.info("Found product with ProductId='{}'", productId);

        return product;
    }

    public List<String> retrieveSimilarProductIdsFallback(String productId, Throwable ex) {
        log.error("Returning fallback response for endpoint '/product/{}/similarids'", productId);
        return List.of();
    }
    public ProductDto retrieveProductFallback(String productId, Throwable ex) {
        log.error("Returning fallback response for endpoint '/product/{}'", productId);
        return null;
    }

}
