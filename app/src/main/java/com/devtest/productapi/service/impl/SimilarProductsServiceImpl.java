package com.devtest.productapi.service.impl;

import com.devtest.productapi.client.payload.response.ProductDto;
import com.devtest.productapi.domain.Product;
import com.devtest.productapi.service.SimilarProductsService;
import com.devtest.productapi.service.client.ProductServiceFeignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class SimilarProductsServiceImpl implements SimilarProductsService {

    private final ProductServiceFeignService productServiceFeignService;

    @Override
    public Set<Product> retrieveSimilarProducts(String productId) {
        log.info("Try to find similar products for ProductId='{}'", productId);

        return productServiceFeignService.retrieveSimilarProductIds(productId)
                                         .stream()
                                         .map(productServiceFeignService::retrieveProduct)
                                         .filter(Objects::nonNull)
                                         .map(ProductDto::toProduct)
                                         .collect(Collectors.toSet());
    }

}
