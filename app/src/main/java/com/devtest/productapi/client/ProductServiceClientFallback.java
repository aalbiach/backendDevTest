package com.devtest.productapi.client;

import com.devtest.productapi.client.payload.response.ProductDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
public class ProductServiceClientFallback implements ProductServiceClient {

    @Override
    public List<String> retrieveSimilarProductIds(String productId) {
        log.error("Returning fallback response for endpoint '/product/{}/similarids'", productId);
        return List.of();
    }

    @Override
    public ProductDto retrieveProduct(String productId) {
        log.error("Returning fallback response for endpoint '/product/{}'", productId);
        return null;
    }

}
